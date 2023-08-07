package kr.yeoksi.ours.oursserver.config;

import kr.yeoksi.ours.oursserver.security.HeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // MARK: 개발 편의를 위해 인증 없이 Swagger UI에 접근할 수 있도록 허용
        // TODO: 배포 시 개발 서버에만 /swagger-ui 접근을 허용하고, 운영 서버에는 허용하지 않도록 수정
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable);

        http
         .addFilterBefore(new HeaderFilter(), AbstractPreAuthenticatedProcessingFilter.class);

        return http.build();
    }
}
