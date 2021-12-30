package mozziyulmu.meeple.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SearchReqList {
    List<MechListDto> mechanisms = new ArrayList<>();
    List<CateListDto> categories = new ArrayList<>();

    public SearchReqList setMechanisms(List<MechListDto> mechanisms) {
        this.mechanisms = mechanisms;
        return this;
    }

    public SearchReqList setCategories(List<CateListDto> categories) {
        this.categories = categories;
        return this;
    }
}
