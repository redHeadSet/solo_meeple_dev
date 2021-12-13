package mozziyulmu.meeple.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {
    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    private String Title;
    private String text;
    private String url;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Images companyImage;

    public News(String title) {
        Title = title;
    }

    public News setText(String text){
        this.text = text;
        return this;
    }

    public News setUrl(String url) {
        this.url = url;
        return this;
    }

    public News setCompanyImage(String imagePath) {
        companyImage = new Images(imagePath, this);
        return this;
    }
}
