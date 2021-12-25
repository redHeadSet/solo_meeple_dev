package mozziyulmu.meeple.dto;

import lombok.Getter;
import mozziyulmu.meeple.entity.Boardgame;

import java.util.List;

@Getter
public class BoardgameDetailDto {
    private Long id;
    private String korName;
    private String engName;
    private String lineComment;
    private int publishedYear;

    private int minPlayer;
    private int maxPlayer;
    private int age;
    private int PlayingTime;

    private String difficulty;
    private String publisherName;

    private Double geekRating;
    private String geekLink;

    private List<String> mechanisms;
    private List<String> categories;

    public BoardgameDetailDto(Boardgame boardgame) {
        this.id = boardgame.getId();
        this.korName = boardgame.getKorName();
        this.engName = boardgame.getEngName();
        this.lineComment = boardgame.getLineComment();
        this.publishedYear = boardgame.getPublishedYear();

        this.minPlayer = boardgame.getMinPlayer();
        this.maxPlayer = boardgame.getMaxPlayer();
        this.age = boardgame.getAge();
        this.PlayingTime = boardgame.getMinPlayingMinute();

        switch (boardgame.getDifficulty()){
            case EASY:      this.difficulty = "쉬움";     break;
            case MIDDLE:    this.difficulty = "보통";     break;
            case HARD:      this.difficulty = "어려움";    break;
            case MASTER:    this.difficulty = "마스터";    break;
            default:        this.difficulty = "알 수 없음";
        }

        this.publisherName = boardgame.getPublisher().getKorName();

        this.geekRating = boardgame.getGeekRating();
        this.geekLink = boardgame.getGeekLink();
    }

    public BoardgameDetailDto setMechanisms(List<String> mechanisms) {
        this.mechanisms = mechanisms;
        return this;
    }

    public BoardgameDetailDto setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }
}
