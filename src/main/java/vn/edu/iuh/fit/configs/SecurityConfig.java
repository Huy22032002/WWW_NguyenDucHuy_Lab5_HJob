package vn.edu.iuh.fit.configs;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import vn.edu.iuh.fit.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Lấy role từ Authentication
            String role = authentication.getAuthorities().toString();
            System.out.println("role: " + role);
            if (role.contains("ROLE_CANDIDATE")) {
                response.sendRedirect("/candidate/home");
            } else if (role.contains("ROLE_COMPANY")) {
                response.sendRedirect("/company/home");
            } else {
                response.sendRedirect("/home");
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/login", "/register", "/", "/home")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .successHandler((authenticationSuccessHandler())

                        )
                        .failureHandler((request, response, authentication) -> {
                                System.out.println("SecurityFilterChain: Login Failed");
                                }
                        ).permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
