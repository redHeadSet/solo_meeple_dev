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
@Setter
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
    private String kor_name;
    @Column(name = "boardgame_eng_name")
    private String eng_name;

    private int published_year; // 출시년도

    private int min_player;
    private int max_player;
    private int optimal_player; // 최적 게임 인원

    private int min_playing_minute;
    private int max_playing_minute;

    private int age; // 권장 나이

    @Enumerated(EnumType.STRING)
    private DifficultyGrade difficulty; // 내부 난이도

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private Long geek_id;       // 긱 고유 번호
    private Double geek_rating; // 긱 레이팅
    private String geek_link;   // 긱 링크
    private Double geek_weight; // 긱 웨이트

    @OneToMany(mappedBy = "boardgame", cascade = CascadeType.ALL)
    List<BoardMechaRT> mechanisms = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame", cascade = CascadeType.ALL)
    List<BoardCateRT> categorys = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame", cascade = CascadeType.ALL)
    List<Images> images = new ArrayList<>();

    @OneToMany(mappedBy = "boardgame")
    List<BoardPostRT> relate_posts = new ArrayList<>();

    // ========================================================================
    public void addGameImage(Images image) {
        images.add(image);
    }

    // 퍼블리셔 등록 후, 보드게임에서 퍼블리셔 처리하면 자동 List add(cascade)
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    // 보드게임에서 매커니즘 추가 가능(cascade), 반대는 불가능
    public void initMechanism(Mechanism... inputMachanisms){
        mechanisms.clear();
        for (Mechanism eachMechanism : inputMachanisms){
            addMechanism(eachMechanism);
        }
    }

    public void addMechanism(Mechanism mechanism){
        mechanisms.add(new BoardMechaRT(this, mechanism));
    }

    // 위와 동일
    // 보드게임에서 카테고리 추가 가능(cascade), 반대는 불가능
    public void initCategorys(Category... inputCategorys){
        categorys.clear();
        for (Category eachCategory : inputCategorys){
            addCategory(eachCategory);
        }
    }

    public void addCategory(Category category){
        categorys.add(new BoardCateRT(this, category));
    }
}
