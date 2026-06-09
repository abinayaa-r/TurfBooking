package com.turf.authserver.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.turf.authserver.dto.UserDTO;
import com.turf.authserver.util.JwtUtil;

@Service
public class MyUserDetailsService implements UserDetailsService {


	

//    WebClient client = WebClient.builder()
//            .baseUrl("http://localhost:9000")
//            .build();
	
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	RestTemplate template;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// since we have the details in one get mapping using phoneno...but it accepts only string...
		// so i sent phone number as string.. now changing it into long
		System.out.println(" 11111111111 "+ username);
		
		
//	    UserDTO userDetails = client.get()
//	            .uri(uriBuilder -> uriBuilder
//	                    .path("/turf/user/details")
//	                    .queryParam("phoneNo", phoneNo)
//	                    .build())
//	            .retrieve()
//	            .bodyToMono(UserDTO.class)
//	            .block();
		
		

	

		UserDTO userDetails = template.getForObject("http://localhost:9000/turf/user/detailsByName?userName="+username, UserDTO.class);
		System.out.println(" 22222222222 "+ userDetails.toString());

		return User.withUsername(userDetails.getName())
				.password(userDetails.getPassword())
				.roles(userDetails.getRole())      // this will be stored as list of authorities & in jwtUtil we get by this and put in claims method
				.build();
	}

}
