package mozziyulmu.meeple.Repository.custom;

import mozziyulmu.meeple.dto.BoardgameListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardgameReposirotyCustom {
    public Page<BoardgameListDto> getBoardgameSimpleList(Pageable pageable);

    public Page<BoardgameListDto> getBoardgameInCategory(String categoryKorName, Pageable pageable);

    public Page<BoardgameListDto> getBoardgameInMechanism(String mechanismKorName, Pageable pageable);

    public List<BoardgameListDto> getBoardgameInRecommand(Long recommandId);

    public List<String> getBoardgameMechanismNames(Long BoardgameId);
    public List<String> getBoardgameCategoryNames(Long BoardgameId);
}
