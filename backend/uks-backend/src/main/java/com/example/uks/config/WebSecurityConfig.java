package com.example.uks.config;

import com.example.uks.security.auth.CustomAuthenticationFailureHandler;
import com.example.uks.security.auth.RestAuthenticationEntryPoint;
import com.example.uks.security.auth.TokenAuthenticationFilter;
import com.example.uks.services.CustomUserDetailsService;
import com.example.uks.util.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class WebSecurityConfig {

     @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private TokenUtils tokenUtils;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    // Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
    // BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws BadCredentialsException {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    // Definisemo prava pristupa za zahteve ka odredjenim URL-ovima/rutama
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()); //Omogućava CORS politiku kako bi aplikacija mogla da obradi zahteve sa različitih domena
        http.csrf((csrf) -> csrf.disable()); //CSRF zaštita je isključena
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //Postavlja politiku upravljanja sesijama na STATELESS
        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(restAuthenticationEntryPoint));

        //TODO: DODATI PATHS
        http.authorizeHttpRequests(request -> {
            request.requestMatchers(new AntPathRequestMatcher("/api/auth/login")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/api/auth/register")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/api/auth/logout")).authenticated()
                    .requestMatchers(new AntPathRequestMatcher("/api/gateway/update-user/{id}")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/api/gateway/disable-user/{id}")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/api/auth/current-user")).authenticated()

                    // User service endpoints
                    .requestMatchers(HttpMethod.GET, "/api/user/{id}").hasAnyRole("GUEST", "HOST")
                    .requestMatchers(HttpMethod.GET, "/api/user/all").hasAnyRole("GUEST", "HOST")
                    .requestMatchers(HttpMethod.GET, "/api/user").hasAnyRole("GUEST", "HOST")
                    .requestMatchers(HttpMethod.GET, "/api/user/search").hasAnyRole("GUEST", "HOST")
                    .requestMatchers(HttpMethod.PUT, "/api/user/{id}").hasAnyRole("GUEST", "HOST")
                    .requestMatchers(HttpMethod.DELETE, "/api/user/{id}").hasAnyRole("GUEST", "HOST")

                    //Accommodation endpoints
                    .requestMatchers(HttpMethod.GET, "/api/accommodation/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/accommodation").hasRole("HOST")
                    .requestMatchers(HttpMethod.POST, "/api/accommodation/search").permitAll()

                    //Availability endpoints
                    .requestMatchers(HttpMethod.GET, "/api/availability/{accommodationId}").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/availability").hasRole("HOST")
                    .requestMatchers(HttpMethod.PUT, "/api/availability/{availabilityId}").hasRole("HOST")

                    // Reservation endpoints
                    .requestMatchers(HttpMethod.POST, "/api/reservation").hasRole("GUEST")
                    .requestMatchers(HttpMethod.DELETE, "/api/reservation/{id}").hasRole("GUEST")
                    .requestMatchers(HttpMethod.GET, "/api/reservation/all-host-pending-accommodation/{hostId}").hasRole("HOST")
                    .requestMatchers(HttpMethod.POST, "/api/reservation/save-manually-approved").hasRole("HOST")

                    // Review endpoints
                    .requestMatchers(HttpMethod.GET, "/api/accommodation-review/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/accommodation-review").hasRole("GUEST")
                    .requestMatchers(HttpMethod.PUT, "/api/accommodation-review/{id}").hasRole("GUEST")
                    .requestMatchers(HttpMethod.DELETE, "/api/accommodation-review/{id}").hasRole("GUEST")

                    .requestMatchers(HttpMethod.GET, "/api/host-review/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/host-review").hasRole("GUEST")
                    .requestMatchers(HttpMethod.PUT, "/api/host-review/{id}").hasRole("GUEST")
                    .requestMatchers(HttpMethod.DELETE, "/api/host-review/{id}").hasRole("GUEST")

                    // Notifications endpoints
                    .requestMatchers(HttpMethod.GET, "/api/notifications/{userId}").hasAnyRole("GUEST", "HOST")
                    .requestMatchers(HttpMethod.PUT, "/api/notifications/read").hasAnyRole("GUEST", "HOST")

                    .requestMatchers(HttpMethod.GET, "api/notifications-preferences/{userId}").hasAnyRole("GUEST", "HOST")
                    .requestMatchers(HttpMethod.PUT, "/api/notifications-preferences").hasAnyRole("GUEST", "HOST")

                    .requestMatchers("/actuator/prometheus").permitAll()
                    .requestMatchers("/actuator/**").permitAll()

                    .anyRequest().authenticated();
        });
        http.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userDetailsService()), UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // Autentifikacija ce biti ignorisana ispod navedenih putanja (kako bismo ubrzali pristup resursima)
        // Zahtevi koji se mecuju za web.ignoring().antMatchers() nemaju pristup SecurityContext-u
        // Dozvoljena POST metoda na ruti /auth/login, za svaki drugi tip HTTP metode greska je 401 Unauthorized
        return (web) -> web.ignoring().requestMatchers(HttpMethod.POST, "/auth/login")


                // Ovim smo dozvolili pristup statickim resursima aplikacije
                .requestMatchers(HttpMethod.GET, "/", "/webjars/*", "/*.html", "favicon.ico",
                        "/*/*.html", "/*/*.css", "/*/*.js");

    }
    //Podesavanja CORS-a
    //https://docs.spring.io/spring-security/reference/servlet/integrations/cors.html
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://devops-user:8081", "http://prometheus:9090"));
        configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE", "PATCH")); // or simply "*"
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
