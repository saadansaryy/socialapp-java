package com.mecaps.socialApp.config;

import com.mecaps.socialApp.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthFilter jwtFilter;

     public SecurityConfig(UserDetailsService userDetailsService, JwtAuthFilter jwtFilter){
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

//    Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)      // Disable CSRF for APIs
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/user/create").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/user/get").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/post/get").permitAll()
                        .requestMatchers("/post/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/comment/get").permitAll()
                        .requestMatchers("/comment/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
                );
//                .userDetailsService(userDetailsService); // Optional to write. loadUserByUserName is implicitly called (when there is no other method with the same name is present in the application.)
//                .httpBasic(Customizer.withDefaults()); // Enable Basic Auth

//        Add JWT filter before username/password authentication filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
