package mozziyulmu.meeple.service;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.BoardgameRepository;
import mozziyulmu.meeple.Repository.SearchRepository;
import mozziyulmu.meeple.dto.BoardgameDetailDto;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.support.BoardgameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardgameService {
    private final BoardgameRepository boardgameRepository;
    private final SearchRepository searchRepository;

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
}
