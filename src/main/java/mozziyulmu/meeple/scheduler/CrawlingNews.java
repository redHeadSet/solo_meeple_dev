package mozziyulmu.meeple.scheduler;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.NewsRepository;
import mozziyulmu.meeple.entity.Images;
import mozziyulmu.meeple.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CrawlingNews {
    private final NewsRepository newsRepository;

//    @Scheduled(cron = "0 0 14 * * 1-5")
    public void crawlingNews_chedule() {
        try {
            crawlingNews_KoreaBoardgames();
        } catch (Exception e){
            String message = e.getMessage();
            e.printStackTrace();
        }
    }

    public void crawlingNews_KoreaBoardgames() throws Exception {
        String crawlingBaseUrl = "https://www.divedice.com/board/";
        String crawlingUrl = crawlingBaseUrl + "?db=basic_1";
        Document document = Jsoup.connect(crawlingUrl).get();
        Elements newsList = document.getElementsByClass("announcement");
        for(Element eachPost : newsList){
            String image_path = eachPost.getElementsByClass("img").get(0)
                    .getElementsByTag("img")
                    .attr("src");
            String write_date = eachPost.getElementsByClass("date").get(0).text();
            write_date += " 00:00:00";
            LocalDateTime writeTime = LocalDateTime.parse(write_date,
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Element link = eachPost.getElementsByTag("a").get(0);
            String href = crawlingBaseUrl + link.attr("href");
            String post_title = link.text();

//            System.out.println(post_title);
//            System.out.println(image_path);
//            System.out.println(write_date);
//            System.out.println(href);
//            System.out.println();

            News news = new News(post_title)
                    .setUrl(href)
                    .setWriteTime(writeTime)
                    .setCompanyImage(StringUtils.hasText(image_path) ? image_path : "");

            newsRepository.save(news);
        }
    }
}
