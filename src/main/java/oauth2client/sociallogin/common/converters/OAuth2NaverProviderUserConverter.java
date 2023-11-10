package oauth2client.sociallogin.common.converters;

import oauth2client.sociallogin.common.enums.OAuth2Config;
import oauth2client.sociallogin.model.ProviderUser;
import oauth2client.sociallogin.model.social.NaverUser;
import oauth2client.sociallogin.common.util.OAuth2Utils;

public class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        String registrationId = providerUserRequest.clientRegistration().getRegistrationId();
        String socialName = OAuth2Config.SocialType.NAVER.getSocialName();

        if (!registrationId.equals(socialName)){
            return null;
        }
        return new NaverUser(
                OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(), "response"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
