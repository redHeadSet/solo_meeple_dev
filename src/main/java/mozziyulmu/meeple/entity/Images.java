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

    @OneToOne(mappedBy = "user_profile_image")
    private User user;

    // ====================================================================
    public Images(String path, Boardgame boardgame) {
        this.path = path;
        this.boardgame = boardgame;
    }

    public Images(String path, User user) {
        this.path = path;
        this.user = user;
    }
}
