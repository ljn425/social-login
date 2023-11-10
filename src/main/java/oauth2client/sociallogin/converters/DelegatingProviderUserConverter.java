package oauth2client.sociallogin.converters;

import oauth2client.sociallogin.model.ProviderUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{
    private final List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter() {
        List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> providerUserConverters =
                List.of(new OAuth2GoogleProviderUserConverter(),
                        new OAuth2NaverProviderUserConverter());

        converters = Collections.unmodifiableList(new LinkedList<>(providerUserConverters));
    }

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null");

        for(ProviderUserConverter<ProviderUserRequest, ProviderUser> converter : converters) {
            ProviderUser providerUser = converter.converter(providerUserRequest);
            if(providerUser != null) return providerUser;
        }
        return null;
    }
}
