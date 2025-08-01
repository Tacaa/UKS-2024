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

    // Handler za vracanje 401 kada klijent sa neodogovarajucim korisnickim imenom i lozinkom pokusa da pristupi resursu
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    // Injektujemo implementaciju iz TokenUtils klase kako bismo mogli da koristimo njene metode za rad sa JWT u TokenAuthenticationFilteru
    @Autowired
    private TokenUtils tokenUtils;

    // Servis koji se koristi za citanje podataka o korisnicima aplikacije
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
        // 1. koji servis da koristi da izvuce podatke o korisniku koji zeli da se autentifikuje
        // prilikom autentifikacije, AuthenticationManager ce sam pozivati loadUserByUsername() metodu ovog servisa
        authProvider.setUserDetailsService(userDetailsService());
        // 2. kroz koji enkoder da provuce lozinku koju je dobio od klijenta u zahtevu
        // da bi adekvatan hash koji dobije kao rezultat hash algoritma uporedio sa onim koji se nalazi u bazi (posto se u bazi ne cuva plain lozinka)
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // Registrujemo authentication manager koji ce da uradi autentifikaciju korisnika za nas
    // Centralizuje proces autentifikacije
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

        http.authorizeHttpRequests(request -> {
            request.requestMatchers(new AntPathRequestMatcher("/api/auth/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/auth/register")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/auth/logout")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/auth/current-user")).authenticated()
                .requestMatchers(HttpMethod.GET, "/api/users/all").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/repositories/all").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/repositories/official/all").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/repositories/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/stars/count/{repositoryId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/tags/{repositoryId}").permitAll()
                


                    // ORGANISATION
                    .requestMatchers(HttpMethod.PUT, "/api/organisation/{orgId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/organisation/{id}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/organisation/{orgId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/organisation").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/organisation/user/{userId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/organisation/{orgId}/members").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/organisation/{orgId}/members").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")

                    //TEAMS
                    .requestMatchers(HttpMethod.GET, "/api/team/{orgId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/team").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/team/add_member").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/team/{teamId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")

                    //REPOSITORIES
                    .requestMatchers(HttpMethod.GET, "/api/repositories").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/repositories/{id}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/repositories/all").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/repositories/search").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/repositories").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/repositories/{id}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/repositories/{id}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/repositories/soft/{id}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")

                    //OFFICIAL REPOSITORIES
                    .requestMatchers(HttpMethod.GET, "/api/repositories/official").hasAnyRole( "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/repositories/official/{id}").hasAnyRole( "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/repositories/official/all").hasAnyRole( "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/repositories/official/search").hasAnyRole( "USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/repositories/official").hasAnyRole( "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/repositories/official/{id}").hasAnyRole( "ADMIN", "SUPER_ADMIN")
                    
                    .requestMatchers(HttpMethod.GET, "/api/repositories/organisation/{organisationId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/repositories/filter-by-badge").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")

                    //STARS
                    .requestMatchers(HttpMethod.POST, "/api/stars").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/stars").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/stars/user").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/stars/count/{repositoryId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    

                    //TAGS
                    .requestMatchers(HttpMethod.POST, "/api/tags").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/tags/{repositoryId}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/tags/search").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/tags/{id}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")    


                    //USERS
                    .requestMatchers(HttpMethod.GET, "/api/users").hasAnyRole( "USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole( "USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/users/all").hasAnyRole( "USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/users/search").hasAnyRole( "USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/users/badge/{id}").hasAnyRole(  "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasAnyRole(  "USER", "ADMIN", "SUPER_ADMIN")


                    // STARS
                    .requestMatchers(HttpMethod.POST, "/api/stars").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/stars").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/stars/user").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")


                    // TAGS
                    .requestMatchers(HttpMethod.POST, "/api/tags").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/tags/{repositoryId}").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/tags/search").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/tags/{id}").hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")



                    .requestMatchers(HttpMethod.GET, "/api/users/badge").hasAnyRole(  "USER", "ADMIN", "SUPER_ADMIN")
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
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4201"));
        configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE", "PATCH")); // or simply "*"
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
