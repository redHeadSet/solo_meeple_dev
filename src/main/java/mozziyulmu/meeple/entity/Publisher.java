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
    private String kor_name;
    @Column(name = "publisher_eng_name")
    private String eng_name;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    List<Boardgame> publish_boardgames = new ArrayList<>();

    public Publisher(String kor_name, String eng_name) {
        this.kor_name = kor_name;
        this.eng_name = eng_name;
    }
}
