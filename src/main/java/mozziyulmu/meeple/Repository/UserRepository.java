package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.Repository.custom.UserRepositoryCustom;
import mozziyulmu.meeple.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
