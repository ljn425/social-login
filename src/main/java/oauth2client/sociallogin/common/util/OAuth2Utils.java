package oauth2client.sociallogin.common.util;

import oauth2client.sociallogin.model.Attributes;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class OAuth2Utils {
//    public static Attributes getMainAttributes(OAuth2User oAuth2User) {
//        return Attributes.builder()
//                .mainAttributes(oAuth2User.getAttributes())
//                .build();
//    }

    public static Attributes getSubAttributes(OAuth2User oAuth2User, String subAttributesKey) {
        Map<String, Object> mainAttributes = oAuth2User.getAttributes();
        Map<String, Object> subAttributes = (Map<String, Object>) mainAttributes.get(subAttributesKey);
        return Attributes.builder()
                .subAttributes(subAttributes)
                .build();
    }

//    public static Attributes getOtherAttributes(OAuth2User oAuth2User, String subAttributesKey, String otherAttributesKey) {
//        Map<String, Object> mainAttributes = oAuth2User.getAttributes();
//        Map<String, Object> subAttributes = (Map<String, Object>) mainAttributes.get(subAttributesKey);
//        Map<String, Object> otherAttributes = (Map<String, Object>) subAttributes.get(otherAttributesKey);
//        return Attributes.builder()
//                .subAttributes(subAttributes)
//                .otherAttributes(otherAttributes)
//                .build();
//    }

//    public static String oAuth2UserName(OAuth2AuthenticationToken authentication, PrincipalUser principalUser) {
//        String userName;
//        String registrationId = authentication.getAuthorizedClientRegistrationId();
//        OAuth2User oAuth2User = principalUser.providerUser().getOAuth2User();
//
//        // google
//        Attributes attributes = OAuth2Utils.getMainAttributes(oAuth2User);
//        userName = (String) attributes.getMainAttributes().get("name");
//
//        // naver
//        if (registrationId.equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
//            attributes = OAuth2Utils.getSubAttributes(oAuth2User, "response");
//            userName = (String) attributes.getSubAttributes().get("email");
//
//        // kakao
//        } else if(registrationId.equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
//            attributes = OAuth2Utils.getMainAttributes(oAuth2User);
//            userName = (String) attributes.getMainAttributes().get("nickname");
//        }
//
//        return userName;
//    }

}
