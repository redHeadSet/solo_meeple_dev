package mozziyulmu.meeple.entity.Relation.BoardMechanism;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Mechanism;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "boardgame_mechanism_rt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardMechaRT extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "bg_m_r_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardgame_id")
    Boardgame boardgame;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mechanism_id")
    Mechanism mechanism;

    public BoardMechaRT(Boardgame boardgame, Mechanism mechanism) {
        this.boardgame = boardgame;
        this.mechanism = mechanism;
    }
}
