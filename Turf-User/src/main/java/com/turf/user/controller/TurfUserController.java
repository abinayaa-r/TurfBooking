package com.turf.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turf.user.dto.UserDTO;
import com.turf.user.exception.TurfUserException;
import com.turf.user.service.TurfServiceUser;

@RestController
@RequestMapping("/user")
public class TurfUserController {

	@Autowired
	TurfServiceUser turfService;
	
	@GetMapping(value ="/details",produces = "application/json")
	public ResponseEntity<UserDTO> viewUserDetails(@RequestParam Long phoneNo)throws TurfUserException{	
		return new ResponseEntity<UserDTO>(turfService.viewUserDetails(phoneNo), HttpStatus.OK);
		
	}
}
