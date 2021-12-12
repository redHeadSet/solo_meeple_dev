package mozziyulmu.meeple.entity.Relation.BoardRecom;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Recommand;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "boardgame_recommand_rt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRecomRT extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "bg_rm_r_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommand_id")
    Recommand recommand;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardgame_id")
    Boardgame boardgame;

    // ==================================================

    public BoardRecomRT(Recommand recommand, Boardgame boardgame) {
        this.recommand = recommand;
        this.boardgame = boardgame;
    }
}
