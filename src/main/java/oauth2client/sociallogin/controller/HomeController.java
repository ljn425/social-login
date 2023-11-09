package oauth2client.sociallogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {
    @GetMapping("/api/user")
    public String user(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println("authentication = " + authentication);
        System.out.println("oAuth2User = " + oAuth2User);
        return "authentication";
    }

    @GetMapping("/api/oidc")
    public String oidc(Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {
        System.out.println("authentication = " + authentication);
        System.out.println("oidcUser = " + oidcUser);
        return "authentication";
    }
}
