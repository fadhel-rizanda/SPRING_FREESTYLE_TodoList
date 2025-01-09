package SPRING_FREESTYLE_TodoList.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // untuk  interupt default
public class SecurityConfiguration {
//    8
    @Autowired
    private JwtFilter jwtFilter;

    @Bean // 1 bikin default config
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/users/**") // gunakna utnuk mengatasi collision
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/users/auth/register", "/users/auth/login").permitAll() // untuk address api non auth require
//                        .requestMatchers("/error").permitAll() mgnebolehin error mvc diliatin
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // tambahkan setelah selesai jwt service
                .build();
    }

//    OAuth
    @Bean
    @Order(2)
    public SecurityFilterChain securityOauthFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/oauth2/**")  // Only applies to OAuth2 paths
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/oauth2/authorization/google", "/login/oauth2/code/google").permitAll()  // Google OAuth2 URLs
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(Customizer.withDefaults()) // akan error karena oauth banyak (google dll) maka harus disepecify di application prop
        .build();
    }


    //    2 provider
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

//    5
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
