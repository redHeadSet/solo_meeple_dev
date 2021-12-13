package mozziyulmu.meeple.Repository.customImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import mozziyulmu.meeple.Repository.custom.UserRepositoryCustom;
import mozziyulmu.meeple.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

import static mozziyulmu.meeple.entity.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
}
