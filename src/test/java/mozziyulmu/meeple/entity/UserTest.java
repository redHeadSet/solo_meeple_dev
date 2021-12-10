package mozziyulmu.meeple.entity;

import mozziyulmu.meeple.entity.Relation.BoardUser.EvaluateBoardgames;
import mozziyulmu.meeple.entity.Relation.BoardUser.InterestBoardgames;
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
    public void 보드게임_유저_test() {
        // given
        Boardgame bg1 = new Boardgame(); bg1.setKor_name("테포마"); bg1.setPublished_date(LocalDateTime.now());em.persist(bg1);
        Boardgame bg2 = new Boardgame(); bg2.setKor_name("니다벨리르");  em.persist(bg2);
        Boardgame bg3 = new Boardgame(); bg3.setKor_name("팬거시");    em.persist(bg3);
        Boardgame bg4 = new Boardgame(); bg4.setKor_name("아딱"); em.persist(bg4);

        User user = new User("stikfas7@naver.com", "1234", "한재");
        user.addOwnBoardgame(bg1);
        user.addOwnBoardgame(bg2);
        user.addOwnBoardgame(bg3);
        user.addOwnBoardgame(bg4);
        user.addInterestBoardgame(bg1);
        user.addInterestBoardgame(bg2);
        user.addEvaluateBoardgame(bg3);
        user.addEvaluateBoardgame(bg4);

        em.persist(user);
        Long id = user.getId();

        em.flush();
        em.clear();

        // when
        User finded_user = em.find(User.class, id);

        // then
        Assertions.assertThat(finded_user.ownBoardgames.size()).isEqualTo(4);
        Assertions.assertThat(finded_user.interestBoardgames.size()).isEqualTo(2);
        Assertions.assertThat(finded_user.evaluateBoardgames.size()).isEqualTo(2);
    }

    @Test
    public void 보드게임_메커니즘_test() {
        // given
        Boardgame bg1 = new Boardgame(); bg1.setKor_name("테포마"); em.persist(bg1);
        Boardgame bg2 = new Boardgame(); bg2.setKor_name("니다벨리르");  em.persist(bg2);
        Boardgame bg3 = new Boardgame(); bg3.setKor_name("팬거시");    em.persist(bg3);
        Boardgame bg4 = new Boardgame(); bg4.setKor_name("아딱"); em.persist(bg4);

        Mechanism mech1 = new Mechanism("엔진", "engine"); em.persist(mech1);
        Mechanism mech2 = new Mechanism("랜덤", "random"); em.persist(mech2);

        // when

        // then

    }
}