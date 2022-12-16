package com.masai.payload.response;

import java.util.List;

import com.masai.model.Role;

import lombok.Data;

@Data
public class UserResponse {
	
	private Integer id;
	
	private String username;
	
	private String email;
	
	private List<String> roles;

	public UserResponse(Integer id, String username, String email, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	
	
	
	

}
