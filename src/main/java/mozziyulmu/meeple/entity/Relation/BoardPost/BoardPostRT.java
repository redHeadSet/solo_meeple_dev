package mozziyulmu.meeple.entity.Relation.BoardPost;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Post;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "boardgame_post_rt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPostRT extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "bg_p_r_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "boardgame_id")
    private Boardgame boardgame;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public BoardPostRT(Boardgame boardgame, Post post) {
        this.boardgame = boardgame;
        this.post = post;
    }
}
