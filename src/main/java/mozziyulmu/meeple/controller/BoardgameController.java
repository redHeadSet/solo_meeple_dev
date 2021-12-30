package mozziyulmu.meeple.controller;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.dto.*;
import mozziyulmu.meeple.service.BoardgameService;
import mozziyulmu.meeple.service.RecommandService;
import mozziyulmu.meeple.support.BoardgameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static mozziyulmu.meeple.entity.DifficultyGrade.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boardgames")
public class BoardgameController {
    private final BoardgameService boardgameService;
    private final RecommandService recommandService;

    @GetMapping("/basicList")
    public Page<BoardgameListDto> getBasicList(Pageable pageable) {
        return boardgameService.getBoardgameBasicList(pageable);
    }

    @GetMapping("/detail/{boardgameId}")
    public BoardgameDetailDto getBoardgameDetail(@PathVariable("boardgameId") String boardgameId) {
        try {
            return boardgameService.getBoardgameDetail(Long.parseLong(boardgameId));
        } catch (Exception e) {
            System.out.println("boardgame detail input : " + boardgameId);
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/recommandList")
    public List<RecommandListDto> getRecommandList() {
        return recommandService.getRecommandList();
    }

    @GetMapping("/recommand/{recommandId}")
    public RecommandDto getRecommandDetail(@PathVariable("recommandId") String recommandId) {
        try {
            return recommandService.getRecommandData(Long.parseLong(recommandId));
        } catch (Exception e) {
            System.out.println("recommand detail input : " + recommandId);
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/mechList")
    public List<MechListDto> getMechNames() {
        return boardgameService.getAllMechanisms();
    }

    @GetMapping("/mech/{mechId}")
    public Page<BoardgameListDto> getBoardgameInMech(@PathVariable("mechId") String mechId, Pageable pageable){
        try {
            return boardgameService.getBoardgameListInMechanism(Long.parseLong(mechId), pageable);
        }catch (Exception e){
            System.out.println("mech detail input : " + mechId);
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/cateList")
    public List<CateListDto> getCateNames() {
        return boardgameService.getAllCategories();
    }

    @GetMapping("/cate/{cateId}")
    public Page<BoardgameListDto> getBoardgameInCate(@PathVariable("cateId") String cateId, Pageable pageable){
        try {
            return boardgameService.getBoardgameListInCategory(Long.parseLong(cateId), pageable);
        }catch (Exception e){
            System.out.println("cate detail input : " + cateId);
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/searchInit")
    public SearchReqList searchInit(){
        return boardgameService.initSearchFilter();
    }

    @GetMapping("/search")
    public Page<BoardgameListDto> searchBoardgame(HttpServletRequest request, Pageable pageable) {
        BoardgameFilter boardgameFilter = new BoardgameFilter();
        Map<String, String[]> params = request.getParameterMap();

        if(params.containsKey("innerKorName"))
            boardgameFilter.setInnerKorName(params.get("innerKorName")[0]);
        if(params.containsKey("players")){
            try {
                boardgameFilter.setPlayers(Integer.parseInt(params.get("players")[0]));
            }catch (Exception e){
                System.out.println("search mapping players : " + params.get("players"));
                e.printStackTrace();
            }
        }
        if(params.containsKey("difficultyGrade")){
            switch (params.get("difficultyGrade")[0]){
                case "쉬움" : boardgameFilter.setDifficulty(EASY);    break;
                case "보통" : boardgameFilter.setDifficulty(MIDDLE);  break;
                case "어려움" : boardgameFilter.setDifficulty(HARD);   break;
                case "마스터" : boardgameFilter.setDifficulty(MASTER); break;
            }
        }

        // 매커니즘 리스트
        if (params.containsKey("mechanismIds"))
            boardgameFilter.addMechanisms(
                    Arrays.stream(params.get("mechanismIds"))
                            .map(s -> Long.parseLong(s)).collect(Collectors.toList())
            );

        // 카테고리 리스트
        if (params.containsKey("categoryIds"))
            boardgameFilter.addCategory(
                    Arrays.stream(params.get("categoryIds"))
                            .map(s -> Long.parseLong(s)).collect(Collectors.toList())
            );

        return boardgameService.searchBoardgame(boardgameFilter, pageable);
    }
}
