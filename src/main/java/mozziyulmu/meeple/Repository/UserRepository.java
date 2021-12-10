package mozziyulmu.meeple.Repository;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Relation.BoardUser.OwnBoardgames;
import mozziyulmu.meeple.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public Long save(User user){
        em.persist(user);
        return user.getId();
    }

    public void addOwnBoardgame(User user, Boardgame boardgame){
        OwnBoardgames ownBoardgames = user.addOwnBoardgame(boardgame);
        em.persist(ownBoardgames);
    }
}
