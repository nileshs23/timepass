package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
import com.masai.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> testApi(){
		
		return  new ResponseEntity<String>("Testing Ports",HttpStatus.OK);
	}
	
	@PostMapping("/employee/register")
	public ResponseEntity<Employee> registerEmployeeHandler(@Valid @RequestBody Employee emp) throws EmployeeException{
		
		Employee savedEmp = empService.registerEmployee(emp);
		
		return new ResponseEntity<Employee>(savedEmp,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> getEmployeeByIdHandler(@PathVariable("empId") Integer empid) throws EmployeeException{
		
		Employee returnedEmp  = empService.getEmployeeById(empid);
		
		return new ResponseEntity<>(returnedEmp,HttpStatus.OK);
		
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployeesHandler() throws EmployeeException{
		
		List<Employee> emps = empService.getAllEmployeeDetails();
		
		return new ResponseEntity<List<Employee>>(emps,HttpStatus.OK);
	}
	
	@DeleteMapping("employee/remove/{id}")
	public ResponseEntity<Employee> deleteEmployeeByIdHandler(@PathVariable("id") Integer empId) throws EmployeeException{
		
		Employee emp = empService.deleteEmployeeById(empId);
		
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployeeByIdHandler(@Valid @RequestBody Employee emp) throws EmployeeException {
		
		Employee updatedEmp = empService.updateEmployee(emp);
		
		return new ResponseEntity<Employee>(updatedEmp,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/employees/{addr}")
	public ResponseEntity<List<Employee>> getEmployeeDetailsByAddressHandler(@PathVariable("addr") String address) throws EmployeeException {
		
		List<Employee> emps = empService.getEmployeeDetailsByAddress(address);
		
		return new ResponseEntity<List<Employee>>(emps,HttpStatus.OK);
	}

	@GetMapping("/employee/details/{id}")
	public ResponseEntity<String> getNameAndAddressOfEmpByIdHandler(@PathVariable("id") Integer id) throws EmployeeException{
		
		String response = empService.getNameAndAddressOfEmplyeeById(id);
		
		
		return new ResponseEntity<String>(response.toString(),HttpStatus.OK);
	}
	
	@GetMapping("/employee/login")
	public ResponseEntity<Employee> loginEmployeeHandler(@RequestBody String email, String password) throws EmployeeException{
		
		Employee emp = empService.loginEmployee(email, password);
		
		return new ResponseEntity<Employee>(emp,HttpStatus.ACCEPTED);
		
	}

	@GetMapping("/employee/dto")
	public ResponseEntity<List<EmployeeDTO>> getNameAndAddressDTOHandler() throws EmployeeException{
		
		List<EmployeeDTO> dtos = empService.getNameAddressSalaryOfAllEmployee();
		
		return new ResponseEntity<List<EmployeeDTO>>(dtos,HttpStatus.OK);
	}

	

}
