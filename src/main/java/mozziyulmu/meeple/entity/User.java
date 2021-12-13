package mozziyulmu.meeple.entity;

import com.sun.istack.NotNull;
import lombok.*;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import mozziyulmu.meeple.entity.Relation.BoardUser.EvaluateBoardgames;
import mozziyulmu.meeple.entity.Relation.BoardUser.InterestBoardgames;
import mozziyulmu.meeple.entity.Relation.BoardUser.OwnBoardgames;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"eMail", "password", "nickName"})
public class User extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String eMail;
    @NotNull
    private String password;
    @NotNull
    private String nickName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Images userProfileImage;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<OwnBoardgames> ownBoardgames = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<InterestBoardgames> interestBoardgames = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<EvaluateBoardgames> evaluateBoardgames = new ArrayList<>();

    // !! 내가 작성한 Post 목록을 볼 수 있어야 함
    // Auditing - BaseUserData 값을 User 값으로 변경 가능한지??

    // =================================================================================
    public User(String e_mail, String password, String nick_name) {
        this.eMail = e_mail;
        this.password = password;
        this.nickName = nick_name;
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

    public void setProfileImage(Images images) {
        userProfileImage = images;
    }
}
