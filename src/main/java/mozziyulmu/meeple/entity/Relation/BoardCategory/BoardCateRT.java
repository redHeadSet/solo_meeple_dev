package mozziyulmu.meeple.entity.Relation.BoardCategory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Category;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "boardgame_category_rt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCateRT extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "bg_c_r_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardgame_id")
    Boardgame boardgame;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category category;

    public BoardCateRT(Boardgame boardgame, Category category) {
        this.boardgame = boardgame;
        this.category = category;
    }
}
