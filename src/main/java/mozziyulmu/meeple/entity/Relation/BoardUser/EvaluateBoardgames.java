package mozziyulmu.meeple.entity.Relation.BoardUser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// 평가한 보드게임
@Entity
@DiscriminatorValue("evaluate")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluateBoardgames extends BoardgameUserRT{
    public EvaluateBoardgames(User user, Boardgame boardgame) {
        super(user, boardgame);
    }
}
