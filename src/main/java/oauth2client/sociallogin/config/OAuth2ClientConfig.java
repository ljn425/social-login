package oauth2client.sociallogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class OAuth2ClientConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers("/js/**", "/images/**", "/css/**","/scss/**").permitAll()
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
        );
        http.oauth2Login(Customizer.withDefaults()); // application.yml 에 oauth2 클라이언트 정보를 설정해줘야 오류가 나지 않는다.
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }
}
