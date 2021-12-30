package mozziyulmu.meeple.service;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.BoardgameRepository;
import mozziyulmu.meeple.Repository.CategoryRepository;
import mozziyulmu.meeple.Repository.MechanismRepository;
import mozziyulmu.meeple.Repository.SearchRepository;
import mozziyulmu.meeple.dto.*;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.support.BoardgameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardgameService {
    private final BoardgameRepository boardgameRepository;
    private final SearchRepository searchRepository;
    private final MechanismRepository mechanismRepository;
    private final CategoryRepository categoryRepository;

    // 긱 레이팅 기준으로 하면 난이도나 순위랑은 너무 딴판인 데이터가 나옴... 개선 필요
    public Page<BoardgameListDto> getBoardgameBasicList(Pageable pageable) {
        return boardgameRepository.getBoardgameSimpleList(pageable);
    }

    public BoardgameDetailDto getBoardgameDetail(Long id){
        Optional<Boardgame> findBoardgameWrapper = boardgameRepository.findById(id);
        if (!findBoardgameWrapper.isPresent())
            return null;

        return new BoardgameDetailDto(findBoardgameWrapper.get())
                .setMechanisms(boardgameRepository.getBoardgameMechanismNames(id))
                .setCategories(boardgameRepository.getBoardgameCategoryNames(id));
    }

    public List<MechListDto> getAllMechanisms(){
        return mechanismRepository.findAllMechanismListDto();
    }

    public Page<BoardgameListDto> getBoardgameListInMechanism(Long mechId, Pageable pageable){
        return boardgameRepository.getBoardgameInMechanism(mechId, pageable);
    }

    public List<CateListDto> getAllCategories() {
        return categoryRepository.findAllCategoriesListDto();
    }

    public Page<BoardgameListDto> getBoardgameListInCategory(Long cateId, Pageable pageable){
        return boardgameRepository.getBoardgameInCategory(cateId, pageable);
    }

    // Search ------------------------------

    public Page<BoardgameListDto> searchBoardgame(BoardgameFilter boardgameFilter, Pageable pageable) {
        return searchRepository.searchBoardgame(boardgameFilter, pageable);
    }

    public SearchReqList initSearchFilter() {
        return new SearchReqList()
                .setMechanisms(mechanismRepository.findAllMechanismListDto())
                .setCategories(categoryRepository.findAllCategoriesListDto());
    }
}
