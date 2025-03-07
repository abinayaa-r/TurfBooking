package com.turf.user.service;

import com.turf.user.dto.UserDTO;
import com.turf.user.exception.TurfUserException;

public interface TurfServiceUser {
	
	public String registerUser(UserDTO userDTO) throws TurfUserException;
	public UserDTO viewUserDetails(Long phoneNo)throws TurfUserException;

	

}
