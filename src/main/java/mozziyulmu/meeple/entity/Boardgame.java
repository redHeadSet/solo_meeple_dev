package mozziyulmu.meeple.entity;

import lombok.*;
import mozziyulmu.meeple.entity.BaseEntity.BaseUserData;
import mozziyulmu.meeple.entity.Relation.BoardCategory.BoardCateRT;
import mozziyulmu.meeple.entity.Relation.BoardMechanism.BoardMechaRT;
import mozziyulmu.meeple.entity.Relation.BoardPost.BoardPostRT;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name_kor", "name_eng"})
public class Boardgame extends BaseUserData {
    final static int MINIMUM_PLAYER  = 1;
    final static int INFINITE_PLAYER = 999;

    @Id
    @GeneratedValue
    @Column(name = "boardgame_id")
    private Long id;

    @Column(name = "boardgame_kor_name")
    private String korName;
    @Column(name = "boardgame_eng_name")
    private String engName;

    private String lineComment;

    private int publishedYear; // 출시년도

    private int minPlayer;
    private int maxPlayer;
    private int optimalPlayer; // 최적 게임 인원

    private int minPlayingMinute;
    private int maxPlayingMinute;

    private int age; // 권장 나이

    @Enumerated(EnumType.STRING)
    private DifficultyGrade difficulty; // 내부 난이도

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private Long geekId;       // 긱 고유 번호
    private Double geekRating; // 긱 레이팅
    private String geekLink;   // 긱 링크
    private Double geekWeight; // 긱 웨이트

    @OneToMany(mappedBy = "boardgame", cascade = CascadeType.ALL)
    List<BoardMechaRT> mechanisms = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame", cascade = CascadeType.ALL)
    List<BoardCateRT> categorys = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame", cascade = CascadeType.ALL)
    List<Images> images = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame")
    List<BoardPostRT> relatePosts = new ArrayList<>();

    // ========================================================================
    // Constructor & Builder

    public Boardgame(String korName, String engName) {
        this.korName = korName;
        this.engName = engName;
    }

    public Boardgame setLineComment(String lineComment) {
        this.lineComment = lineComment;
        return this;
    }

    public Boardgame setPublishedYear(int year) {
        this.publishedYear = year;
        return this;
    }

    public Boardgame setPlayers(int minPlayer, int maxPlayer, int optimalPlayer) {
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.optimalPlayer = optimalPlayer;
        return this;
    }

    public Boardgame setPlayingTime(int minPlayingMinute, int maxPlayingMinute){
        this.minPlayingMinute = minPlayingMinute;
        this.maxPlayingMinute = maxPlayingMinute;
        return this;
    }

    public Boardgame setAge(int age) {
        this.age = age;
        return this;
    }

    public Boardgame setDifficulty(DifficultyGrade difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Boardgame setGeekData(long geekId, double geekRating, double geekWeight) {
        this.geekId = Long.valueOf(geekId);
        this.geekRating = Double.valueOf(geekRating);
        this.geekWeight = Double.valueOf(geekWeight);
        this.geekLink = "https://boardgamegeek.com/boardgame/" + geekId;
        return this;
    }

    // 퍼블리셔 등록 후, 보드게임에서 퍼블리셔 처리하면 자동 List add(cascade)
    public Boardgame setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public Boardgame initMechanism(Mechanism... inputMachanisms){
        mechanisms.clear();
        for (Mechanism eachMechanism : inputMachanisms){
            addMechanism(eachMechanism);
        }
        return this;
    }

    public Boardgame initCategorys(Category... inputCategorys){
        categorys.clear();
        for (Category eachCategory : inputCategorys){
            addCategory(eachCategory);
        }
        return this;
    }

    // ========================================================================
    public void addGameImage(Images image) {
        images.add(image);
    }
    public void addGameImage(String path) { images.add(new Images(path, this)); }

    public void addMechanism(Mechanism mechanism){
        mechanisms.add(new BoardMechaRT(this, mechanism));
    }

    public void addCategory(Category category){
        categorys.add(new BoardCateRT(this, category));
    }
}
