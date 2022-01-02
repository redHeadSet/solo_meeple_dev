package mozziyulmu.meeple.oauth;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.UserRepository;
import mozziyulmu.meeple.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes =
                OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = setUser(attributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes.convertToMap(), "email");
    }

    private User setUser(OAuthAttributes attributes) {
        Optional<User> findUser = userRepository.findByEmail(attributes.getEmail());
        User rtnUser = null;
        if(findUser.isPresent()){
            rtnUser = findUser.get();
            rtnUser.updateNickName(attributes.getName());
        }
        else{
            rtnUser = attributes.toEntity();
            userRepository.save(rtnUser);
        }
        return rtnUser;
    }
}
