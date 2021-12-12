package mozziyulmu.meeple.Repository.customImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import mozziyulmu.meeple.Repository.custom.BoardgameReposirotyCustom;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.QBoardgame;
import mozziyulmu.meeple.entity.QCategory;
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
    // 1+N 쿼리 호출됨 : 개선이 되려나?
    public List<BoardgameListDto> getBoardgameSimpleList() {
        List<Boardgame> boardgameList = jpaQueryFactory
                .select(boardgame)
                .from(boardgame)
                .fetch();

        return boardgameList.stream().map(
                boardgame -> {
                    BoardgameListDto eachData = new BoardgameListDto(boardgame);

                    eachData.setViewCategory(jpaQueryFactory.select(category.korName)
                                                            .from(category)
                                                            .join(category.boardgames, boardCateRT)
                                                            .where(boardCateRT.boardgame.eq(boardgame))
                                                            .fetch());

                    return eachData;
                }
        ).collect(Collectors.toList());
    }
}
