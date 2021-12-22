package mozziyulmu.meeple.scheduler.jsonForm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParseGeekGameData {
    @Data
    public class Items{
        List<Item> item = new ArrayList<>();
    }

    @Data
    public class NameData{
        int sortindex;
        String type;
        String value;
    }

    @Data
    public class Item{
        String thumbnail;
        String image;
        List<NameData> name = new ArrayList<>();
    }

    public Items items;
}
