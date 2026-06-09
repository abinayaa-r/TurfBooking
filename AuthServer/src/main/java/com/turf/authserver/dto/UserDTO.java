package com.turf.authserver.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long phoneNo;
	private String name;
	private String password;
	private String role;

}
