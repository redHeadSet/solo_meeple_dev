package mozziyulmu.meeple.oauth;

import lombok.Getter;
import mozziyulmu.meeple.entity.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String nickname;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
