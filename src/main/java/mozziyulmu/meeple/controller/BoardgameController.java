package mozziyulmu.meeple.controller;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.service.BoardgameService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardgameController {
    private final BoardgameService boardgameService;


}
