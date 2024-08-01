package layeredarchitecture.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * SecurityFilterChain 설정
     *
     * @param http HttpSecurity 객체
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
                // `/auth/**` 로 시작하는 요청은 인증 통과시키고, 그 외의 모든 요청은 인증 필요
                authorize.requestMatchers("/auth/**")
                         .permitAll()
                         .anyRequest()
                         .authenticated();
            })
            // 세션 관리를 STATELESS 로 설정하여, 세션을 사용하지 않는 것으로 설정.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // CSRF 보호 비활성화 (token 인증을 사용할 경우 불필요)
            .csrf(AbstractHttpConfigurer::disable)
            // H2 콘솔이 iframe 을 사용하므로 frameOptions 설정 추가
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        // JwtAuthenticationFilter 를 UsernamePasswordAuthenticationFilter 보다 먼저 실행되도록 합니다.
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 설정된 SecurityFilterChain 을 반환합니다.
        return http.build();
    }

}
