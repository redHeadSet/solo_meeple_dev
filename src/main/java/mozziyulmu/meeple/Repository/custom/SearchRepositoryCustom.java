package mozziyulmu.meeple.Repository.custom;

import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.support.BoardgameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepositoryCustom {
    Page<BoardgameListDto> searchBoardgame(BoardgameFilter boardgameFilter, Pageable pageable);
}
