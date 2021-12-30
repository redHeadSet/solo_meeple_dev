package mozziyulmu.meeple.support;

import lombok.Getter;
import mozziyulmu.meeple.entity.DifficultyGrade;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardgameFilter {
    private String innerKorName;
    private Integer players;
    private DifficultyGrade difficultyGrade;
    private List<Long> mechanismIds = new ArrayList<>();
    private List<Long> categoryIds = new ArrayList<>();

    public BoardgameFilter() {
        players = -1;
    }

    public BoardgameFilter setInnerKorName(String innerKorName) {
        this.innerKorName = innerKorName;
        return this;
    }

    public BoardgameFilter setPlayers(int players){
        this.players = players;
        return this;
    }

    public BoardgameFilter setDifficulty(DifficultyGrade difficultyGrade){
        this.difficultyGrade = difficultyGrade;
        return this;
    }

    public BoardgameFilter addMechanisms(List<Long> mechanisms){
        for(Long eachMech : mechanisms){
            this.mechanismIds.add(eachMech);
        }
        return this;
    }

    public BoardgameFilter addCategory(List<Long> categorys){
        for(Long eachCate : categorys){
            this.categoryIds.add(eachCate);
        }
        return this;
    }
}
