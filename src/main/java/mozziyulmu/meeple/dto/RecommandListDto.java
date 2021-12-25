package mozziyulmu.meeple.dto;

import lombok.Getter;
import mozziyulmu.meeple.entity.Recommand;

@Getter
public class RecommandListDto {
    private Long id;
    private String title;
    private String imagePath;

    public RecommandListDto(Recommand recommand) {
        this.id = recommand.getId();
        this.title = recommand.getTitle();
        this.imagePath = recommand.getRepImages().getPath();
    }
}
