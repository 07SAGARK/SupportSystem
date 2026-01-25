package com.example.crudOprt.Security;

import com.example.crudOprt.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig  {

    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService){
        this.customUserDetailsService=userDetailsService;
}
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET,
                                "/agent/login",
                                "/agent/register"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/agent/register",
                                "/login"
                        ).permitAll()

                        .requestMatchers("/css/**", "/js/**").permitAll()

                        .requestMatchers("/incident/create").hasAnyRole("CLIENT", "AGENT")
                        .requestMatchers("/get").hasAnyRole("ADMIN","AGENT")
                        .requestMatchers(HttpMethod.PUT,"/incident/update/**")
                        .hasAnyRole("CLIENT", "ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/agent/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/incident/create", true)
                        .permitAll()
                );

        return http.build();
    }

}
