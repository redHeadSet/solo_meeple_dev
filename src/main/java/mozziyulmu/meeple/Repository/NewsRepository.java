package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
