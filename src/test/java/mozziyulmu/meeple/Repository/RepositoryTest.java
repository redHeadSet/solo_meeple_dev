package mozziyulmu.meeple.Repository;

import static org.junit.jupiter.api.Assertions.*;

import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.*;
import mozziyulmu.meeple.entity.Relation.BoardRecom.BoardRecomRT;
import mozziyulmu.meeple.searchFilter.BoardgameFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class RepositoryTest {
    @Autowired
    private EntityManager em;
    @Autowired private UserRepository userRepository;
    @Autowired private BoardgameRepository boardgameRepository;
    @Autowired private RecommandRepository recommandRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private MechanismRepository mechanismRepository;
    @Autowired private SearchRepository searchRepository;

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
        Mechanism mech17 = setMechanism("카드 드래프팅", "Card drafting");

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
        Category ct13 = setCategory("고대", "Ancient");
        Category ct14 = setCategory("카드 게임", "Card game");
        Category ct15 = setCategory("문명", "Civilzation");
        Category ct16 = setCategory("중세", "Medieval");

        Boardgame terraforming_mars = new Boardgame("테라포밍 마스", "terraforming mars")
                .setPublishedYear(2016)
                .setPlayers(1, 5, 3)
                .setPlayingTime(120, 120)
                .setPublisher(kbg)
                .setDifficulty(DifficultyGrade.MIDDLE)
                .setLineComment("화성을 지구화 시켜보자")
                .setGeekData(167791, 8.247, 3.24)
                .initMechanism(mech1, mech2, mech3, mech4, mech5, mech6, mech7)
                .initCategorys(ct1, ct2, ct3, ct4, ct5);
        terraforming_mars.addGameImage("테포마 이미지 1.jpg");
        terraforming_mars.addGameImage("테포마 이미지 2.jpg");
        terraforming_mars.addGameImage("테포마 이미지 3.jpg");
        em.persist(terraforming_mars);

        Boardgame gloom_haven = new Boardgame("글룸 헤이븐", "Gloom Haven")
                .setPublishedYear(2017)
                .setPlayers(1, 4, 3)
                .setDifficulty(DifficultyGrade.MIDDLE)
                .setLineComment("던전을 탐험하며 강해지는 우리")
                .setPublisher(kbg)
                .setGeekData(174430, 8.515, 3.87)
                .initMechanism(mech4, mech6, mech7, mech8, mech9, mech10, mech11)
                .initCategorys(ct6, ct7, ct8, ct9, ct10);
        gloom_haven.addGameImage("글룸 이미지 1.jpg");
        gloom_haven.addGameImage("글룸 이미지 2.jpg");
        gloom_haven.addGameImage("글룸 이미지 3.jpg");
        em.persist(gloom_haven);

        Boardgame brass_birmingham = new Boardgame("브라스:버밍엄", "Brass:birmingham")
                .setPublishedYear(2018)
                .setPlayers(2, 4, 3)
                .setDifficulty(DifficultyGrade.HARD)
                .setLineComment("운하와 철도를 통해 성공한 사업가가 된다")
                .setPublisher(bm)
                .setGeekData(224517, 8.416, 3.91)
                .initMechanism(mech12, mech13, mech14, mech15)
                .initCategorys(ct1, ct3, ct11);
        brass_birmingham.addGameImage("버밍엄 이미지 1.jpg");
        brass_birmingham.addGameImage("버밍엄 이미지 2.jpg");
        brass_birmingham.addGameImage("버밍엄 이미지 3.jpg");
        em.persist(brass_birmingham);

        Boardgame castles_of_burgundy = new Boardgame("버건디의 성", "The castles of burgundy")
                .setPublishedYear(2011)
                .setPlayers(2, 4, 2)
                .setDifficulty(DifficultyGrade.EASY)
                .setLineComment("중세 시대의 영주로서 내 영지를 부강하게 해보자")
                .setPublisher(rbg)
                .setGeekData(84876, 8.007, 3.00)
                .initMechanism(mech3, mech4, mech5, mech6, mech16)
                .initCategorys(ct12, ct13);
        castles_of_burgundy.addGameImage("버건디 이미지 1.jpg");
        castles_of_burgundy.addGameImage("버건디 이미지 2.jpg");
        castles_of_burgundy.addGameImage("버건디 이미지 3.jpg");
        em.persist(castles_of_burgundy);

        Boardgame seven_wonders_dual = new Boardgame("세븐원더스 듀얼", "7 Wonders dual")
                .setPublishedYear(2015)
                .setPlayers(2, 2, 2)
                .setDifficulty(DifficultyGrade.EASY)
                .setLineComment("내 문명이 너보다 셀 것이다")
                .setPublisher(kbg)
                .setGeekData(173346, 8.108, 2.23)
                .initMechanism(mech17, mech2)
                .initCategorys(ct1, ct13, ct14, ct15);
        seven_wonders_dual.addGameImage("세듀얼 이미지 1.jpg");
        seven_wonders_dual.addGameImage("세듀얼 이미지 2.jpg");
        seven_wonders_dual.addGameImage("세듀얼 이미지 3.jpg");
        em.persist(seven_wonders_dual);

        Boardgame dominion = new Boardgame("도미니언", "Dominion")
                .setPublishedYear(2008)
                .setPlayers(2, 4, 3)
                .setDifficulty(DifficultyGrade.EASY)
                .setLineComment("강한 왕국을 만들어보자")
                .setPublisher(kbg)
                .setGeekData(36218, 7.611, 2.35)
                .initMechanism(mech1, mech11, mech5)
                .initCategorys(ct16, ct14);
        dominion.addGameImage("도미니언 이미지 1.jpg");
        dominion.addGameImage("도미니언 이미지 2.jpg");
        dominion.addGameImage("도미니언 이미지 3.jpg");
        em.persist(dominion);

        User user = new User("stikfas7@naver.com", "1234", "한재");
        user.addOwnBoardgame(terraforming_mars);
        user.addOwnBoardgame(gloom_haven);
        user.addInterestBoardgame(castles_of_burgundy);
        user.addEvaluateBoardgame(brass_birmingham);
        em.persist(user);

        Recommand rec_3_party = new Recommand("3인 추천 게임")
                .setDesc("3인 추천 게임 리스트입니다.")
                .setImage("3인 대표 이미지")
                .initBoardgames(terraforming_mars, dominion);
        em.persist(rec_3_party);

        Recommand rec_2_party = new Recommand("2인 추천 게임")
                .setDesc("2인에서 하면 꿀잼")
                .setImage("2인 대표 이미지")
                .initBoardgames(castles_of_burgundy, seven_wonders_dual);
        em.persist(rec_2_party);
        em.flush(); em.clear();
    }

    @Test
    public void 사용자_테스트() {
        userRepository.save(new User("email1", "pass1", "nick1"));
        userRepository.save(new User("email2", "pass2", "nick2"));
        userRepository.save(new User("email3", "pass3", "nick3"));

        em.flush(); em.clear();

        List<User> allUsers = userRepository.findAll();
        Assertions.assertThat(allUsers.size()).isEqualTo(4);
        for (User each :
                allUsers) {
            System.out.println("닉네임 : " + each.getNickName());
        }
    }

    @Test
    public void 보드게임_테스트() {
        // given

        // when
        List<Boardgame> findOne = boardgameRepository.findByKorName("테라포밍 마스");
        PageRequest pr = PageRequest.of(0, 20);
        Page<BoardgameListDto> boardgameSimpleList = boardgameRepository.getBoardgameSimpleList(pr);

        // then
        Assertions.assertThat(boardgameSimpleList.getTotalElements()).isEqualTo(6);
        for (BoardgameListDto each : boardgameSimpleList) {
            System.out.println(each.toString());
        }
    }

    @Test
    public void 보드게임_이미지_순서_확인() {
        // given

        // when
        List<Boardgame> all = boardgameRepository.findAll();

        // then
        for (Boardgame each : all) {
            System.out.println(each.getKorName() + " : [");
            for(Images image : each.getImages()){
                System.out.println(image.getPath() + ", ");
            }
            System.out.println("]");
        }

    }

    @Test
    public void 추천리스트_테스트() {
        // given
        em.flush(); em.clear();

        // when
        Optional<Recommand> opt_find = recommandRepository.findByTitle("3인 추천 게임");
        if (!opt_find.isPresent())
            fail();
        Recommand find = opt_find.get();

        // then
        System.out.print(find.getTitle() + ": ");
        for(BoardRecomRT rtData :find.getBoardgames()){
            System.out.print(rtData.getBoardgame().getKorName() + ", ");
        }
    }

    @Test
    public void 카테고리_테스트() {
        // given

        // when
        List<String> allCategoryKorName = categoryRepository.findAllCategoryKorName();

        // then
        if(allCategoryKorName.size() <= 0)
            fail("카테고리 없음");

        PageRequest pr = PageRequest.of(0, 20);

        for (String each : allCategoryKorName) {
            System.out.print("카테고리 [" + each + "] 내 게임들 : ");
            for(BoardgameListDto bgInCt : boardgameRepository.getBoardgameInCategory(each, pr)){
                System.out.print(bgInCt.getKorName() + ", ");
            }
            System.out.println();
        }
    }

    @Test
    public void 매커니즘_테스트() {
        // given

        // when
        List<String> allMechanismKorName = mechanismRepository.findAllMechanismKorName();
        System.out.println(allMechanismKorName.toString());

        PageRequest pr = PageRequest.of(0, 20);
        Page<BoardgameListDto> result = boardgameRepository.getBoardgameInMechanism("엔진", pr);

        for (BoardgameListDto each : result) {
            System.out.println(each);
        }
        // then

    }

    @Test
    public void 검색_테스트() {
        // given
        BoardgameFilter boardgameFilter = new BoardgameFilter();
        boardgameFilter.addCategoryName("경제", "산업");
        boardgameFilter.addMechanismsName("엔진", "경쟁");
        boardgameFilter.setInnerName("마스");

        // when
        PageRequest pr = PageRequest.of(0, 20);
        Page<BoardgameListDto> boardgameListDtos = searchRepository.searchBoardgame(boardgameFilter, pr);

        // then
        for (BoardgameListDto each : boardgameListDtos) {
            System.out.println(each.toString());
        }
    }
}