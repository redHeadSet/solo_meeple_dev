package mozziyulmu.meeple.scheduler;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.ImagesRepository;
import mozziyulmu.meeple.Repository.NewsRepository;
import mozziyulmu.meeple.entity.Images;
import mozziyulmu.meeple.entity.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrawlingNews {
    private final NewsRepository newsRepository;
    private final ImagesRepository imagesRepository;

//    @Scheduled(cron = "0 0 14 * * 1-5")
    public void crawlingNews_chedule() {
        try {
            crawlingNews_KoreaBoardgames();
            crawlingNews_PopcornGames();
        } catch (Exception e){
            String message = e.getMessage();
            e.printStackTrace();
        }
    }

    private boolean isDupNews(String title, String url){
        return newsRepository.findByTitleAndUrl(title, url).isPresent() ? true : false;
    }

    private Images setCompanyImage(String imagePath) {
        Optional<Images> dbImage = imagesRepository.findByPath(imagePath);
        return dbImage.orElseGet(null);
    }

    private void crawlingNews_KoreaBoardgames() throws Exception {
        String imagePath = BoardgameCompany.KOREA_BOARDGAMES.getRepImagePath();
        Images companyImage = setCompanyImage(imagePath);

        String crawlingBaseUrl = "https://www.divedice.com/board/";
        String crawlingUrl = crawlingBaseUrl + "?db=basic_1";
        Document document = Jsoup.connect(crawlingUrl).get();
        Elements newsList = document.getElementsByClass("announcement");

        for(Element eachPost : newsList){
//            String image_path = eachPost.getElementsByClass("img").get(0)
//                    .getElementsByTag("img")
//                    .attr("src");
            String write_date = eachPost.getElementsByClass("date").get(0).text();
            write_date += " 00:00:00";
            LocalDateTime writeTime = LocalDateTime.parse(write_date,
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Element link = eachPost.getElementsByTag("a").get(0);
            String url = crawlingBaseUrl + link.attr("href");
            String title = link.text();

            if( ! isDupNews(title, url)){
                News news = new News(title)
                                .setUrl(url)
                                .setWriteTime(writeTime);
                if (companyImage != null)
                    news.setCompanyImage(companyImage);
                else
                    news.setCompanyImage(imagePath);
                newsRepository.save(news);
            }
        }
    }

    private void crawlingNews_PopcornGames() throws Exception {
        String imagePath = BoardgameCompany.POPCORN_GAMES.getRepImagePath();
        Images companyImage = setCompanyImage(imagePath);

        String crawlingUrl = "https://www.popcone.co.kr/board/list.php?bdId=bdfree";
        String crawlingPageUrl = "https://www.popcone.co.kr/board/view.php?&bdId=bdfree&sno=";
        Document document = Jsoup.connect(crawlingUrl).get();
        Elements newsList = document.getElementsByClass("board_tit");

        for (Element eachNews : newsList){
            Element parent = eachNews.parent();

            // 공지사항 제외
            if ((parent.child(0).children().size() > 0)
                    && (parent.child(0).child(0).attr("alt").equals("공지")))
                continue;

            String title = eachNews.getElementsByTag("strong").get(0).text();
            String url = crawlingPageUrl + parent.attr("data-sno");
            String writeTimeDate = parent.child(2).text();
            LocalDateTime writeTime = LocalDateTime.parse(writeTimeDate + " 00:00:00",
                    DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));

            if(!isDupNews(title, url)){
                News news = new News(title)
                        .setUrl(url)
                        .setWriteTime(writeTime);

                if(companyImage != null)
                    news.setCompanyImage(companyImage);
                else
                    news.setCompanyImage(imagePath);
                newsRepository.save(news);
            }
        }
    }
}
