package mozziyulmu.meeple.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseUserData;
import mozziyulmu.meeple.entity.Relation.BoardPost.BoardPostRT;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseUserData {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @NotNull
    private String title;
    private String comment;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Images> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<BoardPostRT> relate_boardgames = new ArrayList<>();

    // ==========================================================
    public Post(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addRelateBoardgame(Boardgame boardgame) {
        relate_boardgames.add(new BoardPostRT(boardgame, this));
    }

    public void addImage(Images images) {
        this.images.add(images);
    }
}
