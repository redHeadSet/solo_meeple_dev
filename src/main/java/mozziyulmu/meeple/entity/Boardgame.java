package mozziyulmu.meeple.entity;

import lombok.*;
import mozziyulmu.meeple.entity.BaseEntity.BaseUserData;
import mozziyulmu.meeple.entity.Relation.BoardMechanism.BoardMechaRT;
import mozziyulmu.meeple.entity.Relation.BoardUser.BoardgameUserRT;
import mozziyulmu.meeple.entity.Relation.BoardUser.OwnBoardgames;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name_kor", "name_eng"})
public class Boardgame extends BaseUserData {
    @Id
    @GeneratedValue
    @Column(name = "boardgame_id")
    private Long id;

    @Column(name = "boardgame_kor_name")
    private String kor_name;
    @Column(name = "boardgame_eng_name")
    private String eng_name;

    private LocalDateTime published_date; // 출시일

    private int min_player;
    private int max_player;
    private int optimal_player; // 최적 게임 인원

    private int min_playing_minute;
    private int max_playing_minute;

    private int age; // 권장 나이

    @Enumerated(EnumType.STRING)
    private DifficultyGrade difficulty; // 내부 난이도

    private Long geek_id;       // 긱 고유 번호
    private double geek_rating; // 긱 레이팅
    private String geek_link;   // 긱 링크
    private double geek_weight; // 긱 웨이트

//    @OneToMany(mappedBy = "boardgame")
//    List<BoardMechaRT> mechanisms = new ArrayList<>();
//
//    public void initMechanism(Mechanism... inputMachanisms){
//        mechanisms.clear();
//        for (Mechanism eachMechanism : inputMachanisms){
//            addMechanism(eachMechanism);
//        }
//    }
//
//    public BoardMechaRT addMechanism(Mechanism mechanism){
//        BoardMechaRT boardMechaRT = new BoardMechaRT(this, mechanism);
//        boardMechaRT.addBoth();
//        return boardMechaRT;
//    }
}
