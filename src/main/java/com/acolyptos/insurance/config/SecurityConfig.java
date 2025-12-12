package com.acolyptos.insurance.config;

import com.acolyptos.insurance.application.service.AgentDetailsServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AgentDetailsServiceImplementation agentDetailsServiceImplementation;

  public SecurityConfig(
      JwtAuthenticationFilter jwtAuthenticationFilter,
      AgentDetailsServiceImplementation agentDetailsServiceImplementation) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.agentDetailsServiceImplementation = agentDetailsServiceImplementation;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(csrf -> csrf.disable());
    httpSecurity.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    httpSecurity.formLogin(form -> form.disable());
    httpSecurity.httpBasic(Customizer.withDefaults());
    httpSecurity.authorizeHttpRequests(
        authortize ->
            authortize
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .requestMatchers("/api/v1/agent/getAgentByUsername/{username}")
                .hasAnyRole("AGENT", "TESTER")
                // .requestMatchers("/api/v1/auth/login")
                // .permitAll()
                // .requestMatchers("/api/v1/vehicle/retrieveVehicle")
                // .hasAnyRole("AGENT", "TESTER")
                // .requestMatchers("/api/v1/agent/getAgentByUsername")
                // .permitAll()
                .anyRequest()
                .denyAll());
    httpSecurity.authenticationProvider(authenticationProvider());
    httpSecurity.addFilterBefore(
        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider =
        new DaoAuthenticationProvider(agentDetailsServiceImplementation);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
