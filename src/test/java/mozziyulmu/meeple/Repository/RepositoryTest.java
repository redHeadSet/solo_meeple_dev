package mozziyulmu.meeple.Repository;

import static org.junit.jupiter.api.Assertions.*;

import mozziyulmu.meeple.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class RepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void simple() {
        // given
        userRepository.save(new User("email1", "pass1", "nick1"));
        userRepository.save(new User("email2", "pass2", "nick2"));
        userRepository.save(new User("email3", "pass3", "nick3"));

        userRepository.clear();

        List<User> allUsers = userRepository.findAllUsers();
        for (User each :
                allUsers) {
            System.out.println("닉네임 : " + each.getNickName());
        }

        // when

        // then

    }
}