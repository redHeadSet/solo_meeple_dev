package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagesRepository extends JpaRepository<Images, Long> {
    Optional<Images> findByPath(String path);
}
