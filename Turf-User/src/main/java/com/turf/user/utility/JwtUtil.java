//package com.turf.user.utility;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtUtil {
//	
//	   @Value("${jwt.secret}")
//	    private String secret;
//	   
//	   //claims have the jwt data like username, role, expiration
//	   public Claims extractClaims(String token) {
//		   System.out.println("--------UserJwt Util------------ExtractClaims");
//		   return Jwts.parserBuilder()
//				   		.setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))    // to verify token is not tampered
//				   		.build()
//				   		.parseClaimsJws(token)						// create a jwt parser like I want to read and verify jwt
//				   		.getBody();
//	   }
//
//	   
//	   public String getUsername(String token) {
//		   System.out.println("--------UserJwt Util------------getUserName");
//
//		   return extractClaims(token).getSubject();
//	   }
//	   
//	   public List<String> getRoles(String token){
//		   System.out.println("--------UserJwt Util------------getRoles");
//
//		   return extractClaims(token).get("roles",List.class);
//	   }
//	   
//	   public boolean isValid(String token) {
//		   try {
//			   System.out.println("--------UserJwt Util-----------isValid");
//
//			   extractClaims(token);
//			   return true;
//		   }catch(Exception e) {
//			   return false;
//		   }
//	   }
//}
