package mozziyulmu.meeple.dto;

import lombok.Getter;
import lombok.ToString;
import mozziyulmu.meeple.entity.Boardgame;

@Getter
@ToString(of = {
        "korName", "minPlayer", "maxPlayer", "geekRating", "viewCategory", "lineComment"
})
public class BoardgameListDto {
    private Long id;
    private String korName;
    private String repImagePath;   // 이미지를 어떻게 전달해주지?
    private String difficulty;
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

        switch (boardgame.getDifficulty()){
            case EASY:      this.difficulty = "쉬움";     break;
            case MIDDLE:    this.difficulty = "보통";     break;
            case HARD:      this.difficulty = "어려움";    break;
            case MASTER:    this.difficulty = "마스터";    break;
            default:        this.difficulty = "알 수 없음";
        }
    }
}
