package mozziyulmu.meeple.Repository.customImpl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mozziyulmu.meeple.Repository.custom.SearchRepositoryCustom;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.DifficultyGrade;
import mozziyulmu.meeple.support.BoardgameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static mozziyulmu.meeple.entity.Relation.BoardCategory.QBoardCateRT.*;
import static mozziyulmu.meeple.entity.Relation.BoardMechanism.QBoardMechaRT.*;
import static mozziyulmu.meeple.entity.QBoardgame.*;

public class SearchRepositoryImpl implements SearchRepositoryCustom {
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public SearchRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<BoardgameListDto> searchBoardgame(BoardgameFilter boardgameFilter, Pageable pageable) {
        List<Long> boardgameIds = new ArrayList<>();
        List<Long> categoryIds = boardgameFilter.getCategoryIds();
        long cateSize = categoryIds.size();
        if(cateSize > 0){
            List<Long> boardgameInCategory = jpaQueryFactory
                    .select(boardCateRT.boardgame.id)
                    .from(boardCateRT)
                    .where(boardCateRT.category.id.in(categoryIds))
                    .groupBy(boardCateRT.boardgame.id)
                    .having(boardCateRT.boardgame.id.count().eq(cateSize))
                    .fetch();

            // 검색 결과가 없는 경우
            if(boardgameInCategory.size() == 0)
                return new PageImpl<>(new ArrayList<>(), pageable, 0);

            boardgameIds.addAll(boardgameInCategory);
        }

        List<Long> mechanismIds = boardgameFilter.getMechanismIds();
        long mechSize = mechanismIds.size();
        if(mechSize > 0){
            List<Long> boardgameInMechanism = jpaQueryFactory
                    .select(boardMechaRT.boardgame.id)
                    .from(boardMechaRT)
                    .where(boardMechaRT.mechanism.id.in(mechanismIds))
                    .groupBy(boardMechaRT.boardgame.id)
                    .having(boardMechaRT.boardgame.id.count().eq(mechSize))
                    .fetch();

            // 검색 결과가 없는 경우
            if(boardgameInMechanism.size() == 0)
                return new PageImpl<>(new ArrayList<>(), pageable, 0);

            boardgameIds.addAll(boardgameInMechanism);
        }

        List<BoardgameListDto> contents = jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class, boardgame))
                .from(boardgame)
                .where(
                        inBoardgameIds(boardgameIds),
                        likeBoardgameKorName(boardgameFilter.getInnerKorName()),
                        btwPlayers(boardgameFilter.getPlayers()),
                        checkDifficulty(boardgameFilter.getDifficultyGrade())
                )
                .orderBy(boardgame.geekRating.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<> (contents, pageable, contents.size());
    }

    BooleanExpression inBoardgameIds(List<Long> boardgameIds){
        return (boardgameIds.size() > 0) ? boardgame.id.in(boardgameIds) : null;
    }
    BooleanExpression likeBoardgameKorName(String searchName){
        return (StringUtils.hasText(searchName)) ? boardgame.korName.like("%" + searchName + "%") : null;
    }
    BooleanExpression btwPlayers(Integer players){
        if (players.intValue() <= 0)
            return null;
        return boardgame.minPlayer.loe(players).and(boardgame.maxPlayer.goe(players));
    }
    BooleanExpression checkDifficulty(DifficultyGrade difficultyGrade) {
        return (difficultyGrade != null) ? boardgame.difficulty.eq(difficultyGrade) : null;
    }
}
