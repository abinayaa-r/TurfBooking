package com.turf.authserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turf.authserver.dto.AuthToken;
import com.turf.authserver.dto.UserDTO;
import com.turf.authserver.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	
	 private final AuthenticationManager authManager;
	    private final JwtUtil jwtUtil;
	    private final UserDetailsService userDetailsService;

	    public AuthController(AuthenticationManager authManager,
	                          JwtUtil jwtUtil,
	                          UserDetailsService userDetailsService) {
	        this.authManager = authManager;
	        this.jwtUtil = jwtUtil;
	        this.userDetailsService = userDetailsService;
	    }

	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody UserDTO userDto){
	    	// first it goes to authentication manager then internally calls userdetailsService and authenticate user
	    	// Then we call the userDetails Service to get the token
	    	System.out.println(" user "+ userDto.getName() + " "+ userDto.getPassword());
	    	Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword());
	    	authManager.authenticate( authentication);
	    	 UserDetails user = userDetailsService.loadUserByUsername(userDto.getName());
	         String token = jwtUtil.generateToken(user);

	         return ResponseEntity.ok(new AuthToken(token));
	    }
}
