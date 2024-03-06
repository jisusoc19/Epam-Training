package com.task3.security;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.task3.service.JWT.IJwtService;


import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private IJwtService jwtservice;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return  http
				 .csrf(csrf ->
				   csrf.disable()
						)
				 .authorizeHttpRequests(autRequest ->
				 	autRequest
				 	 .requestMatchers("/auth/**").permitAll()
				 	 .requestMatchers("/doc/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**").permitAll()
				 	 .requestMatchers("/h2/**","/h2-console").permitAll()
				 	 .requestMatchers(HttpMethod.POST,"/apitrainer/**").permitAll()
				 	 .requestMatchers(HttpMethod.POST,"/apitrainee/**").permitAll()
				 	 .anyRequest().authenticated()
					 
				)
				.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
				.sessionManagement(sessionManager ->
					sessionManager
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						)
				.addFilterBefore(new JWTAuthorizationFilter(jwtservice),UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(exceptionHandling->
					exceptionHandling
						  .authenticationEntryPoint((request ,response , authException)->{
										response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
							}))
				.build();
				
							
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
}
