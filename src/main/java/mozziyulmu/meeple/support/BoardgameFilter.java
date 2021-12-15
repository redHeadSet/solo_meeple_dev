package mozziyulmu.meeple.support;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.DifficultyGrade;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardgameFilter {
    private String innerKorName;
    private Integer players;
    private DifficultyGrade difficultyGrade;
    private List<String> mechanismsName = new ArrayList<>();
    private List<String> categoriesName = new ArrayList<>();

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

    public BoardgameFilter addMechanismsName(String... mechanismsName){
        for(String eachMechName : mechanismsName){
            this.mechanismsName.add(eachMechName);
        }
        return this;
    }

    public BoardgameFilter addCategoryName(String... categorysName){
        for(String eachCateName : categorysName){
            this.categoriesName.add(eachCateName);
        }
        return this;
    }
}
