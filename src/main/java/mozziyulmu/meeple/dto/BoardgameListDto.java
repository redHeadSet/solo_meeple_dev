package mozziyulmu.meeple.dto;

import lombok.Getter;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Images;
import mozziyulmu.meeple.entity.Relation.BoardCategory.BoardCateRT;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardgameListDto {
    private Long id;
    private String korName;
    private String image;   // 이미지를 어떻게 전달해주지?
    private int minPlayer;
    private int maxPlayer;
    private Double geekRating;
    private String lineComment;
    private List<String> viewCategory = new ArrayList<>();

    // ================================================
    public BoardgameListDto(Boardgame boardgame) {
        id = boardgame.getId();
        korName = boardgame.getKorName();
        List<Images> images = boardgame.getImages();
        if(images.size() > 0)
            image = images.get(0).getPath();

        minPlayer = boardgame.getMinPlayer();
        maxPlayer = boardgame.getMaxPlayer();

        geekRating = boardgame.getGeekRating();
        lineComment = boardgame.getLineComment();

//        for (BoardCateRT each : boardgame.getCategorys())
//            viewCategory.add(each.getCategory().getKorName());
    }

    public void setViewCategory(List<String> viewCategory) {
        this.viewCategory = viewCategory;
    }

    @Override
    public String toString() {
        return korName + "[" + minPlayer + "~" + maxPlayer + "][RT:" + geekRating.doubleValue()
                + "] - " + viewCategory.toString() + ":" + lineComment;
    }
}
