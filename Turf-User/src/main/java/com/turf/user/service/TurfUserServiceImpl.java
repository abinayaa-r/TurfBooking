package com.turf.user.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turf.user.dto.UserDTO;
import com.turf.user.entity.User;
import com.turf.user.exception.TurfUserException;
import com.turf.user.repository.TurfUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TurfUserServiceImpl implements TurfServiceUser{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	TurfUserRepository repository;
	

	@Override
	public String registerUser(UserDTO userDTO) throws TurfUserException {
		return null;
	}

	@Override
	public UserDTO viewUserDetails(Long phoneNo) throws TurfUserException {

		System.out.println("in serv "+phoneNo);
		Optional<User> optional = repository.findById(phoneNo);
		System.out.println( "in serv 1111 "+ optional);
		User user = optional.orElseThrow(()-> new TurfUserException("SERVICE_USER_NOT_FOUND"));
		
		return modelMapper.map(user, UserDTO.class);
	}

	
}
