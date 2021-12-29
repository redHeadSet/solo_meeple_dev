package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.dto.MechListDto;
import mozziyulmu.meeple.entity.Mechanism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MechanismRepository extends JpaRepository<Mechanism, Long> {
    @Query("select mech.korName from Mechanism mech")
    List<String> findAllMechanismKorName();

    @Query("select new mozziyulmu.meeple.dto.MechListDto(mech) from Mechanism mech")
    List<MechListDto> findAllMechanismListDto();

    Mechanism findByEngName(String engName);
    Mechanism findByKorName(String korName);

    // 매커니즘별 보드게임은 BoardgameRepositoryImpl 처리
}
