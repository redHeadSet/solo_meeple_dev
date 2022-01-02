package mozziyulmu.meeple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexTest2 {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
