package mozziyulmu.meeple.scheduler.jsonForm;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParseGeekPublisher {
    @Data
    public class Images{
        String thumb;
        String micro;
        String square;
        String squarefit;
        String tallthumb;
        String previewthumb;
        String square200;
    }

    @Data
    public class Item{
        String usersrated;
        String average;
        String avgweight;
        String numowned;
        String numprevowned;
        String numtrading;
        String numwanting;
        String numwish;
        String numcomments;
        String yearpublished;
        String rank;
        String name;
        String postdate;
        String linkid;
        String linktype;
        String objecttype;
        String objectid;
        String itemstate;
        String rep_imageid;
        String subtype;
//        String links;
        String href;
        Images images;
    }

    @Data
    public class Config{
        int numitems;
    }

    public List<Item> items = new ArrayList<>();
//    itemdata
//    linkdata
    public Config config;
}


