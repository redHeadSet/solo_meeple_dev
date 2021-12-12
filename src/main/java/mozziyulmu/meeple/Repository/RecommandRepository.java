package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.entity.Recommand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommandRepository extends JpaRepository<Recommand, Long> {
    Optional<Recommand> findByTitle(String title);
}
