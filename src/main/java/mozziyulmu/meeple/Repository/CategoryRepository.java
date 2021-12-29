package mozziyulmu.meeple.Repository;

import mozziyulmu.meeple.dto.CateListDto;
import mozziyulmu.meeple.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select ct.korName from Category ct")
    List<String> findAllCategoryKorName();

    @Query("select new mozziyulmu.meeple.dto.CateListDto(ct) from Category ct")
    List<CateListDto> findAllCategoriesListDto();

    Category findByEngName(String engName);
    Category findByKorName(String korName);
    // 카테고리별 보드게임은 BoardgameRepositoryImpl 처리
}
