package oauth2client.sociallogin.common.converters;

import oauth2client.sociallogin.model.ProviderUser;
import oauth2client.sociallogin.model.users.FormUser;
import oauth2client.sociallogin.model.users.User;

public class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (providerUserRequest.user() == null){
            return null;
        }
        User user = providerUserRequest.user();
        return FormUser.builder()
                        .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .authorities(user.getAuthorities())
                .provider("none")
                .build();
    }
}
