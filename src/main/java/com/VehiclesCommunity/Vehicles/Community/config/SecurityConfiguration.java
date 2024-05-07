package com.VehiclesCommunity.Vehicles.Community.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//Http Methods
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
//Roles
import static com.VehiclesCommunity.Vehicles.Community.user.Role.ADMIN;
import static com.VehiclesCommunity.Vehicles.Community.user.Role.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/api/v1/vehicle/all",
            "/api/v1/vehicle/{id}",
            "/api/v1/vehicle/compare/{car1Id}/{car2Id}",
            "/api/v1/news/all",
            "/api/v1/news/{id}",
            "/api/v1/events/all",
            "/api/v1/events/{id}",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(req ->
                req.requestMatchers(WHITE_LIST_URL)
                    .permitAll()
                    .requestMatchers("/api/v1/wishlist/**").hasAnyAuthority(USER.name())
                    .requestMatchers(POST, "/api/v1/vehicle/all").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(DELETE, "/api/v1/vehicle/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(DELETE, "/api/v1/appointments/add").hasAnyAuthority(USER.name())
                    .requestMatchers(DELETE, "/api/v1/appointments/myAppointments").hasAnyAuthority(USER.name())
                    .requestMatchers(GET, "/api/v1/appointments/all").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(DELETE, "/api/v1/appointments/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(POST, "/api/v1/news/add").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(DELETE, "/api/v1/news/deleteNews/{id}").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(POST, "/api/v1/events/addEvent").hasAnyAuthority(ADMIN.name())
                    .requestMatchers(DELETE, "/api/v1/events/deleteEvent/{id}").hasAnyAuthority(ADMIN.name())
                    .anyRequest()
                    .authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
