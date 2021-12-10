package mozziyulmu.meeple.entity.Relation.BoardUser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.User;

import javax.persistence.*;

// Boardgame - User Relation Table
@Entity
@Getter
@Table(name = "boardgame_user_rt")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "list_type")
public abstract class BoardgameUserRT extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "bg_u_r_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardgame_id")
    private Boardgame boardgame;

    public BoardgameUserRT(User user, Boardgame boardgame){
        this.user = user;
        this.boardgame = boardgame;
    }
}
