package mozziyulmu.meeple.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRequestMapper {
    public SessionUser toDto(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return SessionUser.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .build();
    }
}
