package mozziyulmu.meeple.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.BaseEntity.BaseUserData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Publisher extends BaseUserData {
    @Id
    @GeneratedValue
    @Column(name = "publisher_id")
    private Long id;

    @Column(name = "publisher_kor_name")
    private String korName;
    @Column(name = "publisher_eng_name")
    private String engName;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    List<Boardgame> publishBoardgames = new ArrayList<>();

    public Publisher(String kor_name, String eng_name) {
        this.korName = kor_name;
        this.engName = eng_name;
    }
}
