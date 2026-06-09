package com.turf.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	private Long phoneNo;
	private String name;
	private String password;
	private String Address;
	private String role;
	
	
}
