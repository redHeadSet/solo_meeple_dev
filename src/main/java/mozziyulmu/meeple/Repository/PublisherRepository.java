package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByKorName(String korName);
}
