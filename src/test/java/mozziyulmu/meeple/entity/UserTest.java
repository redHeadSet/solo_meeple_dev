package mozziyulmu.meeple.entity;

import mozziyulmu.meeple.entity.Relation.BoardUser.OwnBoardgames;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class UserTest {
    @Autowired
    public EntityManager em;

    @Commit
    @Test
    public void test() {
        Boardgame bg1 = new Boardgame();
        bg1.setName_kor("테포마");
        bg1.setPublished_date(LocalDateTime.now());
        em.persist(bg1);
        Boardgame bg2 = new Boardgame();
        bg2.setName_kor("니다벨리르");
        em.persist(bg2);
        Boardgame bg3 = new Boardgame();
        bg3.setName_kor("팬거시");
        em.persist(bg3);
        Boardgame bg4 = new Boardgame();
        bg4.setName_kor("아딱");
        em.persist(bg4);

        em.flush();
        em.clear();

        User user = new User();
        user.setNick_name("한재");
        OwnBoardgames ownBoardgames1 = user.addOwnBoardgame(bg1);
        OwnBoardgames ownBoardgames2 = user.addOwnBoardgame(bg2);
        OwnBoardgames ownBoardgames3 = user.addOwnBoardgame(bg3);
        OwnBoardgames ownBoardgames4 = user.addOwnBoardgame(bg4);
        em.persist(ownBoardgames1);
        em.persist(ownBoardgames2);
        em.persist(ownBoardgames3);
        em.persist(ownBoardgames4);
        em.persist(user);

        em.flush();
        em.clear();
    }
}