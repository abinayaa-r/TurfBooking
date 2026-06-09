//package com.turf.user.utility;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String uri = request.getRequestURI();
//
//        return uri.startsWith("/turf/ping")
//            || uri.startsWith("/turf/user/detailsByName")
//            || "OPTIONS".equalsIgnoreCase(request.getMethod());
//    }
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		System.out.println(" in filllllllllter-------------- "+ request.getServletPath());
//		String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        String token = authHeader.substring(7);
//			
//			System.out.println(" in token filter-------- "+ token);
//			
//			if (jwtUtil.isValid(token)) {
//				
//				// here it extracts the username , roles from the token
//			String username = jwtUtil.getUsername(token);
//			List<String> roles = jwtUtil.getRoles(token);
//			
//			 List<GrantedAuthority> authorities = roles.stream()
//                          								.map(r -> new SimpleGrantedAuthority("ROLE_" + r))
//                          								.collect(Collectors.toList());
//			 
//			 Authentication auth =
//                     new UsernamePasswordAuthenticationToken(username, null, authorities);
//			
//			  SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//			
//		
//		filterChain.doFilter(request, response);
//	}
//
//}
