package mozziyulmu.meeple.Repository.customImpl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mozziyulmu.meeple.Repository.custom.BoardgameReposirotyCustom;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.QBoardgame;
import mozziyulmu.meeple.entity.QCategory;
import mozziyulmu.meeple.entity.Relation.BoardCategory.BoardCateRT;
import mozziyulmu.meeple.entity.Relation.BoardCategory.QBoardCateRT;
import mozziyulmu.meeple.entity.Relation.BoardMechanism.QBoardMechaRT;
import mozziyulmu.meeple.entity.Relation.BoardRecom.QBoardRecomRT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static mozziyulmu.meeple.entity.QBoardgame.boardgame;
import static mozziyulmu.meeple.entity.QCategory.category;
import static mozziyulmu.meeple.entity.Relation.BoardCategory.QBoardCateRT.boardCateRT;
import static mozziyulmu.meeple.entity.Relation.BoardMechanism.QBoardMechaRT.boardMechaRT;
import static mozziyulmu.meeple.entity.Relation.BoardRecom.QBoardRecomRT.boardRecomRT;

public class BoardgameRepositoryImpl implements BoardgameReposirotyCustom {
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public BoardgameRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    // 1+N 쿼리 호출됨 : 개선이 되려나? - 출력용 String으로 처리해봄
    public Page<BoardgameListDto> getBoardgameSimpleList(Pageable pageable) {
        // 생성자 Projection 처리
        List<BoardgameListDto> contents = jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class, boardgame))
                .from(boardgame)
                .orderBy(boardgame.geekRating.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(contents, pageable, contents.size());
        // fetchCount는 만료된 함수로 삭제
//        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<BoardgameListDto> getBoardgameInCategory(String categoryKorName, Pageable pageable) {
        // 생성자 Projection 처리
        List<BoardgameListDto> contents = jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class, boardCateRT.boardgame))
                .from(boardCateRT)
                .where(boardCateRT.category.korName.eq(categoryKorName))
                .orderBy(boardCateRT.boardgame.geekRating.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

//        JPAQuery<Boardgame> countQuery = jpaQueryFactory
//                .select(boardCateRT.boardgame)
//                .from(boardCateRT)
//                .where(boardCateRT.category.korName.eq(categoryKorName));

        return new PageImpl<>(contents, pageable, contents.size());
//        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<BoardgameListDto> getBoardgameInMechanism(String mechanismKorName, Pageable pageable) {
        List<BoardgameListDto> contents = jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class, boardMechaRT.boardgame))
                .from(boardMechaRT)
                .where(boardMechaRT.mechanism.korName.eq(mechanismKorName))
                .orderBy(boardMechaRT.boardgame.geekRating.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(contents, pageable, contents.size());
    }

    // 추천 리스트는 Page 처리만큼 많지 않을 거라 예상, Page 처리 제외
    public List<BoardgameListDto> getBoardgameInRecommand(Long recommandId) {
        return jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class, boardRecomRT.boardgame))
                .from(boardRecomRT)
                .where(boardRecomRT.recommand.id.eq(recommandId))
                .orderBy(boardRecomRT.boardgame.geekRating.desc())
                .fetch();
    }

    @Override
    public List<String> getBoardgameMechanismNames(Long BoardgameId) {
        return jpaQueryFactory
                .select(boardMechaRT.mechanism.korName)
                .from(boardMechaRT)
                .where(boardMechaRT.boardgame.id.eq(BoardgameId))
                .fetch();
    }

    @Override
    public List<String> getBoardgameCategoryNames(Long BoardgameId) {
        return jpaQueryFactory
                .select(boardCateRT.category.korName)
                .from(boardCateRT)
                .where(boardCateRT.boardgame.id.eq(BoardgameId))
                .fetch();
    }
}
