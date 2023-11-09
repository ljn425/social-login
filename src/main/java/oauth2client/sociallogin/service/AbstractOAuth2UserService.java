package oauth2client.sociallogin.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth2client.sociallogin.model.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class AbstractOAuth2UserService {

    private final UserService userService;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {
        User user = userService.findByUsername(providerUser.getUsername());

        if (user == null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            userService.register(registrationId, providerUser);
        } else {
            log.debug("이미 가입한 user 입니다. 유저정보 = {}", user);
        }
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User) {
        String registrationId = clientRegistration.getRegistrationId();
        return switch (registrationId) {
            case "keycloak" -> new KeycloakUser(oAuth2User, clientRegistration);
            case "google" -> new GoogleUser(oAuth2User, clientRegistration);
            case "naver" -> new NaverUser(oAuth2User, clientRegistration);
            default -> null;
        };
    }
}
