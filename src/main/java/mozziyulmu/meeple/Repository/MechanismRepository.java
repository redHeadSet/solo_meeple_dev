package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.entity.Mechanism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MechanismRepository extends JpaRepository<Mechanism, Long> {
    @Query("select mech.korName from Mechanism mech")
    List<String> findAllMechanismKorName();

    Mechanism findByEngName(String engName);
}
