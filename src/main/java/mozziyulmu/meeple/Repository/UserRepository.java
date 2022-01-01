package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.Repository.custom.UserRepositoryCustom;
import mozziyulmu.meeple.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    List<User> findAll();

    @Query("select count(1) from User where e_mail = :eMail")
    int checkDupEMail(@Param("eMail") String eMail);

    @Query("select count(1) from User where nickname = :nickName")
    int checkDupNickName(@Param("nickName") String nickName);

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
