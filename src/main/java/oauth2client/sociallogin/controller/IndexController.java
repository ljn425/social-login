package oauth2client.sociallogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Authentication authentication,
                        @AuthenticationPrincipal OAuth2User oAuth2User,
                        Model model) {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if (oAuth2AuthenticationToken != null) {
           model.addAttribute("user", oAuth2User.getName());
        }
        return "index";
    }
}
