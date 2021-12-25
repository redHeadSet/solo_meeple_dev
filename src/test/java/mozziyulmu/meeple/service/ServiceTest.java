package mozziyulmu.meeple.service;

import mozziyulmu.meeple.dto.BoardgameDetailDto;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.dto.RecommandDto;
import mozziyulmu.meeple.dto.RecommandListDto;
import mozziyulmu.meeple.support.BoardgameFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class ServiceTest {
    @Autowired RecommandService recommandService;
    @Autowired BoardgameService boardgameService;

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
}