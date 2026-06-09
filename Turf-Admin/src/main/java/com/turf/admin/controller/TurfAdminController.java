package com.turf.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.turf.admin.dto.UserDTO;
import com.turf.admin.exception.TurfAdminException;
import com.turf.admin.utility.ErrorInfo;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/admin")
public class TurfAdminController {



	
	@PostMapping(value="/user/register")
	public Mono<ResponseEntity<String>> registerUserDetails(@RequestBody UserDTO userDTO)throws TurfAdminException{
		
//		WebClient webclient = WebClient.create();
		// proper way to give url is like...."http://"+ [fileUtils.getValuefromProperties -> host, method name separately]
		return WebClient.create()
		        .post()
		        .uri("http://localhost:9000/turf/user/details/register")
		        .bodyValue(userDTO)
		        .exchangeToMono(response -> 
	            response.bodyToMono(String.class)
	                .map(body -> ResponseEntity.status(response.statusCode()).body(body))
	        );


//		        .bodyToMono(String.class)
//		        .map(response -> ResponseEntity.status(HttpStatus.ACCEPTED).body(response));

 	}
	
}
