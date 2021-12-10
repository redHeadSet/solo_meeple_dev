package mozziyulmu.meeple.entity.Relation.BoardUser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// 흥미있는, 관심있는 보드게임
@Entity
@DiscriminatorValue("interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestBoardgames extends BoardgameUserRT{
    public InterestBoardgames(User user, Boardgame boardgame) {
        super(user, boardgame);
    }
}
