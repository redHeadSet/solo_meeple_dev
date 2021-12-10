package mozziyulmu.meeple.entity;

import lombok.*;
import mozziyulmu.meeple.entity.Relation.BoardUser.OwnBoardgames;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"e_mail"})
public class User{
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String e_mail;
    private String password;
    private String nick_name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<OwnBoardgames> ownBoardgames = new ArrayList<>();

    // ========================================================
    public OwnBoardgames addOwnBoardgame(Boardgame boardgame) {
        OwnBoardgames newOwnBgData = new OwnBoardgames(this, boardgame);
        ownBoardgames.add(newOwnBgData);
        return newOwnBgData;
    }
}
