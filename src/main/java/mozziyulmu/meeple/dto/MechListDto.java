package mozziyulmu.meeple.dto;

import lombok.Data;
import mozziyulmu.meeple.entity.Mechanism;

@Data
public class MechListDto {
    Long id;
    String name;

    public MechListDto(Mechanism mechanism) {
        this.id = mechanism.getId();
        this.name = mechanism.getKorName();
    }
}
