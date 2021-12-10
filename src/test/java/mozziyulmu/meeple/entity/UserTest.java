package mozziyulmu.meeple.entity;

import mozziyulmu.meeple.entity.Relation.BoardMechanism.BoardMechaRT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class UserTest {
    @Autowired
    public EntityManager em;

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

    @Commit
    @Test
    public void 보드게임_메커니즘_test() {
        // given
        Boardgame bg1 = new Boardgame(); bg1.setKor_name("테포마"); em.persist(bg1);
        Boardgame bg2 = new Boardgame(); bg2.setKor_name("팬거시");  em.persist(bg2);

        Mechanism mech1 = new Mechanism("엔진", "engine"); em.persist(mech1);
        Mechanism mech2 = new Mechanism("셋콜렉션", "set collection"); em.persist(mech2);
        Mechanism mech3 = new Mechanism("타일", "tile"); em.persist(mech3);
        Mechanism mech4 = new Mechanism("협동", "co-work"); em.persist(mech4);
        Mechanism mech5 = new Mechanism("경쟁", "fight"); em.persist(mech5);
        Mechanism mech6 = new Mechanism("카드", "card"); em.persist(mech6);

        bg1.initMechanism(mech1, mech3, mech5, mech6);
        bg2.initMechanism(mech3, mech4, mech6);
        Mechanism mech7 = new Mechanism("경영", "operation"); em.persist(mech7);
        bg1.addMechanism(mech7);

        Long id1 = bg1.getId();
        Long id2 = bg2.getId();

        em.flush(); em.clear();

        // when
        Boardgame boardgame1 = em.find(Boardgame.class, id1);
        Boardgame boardgame2 = em.find(Boardgame.class, id2);

        // then
        // 비효율적으로 쿼리가 나가는데, Repository에서 fetch join 처리 필요할 듯
        Assertions.assertThat(boardgame1.getMechanisms().size()).isEqualTo(5);
        System.out.print(boardgame1.getKor_name() + "의 매커니즘 : ");
        for (BoardMechaRT each : boardgame1.getMechanisms()) {
            System.out.print(each.getMechanism().getKor_name() + ", ");
        }
        System.out.println();

        Assertions.assertThat(boardgame2.getMechanisms().size()).isEqualTo(3);
        System.out.print(boardgame2.getKor_name() + "의 매커니즘 : ");
        for (BoardMechaRT each : boardgame2.getMechanisms()) {
            System.out.print(each.getMechanism().getKor_name() + ", ");
        }
        System.out.println();
    }
}