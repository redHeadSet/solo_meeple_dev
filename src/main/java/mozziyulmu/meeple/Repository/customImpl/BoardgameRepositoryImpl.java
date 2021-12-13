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
}
