package pl.com.core.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import pl.com.core.Services.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration{

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Token w ciasteczkach
        );

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(("/admin/**")).permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/seller/**").hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "SELLER", "ADMIN")
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/General/**").permitAll()
                        .requestMatchers("/api/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );
        http.formLogin(form -> form.
                loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login")
                .permitAll()
        );
        http.logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-CSRF-TOKEN");
        return repository;
    }
}
