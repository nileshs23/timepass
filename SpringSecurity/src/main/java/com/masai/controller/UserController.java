package com.masai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

	@GetMapping("/welcome")
	public ResponseEntity<String> welcome(){
		String msg ="Welcome to Masai App without security" ;
	 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/welcome2")
	public ResponseEntity<String> welcomeUser(){
		String msg ="Welcome to Masai App with security Authorized" ;
	 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
		
	
}
