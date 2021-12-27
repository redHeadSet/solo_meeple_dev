package mozziyulmu.meeple.service;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.BoardgameRepository;
import mozziyulmu.meeple.Repository.CategoryRepository;
import mozziyulmu.meeple.Repository.MechanismRepository;
import mozziyulmu.meeple.Repository.SearchRepository;
import mozziyulmu.meeple.dto.BoardgameDetailDto;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.dto.SearchReqList;
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

    public Page<BoardgameListDto> searchBoardgame(BoardgameFilter boardgameFilter, Pageable pageable) {
        return searchRepository.searchBoardgame(boardgameFilter, pageable);
    }

    public Optional<BoardgameDetailDto> getBoardgameDetail(Long id){
        Optional<Boardgame> findBoardgameWrapper = boardgameRepository.findById(id);
        if (!findBoardgameWrapper.isPresent())
            return Optional.of(null);

        return Optional.of(new BoardgameDetailDto(findBoardgameWrapper.get())
                .setMechanisms(boardgameRepository.getBoardgameMechanismNames(id))
                .setCategories(boardgameRepository.getBoardgameCategoryNames(id)));
    }

    public SearchReqList initSearchFilter() {
        return new SearchReqList()
                .setMechanisms(mechanismRepository.findAllMechanismKorName())
                .setCategories(categoryRepository.findAllCategoryKorName());
    }
}
