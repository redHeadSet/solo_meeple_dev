package mozziyulmu.meeple.Repository;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Relation.BoardUser.EvaluateBoardgames;
import mozziyulmu.meeple.entity.Relation.BoardUser.InterestBoardgames;
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
        user.addOwnBoardgame(boardgame);
    }

    public void addInterestBoardgame(User user, Boardgame boardgame){
        user.addInterestBoardgame(boardgame);
    }

    public void addEvaluateBoardgame(User user, Boardgame boardgame){
        user.addEvaluateBoardgame(boardgame);
    }
}
