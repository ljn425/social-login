package oauth2client.sociallogin.service;

import oauth2client.sociallogin.common.converters.ProviderUserConverter;
import oauth2client.sociallogin.common.converters.ProviderUserRequest;
import oauth2client.sociallogin.model.PrincipalUser;
import oauth2client.sociallogin.model.ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    public CustomOAuth2UserService(UserService userService,
                                   ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter) {
        super(userService, providerUserConverter);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest); // 인가서버에 유저정보 요청 (userInfo)

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oAuth2User);

        ProviderUser providerUser = providerUser(providerUserRequest); // 인가서버에 맞춰 User 객체 생성 ex) google, naver 등
        // DB 회원가입
        super.register(providerUser, userRequest);

        return new PrincipalUser(providerUser);
    }
}
