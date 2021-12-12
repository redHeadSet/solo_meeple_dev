package mozziyulmu.meeple.Repository.custom;

import mozziyulmu.meeple.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    public List<User> findAllUsers();
    public void clear();
}
