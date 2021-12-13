package mozziyulmu.meeple.Repository.custom;

import mozziyulmu.meeple.dto.BoardgameListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardgameReposirotyCustom {
    public Page<BoardgameListDto> getBoardgameSimpleList(Pageable pageable);

    public Page<BoardgameListDto> getBoardgameInCategory(String categoryKorName, Pageable pageable);

    public Page<BoardgameListDto> getBoardgameInMechanism(String mechanismKorName, Pageable pageable);
}
