package mozziyulmu.meeple.controller;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.dto.UserSignUpDto;
import mozziyulmu.meeple.dto.UserUpdateDto;
import mozziyulmu.meeple.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // 중복이 없는 경우 true
    @GetMapping("/check/email")
    public Boolean checkDupEMail(@RequestParam("eMail")String email){
        return userService.checkDupEMail(email);
    }

    // 중복이 없는 경우 true
    @GetMapping("/check/nickname")
    public Boolean checkDupNickName(@RequestParam("nickName")String nickname){
        return userService.checkDupNickName(nickname);
    }

    @PostMapping("/signup")
    public Boolean signup(@ModelAttribute UserSignUpDto userSignUpDto) {
        return userService.signUp(userSignUpDto);
    }

    @PostMapping("/update")
    public Boolean update(@ModelAttribute UserUpdateDto userUpdateDto) {
        return userService.update(userUpdateDto);
    }
}
