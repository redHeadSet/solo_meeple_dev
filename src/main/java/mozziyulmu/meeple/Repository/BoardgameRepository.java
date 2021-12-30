package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.Repository.custom.BoardgameReposirotyCustom;
import mozziyulmu.meeple.entity.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardgameRepository extends JpaRepository<Boardgame, Long>, BoardgameReposirotyCustom {
    Optional<Boardgame> findById(Long id);

    List<Boardgame> findAll();

    Optional<Boardgame> findByKorName(String korName);

    Optional<Boardgame> findByGeekId(Long geekId);
}
