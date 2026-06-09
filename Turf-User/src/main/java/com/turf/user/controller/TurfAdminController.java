package com.turf.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TurfAdminController {
	
	//this is just for the jwt understanding
	@GetMapping
	public ResponseEntity<String> getMap(){
		return new ResponseEntity<>("Hi Admin" , HttpStatus.OK);
	}

}
