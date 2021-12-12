package mozziyulmu.meeple.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import mozziyulmu.meeple.entity.Relation.BoardRecom.BoardRecomRT;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recommand extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "recommand_id")
    private Long id;

    @NotNull
    @Column(name = "recommand_title")
    private String title;
    @Column(name = "recommand_description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Images repImages;

    @OneToMany(mappedBy = "recommand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BoardRecomRT> boardgames = new ArrayList<>();

    // ====================================================================

    public Recommand(String title) {
        this.title = title;
    }

    public Recommand setDesc(String description) {
        this.description = description;
        return this;
    }

    public Recommand setImage(String imagePath){
        this.repImages = new Images(imagePath, this);
        return this;
    }

    public Recommand initBoardgames(Boardgame... addBoardgames){
        for (Boardgame each : addBoardgames)
            addBoardgame(each);
        return this;
    }

    public Recommand addBoardgame(Boardgame boardgame){
        boardgames.add(new BoardRecomRT(this, boardgame));
        return this;
    }
}