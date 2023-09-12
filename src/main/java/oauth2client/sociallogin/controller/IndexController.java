package oauth2client.sociallogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        if (authentication != null)
            model.addAttribute("user", authentication.getName());
        return "index";
    }
}
