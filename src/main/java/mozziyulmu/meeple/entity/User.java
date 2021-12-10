package mozziyulmu.meeple.entity;

import com.sun.istack.NotNull;
import lombok.*;
import mozziyulmu.meeple.entity.Relation.BoardUser.EvaluateBoardgames;
import mozziyulmu.meeple.entity.Relation.BoardUser.InterestBoardgames;
import mozziyulmu.meeple.entity.Relation.BoardUser.OwnBoardgames;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"e_mail", "password", "nick_name"})
public class User{
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String e_mail;
    @NotNull
    private String password;
    @NotNull
    private String nick_name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<OwnBoardgames> ownBoardgames = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<InterestBoardgames> interestBoardgames = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<EvaluateBoardgames> evaluateBoardgames = new ArrayList<>();

    // =================================================================================
    public User(String e_mail, String password, String nick_name) {
        this.e_mail = e_mail;
        this.password = password;
        this.nick_name = nick_name;
    }

    // 사용자가 보드게임 각각을 추가 가능
    // 가지고 있는 보드게임
    public void addOwnBoardgame(Boardgame boardgame) {
        ownBoardgames.add(new OwnBoardgames(this, boardgame));
    }

    // 흥미있는 보드게임
    public void addInterestBoardgame(Boardgame boardgame) {
        interestBoardgames.add(new InterestBoardgames(this, boardgame));
    }

    // 평가한 보드게임
    public void addEvaluateBoardgame(Boardgame boardgame) {
        evaluateBoardgames.add(new EvaluateBoardgames(this, boardgame));
    }
}
