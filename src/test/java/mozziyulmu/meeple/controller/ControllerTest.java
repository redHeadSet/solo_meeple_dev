package mozziyulmu.meeple.controller;

import mozziyulmu.meeple.dto.UserUpdateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ControllerTest {
    @Autowired
    private UserController userController;

    @Test
    @Commit
    public void 유저_컨트롤러_테스트() {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail("stikfascube@gmail.com");
        userUpdateDto.setPassword("1234");
        userUpdateDto.setNickname("빨헤");

        userController.update(userUpdateDto);

        // when

        // then

    }
}