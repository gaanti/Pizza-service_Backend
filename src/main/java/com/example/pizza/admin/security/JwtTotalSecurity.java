package com.example.pizza.admin.security;

import com.example.pizza.admin.security.user.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class JwtTotalSecurity {
	@Autowired
	private AuthenticationManager authenticationManager;
	private final AuthSuccessHandler authSuccessHandler;
	private final JwtUserDetailsService jwtUserDetailsService;
	private final String secret;

	public JwtTotalSecurity(AuthSuccessHandler authSuccessHandler, JwtUserDetailsService jwtUserDetailsService, @Value("${jwt.secret}") String secret) {
		this.authSuccessHandler = authSuccessHandler;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.secret = secret;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.cors()
				.and()
				.csrf()
				.disable()
				.formLogin(withDefaults())
				.authorizeHttpRequests(auth -> {
					try {
						auth
								.antMatchers("/admin").hasRole("ADMIN")
								.antMatchers("/user").hasRole("USER")
								.anyRequest().permitAll()
								.and()
								.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
								.and()
								.formLogin(withDefaults())
								.addFilter(authenticationFilter())
								.addFilter(new JsonAuthorizationFilter(authenticationManager, jwtUserDetailsService, secret))
								.exceptionHandling()
								.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				})
				.httpBasic(withDefaults());
		return http.build();
	}

	@Bean
	public JsonObjectAuthenticationFilter authenticationFilter() {
		JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(authSuccessHandler);
		filter.setAuthenticationManager(authenticationManager);
		return filter;
	}
}
