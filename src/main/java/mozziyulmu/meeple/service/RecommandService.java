package mozziyulmu.meeple.service;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.BoardgameRepository;
import mozziyulmu.meeple.Repository.RecommandRepository;
import mozziyulmu.meeple.dto.BoardgameListDto;
import mozziyulmu.meeple.dto.RecommandDto;
import mozziyulmu.meeple.dto.RecommandListDto;
import mozziyulmu.meeple.entity.Recommand;
import mozziyulmu.meeple.entity.Relation.BoardRecom.BoardRecomRT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommandService {
    private final RecommandRepository recommandRepository;
    private final BoardgameRepository boardgameRepository;

    public List<RecommandListDto> getRecommandList(){
        return recommandRepository.findAll().stream()
                .map(recommand -> new RecommandListDto(recommand))
                .collect(Collectors.toList());
    }

    public Optional<RecommandDto> getRecommandData(Long id) {
        Optional<Recommand> wrappedRecommand = recommandRepository.findById(id);

        if(!wrappedRecommand.isPresent())
            return Optional.of(null);

        return Optional.of(
                    new RecommandDto(wrappedRecommand.get())
                    .setBoardgames(boardgameRepository.getBoardgameInRecommand(id)));
    }
}
