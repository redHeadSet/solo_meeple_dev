package mozziyulmu.meeple.service;

import mozziyulmu.meeple.dto.*;
import mozziyulmu.meeple.support.BoardgameFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class ServiceTest {
    @Autowired RecommandService recommandService;
    @Autowired BoardgameService boardgameService;
    @Autowired UserService userService;
    @Autowired NewsService newsService;

    // 테스트 전, scheduler 내 Init 처리

    @Test
    public void 추천서비스_테스트() {
        List<RecommandListDto> recommandList = recommandService.getRecommandList();
        Optional<RecommandDto> recommandData = recommandService.getRecommandData(recommandList.get(0).getId());
        System.out.println();
    }

    @Test
    public void 보드게임_테스트() {
        BoardgameFilter boardgameFilter = new BoardgameFilter()
                .setInnerKorName("마스");
        PageRequest pageRequest = PageRequest.of(0, 100);
        Page<BoardgameListDto> boardgameListDtos = boardgameService.searchBoardgame(boardgameFilter, pageRequest);

        Optional<BoardgameDetailDto> boardgameDetail = boardgameService.getBoardgameDetail(boardgameListDtos.getContent().get(0).getId());
        System.out.println();
    }

    @Test
    public void 뉴스_테스트() {
        for (NewsListDto eachNews : newsService.getNewsList()) {
            System.out.println(eachNews.getTitle());
        }
    }

    @Test
    @Commit
    public void 사용자_테스트() {
        // given
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail("stikfascube@gmail.com");
        userUpdateDto.setPassword("1234");
        userUpdateDto.setNickname("두두두");

        userService.update(userUpdateDto);
        // when

        // then

    }
}