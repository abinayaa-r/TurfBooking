package com.turf.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.turf.user.entity.User;

public interface TurfUserRepository extends CrudRepository<User, Long> {

	
	public Optional<User> findByName(String userName);
}
