package mozziyulmu.meeple.entity.Relation.BoardUser;

import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("own")
public class OwnBoardgames extends BoardgameUserRT{
    public OwnBoardgames(User user, Boardgame boardgame) {
        super(user, boardgame);
    }
}
