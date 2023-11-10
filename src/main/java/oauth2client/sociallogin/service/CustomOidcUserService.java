package oauth2client.sociallogin.service;

import oauth2client.sociallogin.converters.ProviderUserConverter;
import oauth2client.sociallogin.converters.ProviderUserRequest;
import oauth2client.sociallogin.model.ProviderUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser>{
    public CustomOidcUserService(UserService userService,
                                 ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter) {
        super(userService, providerUserConverter);
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService = new OidcUserService();

        OidcUser oidcUser = oAuth2UserService.loadUser(userRequest); // 인가서버에 유저정보 요청 (userInfo)

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oidcUser);
        ProviderUser providerUser = providerUser(providerUserRequest); // 인가서버에 맞춰 User 객체 생성 ex) google, naver 등

        // DB 회원가입
        super.register(providerUser, userRequest);

        return oidcUser;
    }


}
