package oauth2client.sociallogin.model.users;

import lombok.Builder;
import lombok.Data;
import oauth2client.sociallogin.model.ProviderUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class FormUser implements ProviderUser {

    private String id;
    private String username;
    private String password;
    private String email;
    private String provider;
    private List<? extends GrantedAuthority> authorities;

    @Override
    public String getPicture() {
        return null;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    @Override
    public OAuth2User getOAuth2User() {
        return null;
    }
}
