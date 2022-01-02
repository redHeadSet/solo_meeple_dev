package mozziyulmu.meeple.controller;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.oauth.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexTest {
    private final HttpSession httpSession;

    @GetMapping("/test")
    public String index(Model model) {
//        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        if (sessionUser != null)
            model.addAttribute("userName", sessionUser.getNickname());

        return "index";
    }
}
