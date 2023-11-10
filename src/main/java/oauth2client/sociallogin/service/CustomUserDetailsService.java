package oauth2client.sociallogin.service;

import oauth2client.sociallogin.common.converters.ProviderUserConverter;
import oauth2client.sociallogin.common.converters.ProviderUserRequest;
import oauth2client.sociallogin.model.PrincipalUser;
import oauth2client.sociallogin.model.ProviderUser;
import oauth2client.sociallogin.model.users.User;
import oauth2client.sociallogin.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService extends AbstractOAuth2UserService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserService userService,
                                    ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter,
                                    UserRepository userRepository) {
        super(userService, providerUserConverter);
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            user = User.builder()
                    .id("1")
                    .username("user1")
                    .password("{noop}1234")
                    .authorities(AuthorityUtils.createAuthorityList("ROLE_USER"))
                    .email("user@a.com")
                    .build();
        }
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(user);
        ProviderUser providerUser = providerUser(providerUserRequest);

        return new PrincipalUser(providerUser);
    }
}
