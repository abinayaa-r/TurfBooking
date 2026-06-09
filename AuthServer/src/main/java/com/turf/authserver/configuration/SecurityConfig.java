package com.turf.authserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//SecurityFilterChain decides IF a request reaches your controller
	/*
	 * It runs before your controller is even touched.
				It handles:
							JWT validation
							Authentication (who is the user?)
							CSRF
							Session / stateless config
							CORS
							Which URLs are public
								 * 
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		// we disable csrf bcoz it is meant for browser based security , session + form logins not stateless API's
		/* csrf is a attach where user is already logged in & browser automatically sends cookies. 
			A malicious site tricks the browser into calling your API. Because cookies are sent automatically, the server thinks
			the request is from valid user
		*/
		
		// here we don't use session and form login so we are disabling it .& Authentication done with jwt, oauth2, & bearer token
		httpSecurity.csrf(csr-> csr.disable())
					.authorizeHttpRequests(auth -> auth
							.requestMatchers("/auth/login").permitAll()
							.anyRequest().authenticated())
					   .httpBasic(Customizer.withDefaults());;
		
		return httpSecurity.build();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		
		return config.getAuthenticationManager();
		
	}
}
