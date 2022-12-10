package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.service.MyCustomerService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	MyCustomerService customerService;

	@GetMapping("/welcome")
	public ResponseEntity<String> welcome(){
		String msg ="Welcome to Masai App without security" ;
	 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/welcome")
	public ResponseEntity<String> welcomeUser(){
		String msg ="Welcome to Masai App with security Authorized" ;
	 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
		
	
	@GetMapping("/user")
	public ResponseEntity<String> welcomeP(){
		String msg = "Welcome to Masai App with Security" ;
			return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDetails> register(@Valid @RequestBody Customer cust){
		UserDetails user = customerService.createUser(cust);
			return new ResponseEntity<UserDetails>(user,HttpStatus.CREATED);
	}

	@GetMapping("/admin/welcome")
	public ResponseEntity<String> admin(){
		String msg = "Welcome to Masai App for Admin";
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
		}
	
}
