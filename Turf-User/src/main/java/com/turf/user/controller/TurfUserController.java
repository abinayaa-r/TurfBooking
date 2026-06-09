package com.turf.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	Environment environment;
	
	//@PreAuthorize("hasAnyRole('admin','user')")
	@GetMapping(value ="/details",produces = "application/json")
	public ResponseEntity<UserDTO> viewUserDetails(@RequestParam Long phoneNo)throws TurfUserException{	
		System.out.println(" 333333333 UserController ---------------- "+ phoneNo.TYPE + " "+ phoneNo);
		return new ResponseEntity<UserDTO>(turfService.viewUserDetails(phoneNo), HttpStatus.OK);
		
	}
	
	@GetMapping(value ="/detailsByName",produces = "application/json")
	public ResponseEntity<UserDTO> viewUserDetails(@RequestParam String userName)throws TurfUserException{	
		System.out.println("  ToGetUserDetails UserController------------------"+ userName);
		return new ResponseEntity<UserDTO>(turfService.viewByUsername(userName), HttpStatus.OK);
		
	}
	
	@PostMapping(value ="/details/register",produces = "application/json")
	public ResponseEntity<String> registerUserDetails(@RequestBody UserDTO userDTO)throws TurfUserException{	
		
		return new ResponseEntity<String>(environment.getProperty("USER_REGISTER_SUCCESSFULL") +"\n"+ turfService.registerUser(userDTO), HttpStatus.OK);
		
	}
	


	
}
