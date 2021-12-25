package mozziyulmu.meeple.dto;

import lombok.Getter;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Recommand;

import java.util.List;

@Getter
public class RecommandDto {
    private String title;
    private String description;
    private String imagePath;
    private List<BoardgameListDto> boardgames;

    public RecommandDto(Recommand recommand) {
        this.title = recommand.getTitle();
        this.description = recommand.getDescription();
        this.imagePath = recommand.getRepImages().getPath();
    }

    public RecommandDto setBoardgames(List<BoardgameListDto> boardgames) {
        this.boardgames = boardgames;
        return this;
    }
}
