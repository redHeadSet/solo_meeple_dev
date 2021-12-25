package mozziyulmu.meeple.service;

import mozziyulmu.meeple.dto.RecommandDto;
import mozziyulmu.meeple.dto.RecommandListDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class ServiceTest {
    @Autowired RecommandService recommandService;

    // 테스트 전, scheduler 내 Init 처리

    @Test
    public void 추천서비스_테스트() {
        List<RecommandListDto> recommandList = recommandService.getRecommandList();
        Optional<RecommandDto> recommandData = recommandService.getRecommandData(recommandList.get(0).getId());
        System.out.println();
    }
}