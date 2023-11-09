package oauth2client.sociallogin.config;

import lombok.RequiredArgsConstructor;
import oauth2client.sociallogin.mapper.CustomAuthorityMapper;
import oauth2client.sociallogin.resolver.CustomOAuth2AuthorizationRequestResolver;
import oauth2client.sociallogin.service.CustomOAuth2UserService;
import oauth2client.sociallogin.service.CustomOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class OAuth2ClientConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers("/js/**", "/images/**", "/css/**","/scss/**").permitAll()
                .requestMatchers("/api/user").hasAnyRole("SCOPE_profile", "SCOPE_email", "OAUTH2_USER")
                .requestMatchers("/api/oidc").hasRole("SCOPE_openid")
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
        );

        http.oauth2Login(oauth2 -> oauth2
                .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
                        .authorizationRequestResolver(customOAuthorizationRequestResolver()))
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)
                        .oidcUserService(customOidcUserService)));

        http.logout(logout -> logout
                .logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }
    private OAuth2AuthorizationRequestResolver customOAuthorizationRequestResolver() {
        return new CustomOAuth2AuthorizationRequestResolver(clientRegistrationRepository, "/oauth2/authorization");
    }
}
