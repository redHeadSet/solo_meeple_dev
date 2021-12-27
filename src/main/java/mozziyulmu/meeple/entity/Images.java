package mozziyulmu.meeple.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseUserData;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Images extends BaseUserData {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @NotNull
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardgame_id")
    private Boardgame boardgame;

    @OneToOne(mappedBy = "userProfileImage")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne(mappedBy = "repImages")
    private Recommand recommand;

    @OneToOne(mappedBy = "companyImage")
    private News news;

    // ====================================================================
    public Images(String path, Boardgame boardgame) {
        this.path = path;
        this.boardgame = boardgame;
    }

    public Images(String path, User user) {
        this.path = path;
        this.user = user;
    }

    public Images(String path, Post post) {
        this.path = path;
        this.post = post;
    }

    public Images(String path, Recommand recommand) {
        this.path = path;
        this.recommand = recommand;
    }

    public Images(String path, News news) {
        this.path = path;
        this.news = news;
    }

    public void updateImage(String path) {
        if(user == null)
            return;
        this.path = path;
    }
}
