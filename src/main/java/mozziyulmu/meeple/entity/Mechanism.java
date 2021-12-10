package mozziyulmu.meeple.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseUserData;
import mozziyulmu.meeple.entity.Relation.BoardMechanism.BoardMechaRT;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mechanism extends BaseUserData {
    @Id
    @GeneratedValue
    @Column(name = "mechanism_id")
    private Long id;

    @Column(name = "mechanism_kor_name")
    private String kor_name;
    @Column(name = "mechanism_eng_name")
    private String eng_name;

    @OneToMany(mappedBy = "mechanism")
    List<BoardMechaRT> boardgames = new ArrayList<>();

    // ========================================================================
    // 보드게임에서 매커니즘 추가 가능, 반대는 불가능
    public Mechanism(String kor_name, String eng_name) {
        this.kor_name = kor_name;
        this.eng_name = eng_name;
    }
}