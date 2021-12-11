package mozziyulmu.meeple.entity;

import mozziyulmu.meeple.entity.Relation.BoardCategory.BoardCateRT;
import mozziyulmu.meeple.entity.Relation.BoardMechanism.BoardMechaRT;
import mozziyulmu.meeple.entity.Relation.BoardPost.BoardPostRT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class UserTest {
    @Autowired
    public EntityManager em;
    Boardgame terraforming_mars;
    Boardgame gloom_haven;
    Boardgame brass_birmingham;
    Boardgame castles_of_burgundy;

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

    //    @BeforeEach
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

        terraforming_mars = new Boardgame("테라포밍 마스", "terraforming mars")
                        .setPublishedYear(2016)
                        .setPlayers(1, 5, 3)
                        .setPlayingTime(120, 120)
                        .setPublisher(kbg)
                        .setDifficulty(DifficultyGrade.MIDDLE)
                        .setGeekData(167791, 8.247, 3.24)
                        .initMechanism(mech1, mech2, mech3, mech4, mech5, mech6, mech7)
                        .initCategorys(ct1, ct2, ct3, ct4, ct5);
        em.persist(terraforming_mars);

        gloom_haven = new Boardgame("글룸 헤이븐", "Gloom Haven")
                        .setPublishedYear(2017)
                        .setPlayers(1, 4, 3)
                        .setDifficulty(DifficultyGrade.MIDDLE)
                        .setPublisher(kbg)
                        .setGeekData(174430, 8.515, 3.87)
                        .initMechanism(mech4, mech6, mech7, mech8, mech9, mech10, mech11)
                        .initCategorys(ct6, ct7, ct8, ct9, ct10);
        em.persist(gloom_haven);
        
        brass_birmingham = new Boardgame("브라스:버밍엄", "Brass:birmingham")
                        .setPublishedYear(2018)
                        .setPlayers(2, 4, 3)
                        .setDifficulty(DifficultyGrade.HARD)
                        .setPublisher(bm)
                        .setGeekData(224517, 8.416, 3.91)
                        .initMechanism(mech12, mech13, mech14, mech15)
                        .initCategorys(ct1, ct3, ct11);
        em.persist(brass_birmingham);
        
        castles_of_burgundy = new Boardgame("버건디의 성", "The castles of burgundy")
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
    public void 보드게임_유저_test() {
        // given
        Boardgame bg1 = new Boardgame("테포마", ""); em.persist(bg1);
        Boardgame bg2 = new Boardgame("니다벨리르", ""); em.persist(bg2);
        Boardgame bg3 = new Boardgame("팬거시", ""); em.persist(bg3);
        Boardgame bg4 = new Boardgame("아딱", ""); em.persist(bg4);

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
        Boardgame bg1 = new Boardgame("테포마", ""); em.persist(bg1);
        Boardgame bg2 = new Boardgame("팬거시", ""); em.persist(bg2);

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
        System.out.print(boardgame1.getKorName() + "의 매커니즘 : ");
        for (BoardMechaRT each : boardgame1.getMechanisms()) {
            System.out.print(each.getMechanism().getKorName() + ", ");
        }
        System.out.println();

        Assertions.assertThat(boardgame2.getMechanisms().size()).isEqualTo(3);
        System.out.print(boardgame2.getKorName() + "의 매커니즘 : ");
        for (BoardMechaRT each : boardgame2.getMechanisms()) {
            System.out.print(each.getMechanism().getKorName() + ", ");
        }
        System.out.println();
    }

    @Test
    public void 보드게임_카테고리_test() {
        // given
        Boardgame bg1 = new Boardgame("테포마", ""); em.persist(bg1);
        Boardgame bg2 = new Boardgame("아딱", ""); em.persist(bg2);

        Category ct1 = new Category("우주", "space"); em.persist(ct1);
        Category ct2 = new Category("괴물", "monster"); em.persist(ct2);
        bg1.initCategorys(ct1);
        bg2.initCategorys(ct2);

        Category ct3 = new Category("기업", "company"); em.persist(ct3);
        bg1.addCategory(ct3);

        Category ct4 = new Category("테스트 공통", "test"); em.persist(ct4);
        bg1.addCategory(ct4);
        bg2.addCategory(ct4);

        Long bg1Id = bg1.getId();
        Long bg2Id = bg2.getId();
        Long ct4Id = ct4.getId();

        em.flush(); em.clear();

        // when
        Boardgame boardgame1 = em.find(Boardgame.class, bg1Id);
        Boardgame boardgame2 = em.find(Boardgame.class, bg2Id);
        Category category = em.find(Category.class, ct4Id);

        // then
        Assertions.assertThat(category.getBoardgames().size()).isEqualTo(2);
        System.out.println(category.getKorName() + " 카테고리의 보드게임 총 " + category.getBoardgames().size() + "개");
        for (BoardCateRT each : category.getBoardgames()) {
            System.out.println("이름 : " + each.getBoardgame().getKorName());
        }

        Assertions.assertThat(boardgame1.getCategorys().size()).isEqualTo(3);
        Assertions.assertThat(boardgame2.getCategorys().size()).isEqualTo(2);
    }

    @Test
    public void 이미지_테스트() {
        // given
        User user = new User("email", "password", "nickname");
        user.setProfileImage(new Images("D:\\aaa.jpg", user));
        em.persist(user);
        Long userId = user.getId();

        Boardgame bg = new Boardgame("테포마", "");
        bg.addGameImage(new Images("C:\\terraforming_mars1.jpg", bg));
        bg.addGameImage(new Images("C:\\add\\terraforming_mars2.jpg", bg));
        bg.addGameImage(new Images("C:\\remove\\terraforming_mars3.jpg", bg));
        em.persist(bg);
        Long bgid = bg.getId();

        em.flush(); em.clear();

        // when
        User user1 = em.find(User.class, userId);
        Boardgame boardgame = em.find(Boardgame.class, bgid);

        // then
        String path = user1.getUserProfileImage().getPath();
        Assertions.assertThat(path).isEqualTo("D:\\aaa.jpg");
        System.out.println(user1.getNickName() + "님의 이미지는 " + path + "에 있다");

        List<Images> images = boardgame.getImages();
        Assertions.assertThat(images.size()).isEqualTo(3);
        System.out.println("이미지 총 " + images.size() + "장");
        for(Images each: images){
            System.out.println(each.getPath());
        }
    }

    @Test
    public void 보드게임_퍼블리셔_test() {
        // given
        Publisher publisher = new Publisher("코리아 보드게임즈", "korea boardgames");
        em.persist(publisher);
        Long pub_id = publisher.getId();

        Boardgame bg1 = new Boardgame("테포마", "");
        bg1.setPublisher(publisher);
        em.persist(bg1);
        Boardgame bg2 = new Boardgame("아딱", "");
        bg2.setPublisher(publisher);
        em.persist(bg2);
        Boardgame bg3 = new Boardgame("돌팔이약장수", "");
        bg3.setPublisher(publisher);
        em.persist(bg3);

        em.flush();em.clear();

        // when
        Publisher find_pub = em.find(Publisher.class, pub_id);

        // then
        Assertions.assertThat(find_pub.getPublishBoardgames().size()).isEqualTo(3);
        for (Boardgame each : find_pub.getPublishBoardgames()) {
            Assertions.assertThat(each.getPublisher().getKorName()).isEqualTo("코리아 보드게임즈");
        }
    }

    @Test
    public void 보드게임_포스트_test() {
        // given
        setDefault();
        Post post = new Post("새 제목");
        post.setComment("dkgkgkgkgkgkgkgk");
        Images innerImage1 = new Images("post 이미지1", post);
        Images innerImage2 = new Images("post 이미지2", post);
        Images innerImage3 = new Images("post 이미지3", post);
        post.addImage(innerImage1);
        post.addImage(innerImage2);
        post.addImage(innerImage3);
        post.addRelateBoardgame(terraforming_mars);
        post.addRelateBoardgame(gloom_haven);
        em.persist(post);

        Long postId = post.getId();
        Long terraforming_marsId = terraforming_mars.getId();
        Long gloom_havenId = gloom_haven.getId();

        em.flush(); em.clear();

        // when
        Post finded_post = em.find(Post.class, postId);

        // then
        Assertions.assertThat(finded_post.getRelateBoardgames().size()).isEqualTo(2);
        for(BoardPostRT bprt : finded_post.getRelateBoardgames()){
            Long this_boardgame_id = bprt.getBoardgame().getId();
            if((this_boardgame_id != terraforming_marsId)
                && (this_boardgame_id != gloom_havenId)){
                Assertions.fail("아이디 불일치" + this_boardgame_id
                        + " vs [" + terraforming_marsId + " or " + gloom_havenId
                        + "]");
            }

            System.out.println(bprt.getBoardgame().getKorName() + "와 관계된 포스트 : "
                    + bprt.getBoardgame().getRelatePosts().size() + "개");
        }

    }
}