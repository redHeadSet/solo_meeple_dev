package mozziyulmu.meeple.entity.Relation.BoardUser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// 가지고 있는 보드게임
@Entity
@DiscriminatorValue("own")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnBoardgames extends BoardgameUserRT{
    public OwnBoardgames(User user, Boardgame boardgame) {
        super(user, boardgame);
    }
}
