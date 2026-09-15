package com.tatkal.backend_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(e->e.disable());
        http.authorizeHttpRequests(authorizeHttpRequests-> {
            authorizeHttpRequests.requestMatchers("/api/v1/auth/register").permitAll()
                    .requestMatchers("/ap1/v1/auth/login").permitAll()
                    .anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public UserDetailsService users(){
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        UserDetails user1 = userBuilder.username("aryan").password("abc").roles("ADMIN").build();
//        UserDetails user2 = userBuilder.username("aryan").password("abc").roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user1);
//    }
}
