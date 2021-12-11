package mozziyulmu.meeple.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseUserData;
import mozziyulmu.meeple.entity.Relation.BoardCategory.BoardCateRT;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseUserData {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_kor_name")
    private String korName;
    @Column(name = "category_eng_name")
    private String engName;

    @OneToMany(mappedBy = "category")
    List<BoardCateRT> boardgames = new ArrayList<>();

    // ========================================================================
    // 보드게임에서 매커니즘 추가 가능, 반대는 불가능
    public Category(String kor_name, String eng_name) {
        this.korName = kor_name;
        this.engName = eng_name;
    }
}
