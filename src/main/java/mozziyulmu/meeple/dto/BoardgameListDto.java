package mozziyulmu.meeple.dto;

import lombok.Getter;
import mozziyulmu.meeple.entity.Boardgame;

@Getter
public class BoardgameListDto {
    private Long id;
    private String korName;
    private String repImagePath;   // 이미지를 어떻게 전달해주지?
    private int minPlayer;
    private int maxPlayer;
    private Double geekRating;
    private String lineComment;
    private String viewCategory;

    // ================================================
    public BoardgameListDto(Boardgame boardgame) {
        id = boardgame.getId();
        korName = boardgame.getKorName();
        repImagePath = boardgame.getRepImagePath();

        minPlayer = boardgame.getMinPlayer();
        maxPlayer = boardgame.getMaxPlayer();

        geekRating = boardgame.getGeekRating();
        lineComment = boardgame.getLineComment();

        viewCategory = boardgame.getPrtRepKorCategory();
    }

    @Override
    public String toString() {
        return korName + "[" + minPlayer + "~" + maxPlayer + "][RT:" + geekRating.doubleValue()
                + "] - " + viewCategory + ":" + lineComment;
    }
}
