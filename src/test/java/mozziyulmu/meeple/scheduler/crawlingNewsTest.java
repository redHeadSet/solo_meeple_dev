package mozziyulmu.meeple.scheduler;

import mozziyulmu.meeple.Repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class crawlingNewsTest {
    @Autowired NewsRepository newsRepository;

    @Test
    @Commit
    public void test() {
        CrawlingNews crawlingNews = new CrawlingNews(newsRepository);
        crawlingNews.crawlingNews_chedule();
    }

}