package mozziyulmu.meeple.Repository.customImpl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mozziyulmu.meeple.Repository.custom.BoardgameReposirotyCustom;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.QBoardgame;
import mozziyulmu.meeple.entity.QCategory;
import mozziyulmu.meeple.entity.Relation.BoardCategory.BoardCateRT;
import mozziyulmu.meeple.entity.Relation.BoardCategory.QBoardCateRT;

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
    public List<BoardgameListDto> getBoardgameSimpleList() {
        // 생성자 Projection 처리
        return jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class,boardgame))
                .from(boardgame)
                .fetch();
    }

    @Override
    public List<BoardgameListDto> getBoardgameInCategory(String categoryKorName) {
        return jpaQueryFactory
                .select(Projections.constructor(BoardgameListDto.class, boardCateRT.boardgame))
                .from(boardCateRT)
                .where(boardCateRT.category.korName.eq(categoryKorName))
                .fetch();
    }
}
