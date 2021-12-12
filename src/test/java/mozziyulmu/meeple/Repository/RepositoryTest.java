package mozziyulmu.meeple.Repository;

import static org.junit.jupiter.api.Assertions.*;

import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class RepositoryTest {
    @Autowired
    private EntityManager em;
    @Autowired private UserRepository userRepository;
    @Autowired private BoardgameRepository boardgameRepository;

    public Publisher setPublisher(String kor_name, String eng_name) {
        Publisher publisher = new Publisher(kor_name, eng_name);
        em.persist(publisher);
        return publisher;
    }

    public Mechanism setMechanism(String kor_name, String eng_name) {
        Mechanism mechanism = new Mechanism(kor_name, eng_name);
        em.persist(mechanism);
        return mechanism;
    }

    public Category setCategory(String kor_name, String eng_name) {
        Category category = new Category(kor_name, eng_name);
        em.persist(category);
        return category;
    }

    @BeforeEach
    public void setDefault() {
        Publisher kbg = setPublisher("코리아 보드게임즈", "Korea Boardgames");
        Publisher bm = setPublisher("보드엠", "BoardM Factory");
        Publisher rbg = setPublisher("라벤스부르거", "Ravensburger");

        Mechanism mech1 = setMechanism("엔진", "engine");
        Mechanism mech2 = setMechanism("셋콜렉션", "set collection");
        Mechanism mech3 = setMechanism("타일 놓기", "tile placement");
        Mechanism mech4 = setMechanism("홀로 플레이", "solo play");
        Mechanism mech5 = setMechanism("경쟁", "take that");
        Mechanism mech6 = setMechanism("6각 격자", "hexagon grid");
        Mechanism mech7 = setMechanism("턴제", "turn order");
        Mechanism mech8 = setMechanism("액션 큐 방식", "Action queue");
        Mechanism mech9 = setMechanism("캠페인", "Campaign");
        Mechanism mech10 = setMechanism("소통 제한", "Communication limit");
        Mechanism mech11 = setMechanism("덱 빌딩", "Deck building");
        Mechanism mech12 = setMechanism("극한 경영", "Hard Management");
        Mechanism mech13 = setMechanism("대출", "Loans");
        Mechanism mech14 = setMechanism("시장", "Market");
        Mechanism mech15 = setMechanism("네트워크 경로 구성", "Network building");
        Mechanism mech16 = setMechanism("주사위 굴림", "Dice Rolling");

        Category ct1 = setCategory("경제", "Economic");
        Category ct2 = setCategory("환경", "Environment");
        Category ct3 = setCategory("산업", "Industry");
        Category ct4 = setCategory("과학", "Science");
        Category ct5 = setCategory("우주", "Space");
        Category ct6 = setCategory("어드벤쳐", "Adventure");
        Category ct7 = setCategory("탐험", "Exploration");
        Category ct8 = setCategory("판타지", "Fantasy");
        Category ct9 = setCategory("대결, 싸움", "Fighting");
        Category ct10 = setCategory("미니어처", "Miniature");
        Category ct11 = setCategory("운송", "Transportation");
        Category ct12 = setCategory("주사위", "Dice");

        Boardgame terraforming_mars = new Boardgame("테라포밍 마스", "terraforming mars")
                .setPublishedYear(2016)
                .setPlayers(1, 5, 3)
                .setPlayingTime(120, 120)
                .setPublisher(kbg)
                .setDifficulty(DifficultyGrade.MIDDLE)
                .setGeekData(167791, 8.247, 3.24)
                .initMechanism(mech1, mech2, mech3, mech4, mech5, mech6, mech7)
                .initCategorys(ct1, ct2, ct3, ct4, ct5);
        em.persist(terraforming_mars);

        Boardgame gloom_haven = new Boardgame("글룸 헤이븐", "Gloom Haven")
                .setPublishedYear(2017)
                .setPlayers(1, 4, 3)
                .setDifficulty(DifficultyGrade.MIDDLE)
                .setPublisher(kbg)
                .setGeekData(174430, 8.515, 3.87)
                .initMechanism(mech4, mech6, mech7, mech8, mech9, mech10, mech11)
                .initCategorys(ct6, ct7, ct8, ct9, ct10);
        em.persist(gloom_haven);

        Boardgame brass_birmingham = new Boardgame("브라스:버밍엄", "Brass:birmingham")
                .setPublishedYear(2018)
                .setPlayers(2, 4, 3)
                .setDifficulty(DifficultyGrade.HARD)
                .setPublisher(bm)
                .setGeekData(224517, 8.416, 3.91)
                .initMechanism(mech12, mech13, mech14, mech15)
                .initCategorys(ct1, ct3, ct11);
        em.persist(brass_birmingham);

        Boardgame castles_of_burgundy = new Boardgame("버건디의 성", "The castles of burgundy")
                .setPublishedYear(2011)
                .setPlayers(2, 4, 2)
                .setDifficulty(DifficultyGrade.EASY)
                .setPublisher(rbg)
                .setGeekData(84876, 8.007, 3.00)
                .initMechanism(mech3, mech4, mech5, mech6, mech16)
                .initCategorys(ct12);
        em.persist(castles_of_burgundy);

        User user = new User("stikfas7@naver.com", "1234", "한재");
        user.addOwnBoardgame(terraforming_mars);
        user.addOwnBoardgame(gloom_haven);
        user.addInterestBoardgame(castles_of_burgundy);
        user.addEvaluateBoardgame(brass_birmingham);
        em.persist(user);
    }

    @Test
    public void 사용자_간단_테스트() {
        userRepository.save(new User("email1", "pass1", "nick1"));
        userRepository.save(new User("email2", "pass2", "nick2"));
        userRepository.save(new User("email3", "pass3", "nick3"));

        em.flush(); em.clear();

        List<User> allUsers = userRepository.findAllUsers();
        for (User each :
                allUsers) {
            System.out.println("닉네임 : " + each.getNickName());
        }
    }

    @Test
    public void 보드게임_Rep_테스트() {
        // given
        System.out.println("여기서부터 시작");
        List<BoardgameListDto> boardgameSimpleList = boardgameRepository.getBoardgameSimpleList();

        // when
        for (BoardgameListDto each : boardgameSimpleList) {
            each.getKorName();
        }

        // then

    }
}