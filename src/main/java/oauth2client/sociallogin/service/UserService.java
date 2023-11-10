package oauth2client.sociallogin.service;

import lombok.RequiredArgsConstructor;
import oauth2client.sociallogin.model.ProviderUser;
import oauth2client.sociallogin.model.users.User;
import oauth2client.sociallogin.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void register(String registrationId, ProviderUser providerUser) {
        User user = User.builder()
                .registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUsername())
                .provider(providerUser.getProvider())
                .email(providerUser.getEmail())
                .authorities(providerUser.getAuthorities())
                .build();

        userRepository.register(user);
    }
}
