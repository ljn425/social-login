package oauth2client.sociallogin.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth2client.sociallogin.converters.ProviderUserConverter;
import oauth2client.sociallogin.converters.ProviderUserRequest;
import oauth2client.sociallogin.model.ProviderUser;
import oauth2client.sociallogin.model.users.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class AbstractOAuth2UserService {

    private final UserService userService;
    private final ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {
        User user = userService.findByUsername(providerUser.getUsername());

        if (user == null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            userService.register(registrationId, providerUser);
        } else {
            log.debug("이미 가입한 user 입니다. 유저정보 = {}", user);
        }
    }

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest) {
        return providerUserConverter.converter(providerUserRequest);
    }
}
