package com.turf.user.configuration;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.turf.user.utility.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//SecurityFilterChain decides IF a request reaches your controller
	//@PreAuthorize decides WHICH METHOD can execute
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
	
//	private final JwtAuthFilter jwtAuthFilter;
//
//    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
//        this.jwtAuthFilter = jwtAuthFilter;
//    }
    
	 @Value("${jwt.secret}")
	    private String secret;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	System.out.println(" securittttttyyyyyyyyyy Filter Chain User-----------");
    	 http.csrf(csrf -> csrf.disable())
         .sessionManagement(s ->
             s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/ping").permitAll()
             .requestMatchers("/user/details/register").permitAll()
            // .requestMatchers("/user/detailsByName").permitAll()         -- this will allow without the authentication & why we didn't give hasRole?
             // JWT from Auth server calls this endpoint and verify user and provide jwt.... so if we call HasRole method then it will also need 
             //authentication then the jwt provider will be 401
             .requestMatchers("/user/detailsByName").permitAll()
             .requestMatchers("turf/user/details").hasAnyRole("('admin',user')")

             .requestMatchers("/admin/**").hasRole("admin")
             .anyRequest().authenticated()
         )
         .oauth2ResourceServer(oauth2 -> oauth2
                 .jwt(jwt -> jwt
                     .decoder(NimbusJwtDecoder.withSecretKey(
                         new SecretKeySpec(secret.getBytes(), "HmacSHA256")
                     ).build())
                     .jwtAuthenticationConverter(jwtAuthenticationConverter())
                 )
             );
         
         //only for the JWT below...not for oauth
//         .addFilterBefore(jwtAuthFilter,
//             UsernamePasswordAuthenticationFilter.class);
    	 
    	    return http.build();

    }
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("roles");
        converter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtAuthConverter;
    }
    /*  for oauth
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception{
		
		
		httpSec.csrf(s->s.disable())
				.authorizeHttpRequests(auth -> auth
													.requestMatchers("/user").hasAnyRole("admin","user")
													.requestMatchers("/admin").hasRole("admin")
													.anyRequest().authenticated())
				.oauth2ResourceServer(oauth-> oauth.jwt()); // it decodes the secret key and checks the token. secretkey in app.prop
		
		return httpSec.build();
	}
*/
}
