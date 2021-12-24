package mozziyulmu.meeple.scheduler;

import mozziyulmu.meeple.Repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class schedulerTest {
    @Autowired NewsRepository newsRepository;
    @Autowired CrawlingBoardgameInfo crawlingBoardgameInfo;
    @Autowired InitPublishers initPublishers;

    @Test
    @Commit
    public void test() {
        CrawlingNews crawlingNews = new CrawlingNews(newsRepository);
        crawlingNews.crawlingNews_chedule();
    }

    @Test
    @Commit
    public void setBoardgameInfo() {
        initPublishers.setPublishers();
        crawlingBoardgameInfo.setCategory();
        crawlingBoardgameInfo.setMechanism();
        crawlingBoardgameInfo.getBoardgameInfo();
    }
}