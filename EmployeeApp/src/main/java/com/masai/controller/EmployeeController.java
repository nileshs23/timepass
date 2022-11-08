package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> helloApp(){
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}
	
//	CRUD OPERATIONS USING JPA
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> registerEmployeeHandler(@Valid @RequestBody Employee emp){
		
		Employee savedObj = empService.registerEmployee(emp);
		
		return new ResponseEntity<Employee>(savedObj,HttpStatus.ACCEPTED);	
	}
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity<Employee> getEmployeeByIdHandler(@Valid @PathVariable("empId") Integer empID) throws EmployeeException{
		
		Employee emp = empService.getEmployeeById(empID);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployeeHandler() throws EmployeeException{
		
		List<Employee> emps = empService.getAllEmployeeDetails();
		
		return new ResponseEntity<List<Employee>>(emps,HttpStatus.OK);
	}
	
	@GetMapping("/byAddress/{addr}")
	public ResponseEntity<List<Employee>> getAllEmployeeByAddressHandler(@PathVariable("addr") String address) throws EmployeeException{
		
		List<Employee> emps = empService.getEmployeeDetailsByAddress(address);
		
		return new ResponseEntity<List<Employee>>(emps,HttpStatus.OK);
	}
	
	
	

}
