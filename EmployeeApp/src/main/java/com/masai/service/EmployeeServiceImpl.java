package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;
	
	@Override
	public Employee registerEmployee(Employee employee) {
		
		Employee savedEmp = empRepo.save(employee);
		
		return savedEmp;
	}

	@Override
	public Employee getEmployeeById(Integer empId) throws EmployeeException{
		
		return empRepo.findById(empId).
				orElseThrow(() -> new EmployeeException("Student Does Not Exist !"));
		
	}

	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeException {
		
		List<Employee> employees = empRepo.findAll();
		
		if(employees.size()==0) {
			throw new EmployeeException("Employee Data is Empty !");
		}else {
			return employees;
		}
		
	}

	@Override
	public List<Employee> getEmployeeDetailsByAddress(String addr) throws EmployeeException {
		
		List<Employee> emps = empRepo.findByAddress(addr);
		
		if(emps.size()==0) {
			throw new EmployeeException("Employee Data is Empty !");
		}else {
			return emps;
		}
	}

}
