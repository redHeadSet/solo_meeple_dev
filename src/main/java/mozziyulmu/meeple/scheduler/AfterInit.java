package mozziyulmu.meeple.scheduler;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.BoardgameRepository;
import mozziyulmu.meeple.Repository.RecommandRepository;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Recommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AfterInit {
    private final BoardgameRepository boardgameRepository;
    private final RecommandRepository recommandRepository;

    private void setBoardgameLineCommentByKorName(String korName, String comment) {
        Optional<Boardgame> boardgame = boardgameRepository.findByKorName(korName);
        if(boardgame.isPresent())
            boardgame.get().setLineComment(comment);
    }

    // 한 줄 코멘트 목록
    public void setBoardgameLineComment(){
        setBoardgameLineCommentByKorName("테라포밍 마스", "화성을 지구화 시켜보자");
    }

    // 기본 추천 목록 - 추가 필요
    public void setRecommandList() {
        // 값에 대한 null check는 initBoardgames 내에서 처리
        Boardgame terraforming_mars = boardgameRepository.findByKorName("테라포밍 마스").get();
        Boardgame dominion = boardgameRepository.findByKorName("도미니언").get();
        Boardgame seven_wonders_dual = boardgameRepository.findByKorName("7 원더스 대결").get();
        Boardgame arkham_horror = boardgameRepository.findByKorName("아컴 호러: 카드 게임").get();

        Recommand rec_3_party = new Recommand("3인 추천 게임")
                .setDesc("3인 추천 게임 리스트")
                .setImage("3인 대표 이미지")
                .initBoardgames(terraforming_mars, dominion);
        recommandRepository.save(rec_3_party);

        Recommand rec_2_party = new Recommand("2인 추천 게임")
                .setDesc("2인에서 하면 꿀잼")
                .setImage("2인 대표 이미지")
                .initBoardgames(arkham_horror, seven_wonders_dual);
        recommandRepository.save(rec_2_party);
    }
}
