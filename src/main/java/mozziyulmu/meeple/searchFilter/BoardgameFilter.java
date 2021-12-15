package mozziyulmu.meeple.searchFilter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.Category;
import mozziyulmu.meeple.entity.Mechanism;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardgameFilter {
    private String innerName;
    private List<String> mechanismsName = new ArrayList<>();
    private List<String> categoriesName = new ArrayList<>();

    public BoardgameFilter setInnerName(String innerName) {
        this.innerName = innerName;
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
