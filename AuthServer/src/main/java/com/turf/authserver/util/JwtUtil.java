package com.turf.authserver.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	
	public String generateToken(UserDetails userDetails) {
		
		/* we have mentioned about userDetails and set the usernam and pwd in MyUserDetailsService class by getting the
			username(from userdto) from the other service 
		
		*/
		 Map<String, Object> claims = new HashMap<>();
	        claims.put("roles", userDetails.getAuthorities().stream()
	                        .map(GrantedAuthority::getAuthority)
	                        .collect(Collectors.toList()));
		System.out.println();
		return Jwts.builder()
					.setSubject(userDetails.getUsername())
					.claim("roles",  userDetails.getAuthorities()
					        .stream()
					        .map(a -> a.getAuthority())
					        .toList())
					.setIssuedAt(new Date())
					 .setExpiration(new Date(System.currentTimeMillis() + expiration))
					 .signWith(Keys.hmacShaKeyFor(secret.getBytes()),SignatureAlgorithm.HS256)
					 .compact();
	}
}
