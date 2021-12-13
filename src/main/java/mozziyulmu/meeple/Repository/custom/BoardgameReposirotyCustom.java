package mozziyulmu.meeple.Repository.custom;

import mozziyulmu.meeple.dto.BoardgameListDto;

import java.util.List;

public interface BoardgameReposirotyCustom {
    public List<BoardgameListDto> getBoardgameSimpleList();

    public List<BoardgameListDto> getBoardgameInCategory(String categoryKorName);
}
