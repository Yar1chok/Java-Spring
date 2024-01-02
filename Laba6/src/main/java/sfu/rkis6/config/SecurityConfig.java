package sfu.rkis6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sfu.rkis6.service.CustomUsersDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    private final CustomUsersDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUsersDetailsService customUsersDetailsService) {
        this.userDetailsService = customUsersDetailsService;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/user/login",
                                        "/user/registration").permitAll()
                                .requestMatchers("/show", "/search", "/user/profile", "/", "/logout").authenticated()
                                .anyRequest().hasRole("ADMIN")
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/user/login")
                                .usernameParameter("login")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/user/profile", true)
                                .failureUrl("/user/login?error=true")
                )
                .logout(logoutForm ->
                        logoutForm
                                .logoutSuccessUrl("/user/login?logout=true")
                )
                .build();
    }
}
