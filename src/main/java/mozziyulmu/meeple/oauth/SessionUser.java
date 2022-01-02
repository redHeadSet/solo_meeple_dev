package mozziyulmu.meeple.oauth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mozziyulmu.meeple.entity.User;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUser // implements Serializable
{
    private String email;
    private String name;

    @Builder
    public SessionUser(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
