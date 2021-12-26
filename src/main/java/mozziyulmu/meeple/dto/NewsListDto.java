package mozziyulmu.meeple.dto;

import lombok.Getter;
import mozziyulmu.meeple.entity.News;

import java.time.LocalDateTime;

@Getter
public class NewsListDto {
    private String title;
    private String url;
    private String imagePath;
    private LocalDateTime writeTime;

    public NewsListDto(News news) {
        this.title = news.getTitle();
        this.url = news.getUrl();
        this.imagePath = news.getCompanyImage().getPath();
        this.writeTime = news.getWriteTime();
    }
}
