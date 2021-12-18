package mozziyulmu.meeple.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseTimeData;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News extends BaseTimeData {
    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    private String title;
//    private String text;
    private String url;

    private LocalDateTime writeTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Images companyImage;

    public News(String title) {
        this.title = title;
    }

//    public News setText(String text){
//        this.text = text;
//        return this;
//    }

    public News setUrl(String url) {
        this.url = url;
        return this;
    }

    public News setCompanyImage(String imagePath) {
        companyImage = new Images(imagePath, this);
        return this;
    }

    public News setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
        return this;
    }
}
