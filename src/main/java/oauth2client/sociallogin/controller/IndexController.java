package oauth2client.sociallogin.controller;

import oauth2client.sociallogin.model.PrincipalUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal PrincipalUser principalUser,
                        Model model) {

        if (principalUser != null) {
            String userName = principalUser.providerUser().getUsername();
            model.addAttribute("user", userName);
            model.addAttribute("provider", principalUser.providerUser().getProvider());
        }
        return "index";
    }
}
