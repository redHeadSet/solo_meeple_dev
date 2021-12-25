package mozziyulmu.meeple.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class schedulerTest {
    @Autowired CrawlingNews crawlingNews;
    @Autowired CrawlingBoardgameInfo crawlingBoardgameInfo;
    @Autowired BeforeInit beforeInit;
    @Autowired AfterInit afterInit;

    @Test
    @Commit
    public void test() {
        crawlingNews.crawlingNews_chedule();
    }

    @Test
    @Commit
    public void setBoardgameInfo() {
        beforeInit.setPublishers();
        beforeInit.setCategory();
        beforeInit.setMechanism();
        crawlingBoardgameInfo.getBoardgameInfo();
        afterInit.setBoardgameLineComment();
        afterInit.setRecommandList();
    }
}