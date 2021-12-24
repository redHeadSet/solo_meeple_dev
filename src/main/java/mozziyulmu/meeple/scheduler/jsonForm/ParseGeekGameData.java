package mozziyulmu.meeple.scheduler.jsonForm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Publisher;
import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParseGeekGameData {
    @Data
    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StringAttrValue {
        @XmlAttribute(name = "value")
        private String value;
    }

    @Data
    @XmlRootElement(name = "name")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NameData{
        @XmlAttribute(name = "type")
        private String type;
        @XmlAttribute(name = "sortindex")
        private String sortindex;
        @XmlAttribute(name = "value")
        private String value;
    }

    @Data
    @XmlRootElement(name = "ratings")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ratings{
        @XmlElement(name = "average")
        private StringAttrValue average;
        @XmlElement(name = "averageweight")
        private StringAttrValue averageweight;
    }

    @Data
    @XmlRootElement(name = "statistics")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Statistics{
        @XmlElement(name = "ratings")
        private Ratings ratings;
    }

    @Data
    @XmlRootElement(name = "link")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LinkData{
        @XmlAttribute(name = "type")
        private String type;
        // boardgamecategory = 카테고리
        // boardgamemechanic = 매커니즘
        @XmlAttribute(name = "id")
        private String id;
        @XmlAttribute(name = "value")
        private String value;
    }

    @Data
    @XmlRootElement(name = "item")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        @XmlAttribute(name = "type")
        private String type;

        @XmlAttribute(name = "id")
        private String id;

        @XmlElement(name = "thumbnail")
        private String thumbnail;

        @XmlElement(name = "image")
        private String image;

        @XmlElement(name = "name")
        private List<NameData> name = new ArrayList<>();

        @XmlElement(name = "yearpublished")
        private StringAttrValue yearpublished;

        @XmlElement(name = "minplayers")
        private StringAttrValue minplayers;

        @XmlElement(name = "maxplayers")
        private StringAttrValue maxplayers;

        @XmlElement(name = "playingtime")
        private StringAttrValue playingtime;

        @XmlElement(name = "minage")
        private StringAttrValue minage;

        @XmlElement(name = "statistics")
        private Statistics statistics;

        @XmlElement(name = "link")
        private List<LinkData> link = new ArrayList<>();
    }

    @XmlElement(name = "item")
    private List<Item> item = new ArrayList<>();
}
