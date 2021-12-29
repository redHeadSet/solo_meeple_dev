package mozziyulmu.meeple.dto;

import lombok.Data;
import mozziyulmu.meeple.entity.Category;

@Data
public class CateListDto {
    Long id;
    String name;

    public CateListDto(Category category) {
        this.id = category.getId();
        this.name = category.getKorName();
    }
}
