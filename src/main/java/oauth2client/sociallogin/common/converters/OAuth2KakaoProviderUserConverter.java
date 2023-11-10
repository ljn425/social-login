package oauth2client.sociallogin.common.converters;

import oauth2client.sociallogin.common.enums.OAuth2Config;
import oauth2client.sociallogin.model.ProviderUser;
import oauth2client.sociallogin.model.social.KakaoUser;

public class OAuth2KakaoProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        String registrationId = providerUserRequest.clientRegistration().getRegistrationId();
        String socialName = OAuth2Config.SocialType.KAKAO.getSocialName();

        if (!registrationId.equals(socialName)){
            return null;
        }
        return new KakaoUser(
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
