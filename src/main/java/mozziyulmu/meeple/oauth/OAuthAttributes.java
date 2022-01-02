package mozziyulmu.meeple.oauth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import mozziyulmu.meeple.entity.User;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class OAuthAttributes {
    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
    private String attributeKey;
    private String email;
    private String name;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String attributeKey, String name, String email) {
        this.attributes = attributes;
        this.attributeKey = attributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String attributeKey, Map<String, Object> attributes){
        // 여기서 네이버와 카카오 등 구분 (ofNaver, ofKakao)

        return ofGoogle(attributeKey, attributes);
    }

    private static OAuthAttributes ofGoogle(String attributeKey, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    public User toEntity(){
        return new User(email, "-", name);
    }

    public Map<String, Object> convertToMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("name", name);
        map.put("email", email);
        return map;
    }
}
