package mozziyulmu.meeple.Repository.customImpl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mozziyulmu.meeple.Repository.custom.SearchRepositoryCustom;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Relation.BoardCategory.BoardCateRT;
import mozziyulmu.meeple.entity.Relation.BoardCategory.QBoardCateRT;
import mozziyulmu.meeple.searchFilter.BoardgameFilter;
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
        List<String> categoriesNames = boardgameFilter.getCategoriesName();
        long cateSize = categoriesNames.size();
        if(cateSize > 0){
            boardgameIds.addAll(
                    jpaQueryFactory
                        .select(boardCateRT.boardgame.id)
                        .from(boardCateRT)
                        .where(boardCateRT.category.korName.in(categoriesNames))
                        .groupBy(boardCateRT.boardgame.id)
                        .having(boardCateRT.boardgame.id.count().eq(cateSize))
                        .fetch()
            );
        }

        List<String> mechanismsNames = boardgameFilter.getMechanismsName();
        long mechSize = mechanismsNames.size();
        if(mechSize > 0){
            boardgameIds.addAll(
                    jpaQueryFactory
                        .select(boardMechaRT.boardgame.id)
                        .from(boardMechaRT)
                        .where(boardMechaRT.mechanism.korName.in(mechanismsNames))
                        .groupBy(boardMechaRT.boardgame.id)
                        .having(boardMechaRT.boardgame.id.count().eq(mechSize))
                        .fetch()
            );
        }

        List<BoardgameListDto> contents = jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class, boardgame))
                .from(boardgame)
                .where(
                        inBoardgameIds(boardgameIds),
                        likeBoardgameKorName(boardgameFilter.getInnerName())
                )
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
}
