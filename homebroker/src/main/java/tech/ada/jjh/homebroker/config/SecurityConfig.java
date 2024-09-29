package tech.ada.jjh.homebroker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/users/create").permitAll()
                        .requestMatchers("/api/v1/transactions").hasRole("USER")
                        .requestMatchers("/api/v1/orders").hasRole("USER")
                        .requestMatchers("/api/v1/stocks").hasRole("ADMIN")
                        .requestMatchers("/api/v1/fees").hasRole("ADMIN")
                        .anyRequest().authenticated()

                );
//                // Configura a página de login
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//
//                // Configura o logout
//                .logout(LogoutConfigurer::permitAll
//                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); // Configura o PasswordEncoder
    }

}
