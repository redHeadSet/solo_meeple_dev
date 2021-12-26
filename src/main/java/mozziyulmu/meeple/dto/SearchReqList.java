package mozziyulmu.meeple.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SearchReqList {
    List<String> mechanisms = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    public SearchReqList setMechanisms(List<String> mechanisms) {
        this.mechanisms = mechanisms;
        return this;
    }

    public SearchReqList setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }
}
