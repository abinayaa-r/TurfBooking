package com.turf.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.turf.user.entity.User;

public interface TurfUserRepository extends CrudRepository<User, Long> {

}
