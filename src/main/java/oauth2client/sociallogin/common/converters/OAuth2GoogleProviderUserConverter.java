package oauth2client.sociallogin.common.converters;

import oauth2client.sociallogin.common.enums.OAuth2Config;
import oauth2client.sociallogin.model.ProviderUser;
import oauth2client.sociallogin.model.social.GoogleUser;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        String registrationId = providerUserRequest.clientRegistration().getRegistrationId();
        String socialName = OAuth2Config.SocialType.GOOGLE.getSocialName();

        if (!registrationId.equals(socialName)){
            return null;
        }
        return new GoogleUser(
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}
