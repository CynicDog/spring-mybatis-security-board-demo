package kr.co.jhta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()

                .formLogin(config -> {
                    config.loginPage("/user/login");
                    config.usernameParameter("email");
                    config.passwordParameter("password");
                    config.loginProcessingUrl("/user/login");
                    config.defaultSuccessUrl("/");
                    config.failureUrl("/user/login?error=fail");
                })

                .logout(config -> {
                    config.logoutUrl("/user/logout");
                    config.logoutSuccessUrl("/");
                    config.invalidateHttpSession(true);
                })

                .exceptionHandling(config -> {
                    config.authenticationEntryPoint(((request, response, authException) -> {
                        response.sendRedirect("/user/login?error=denied");
                    }));
                    config.accessDeniedHandler(((request, response, accessDeniedException) -> {
                        response.sendRedirect("user/login?error=forbidden");
                    }));
                })

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
