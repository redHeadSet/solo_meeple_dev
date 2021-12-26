package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    public List<News> findAllByOrderByWriteTimeDesc();
    public Optional<News> findByTitleAndUrl(String title, String url);
}
