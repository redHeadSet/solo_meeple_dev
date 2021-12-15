package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.Repository.custom.SearchRepositoryCustom;
import mozziyulmu.meeple.Repository.customImpl.SearchRepositoryImpl;
import mozziyulmu.meeple.entity.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Boardgame, Long>, SearchRepositoryCustom {
}
