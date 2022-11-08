package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.EmployeeException;
import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;
import com.masai.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;

	@Override
	public Employee registerEmployee(Employee emp) throws EmployeeException {
		
		Employee savedEmp = empRepo.save(emp);
		
		return savedEmp;
		
	}

	@Override
	public Employee getEmployeeById(Integer empId) throws EmployeeException {
	
		return empRepo.findById(empId)
						.orElseThrow(() -> new EmployeeException("Employee with id: "+ empId+ " Does not Exist !"));
		
		
	}

	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeException {
		
		List<Employee> employees =  empRepo.findAll();
		
		if(employees.size()==0) {
			throw new EmployeeException("Register Employees First !");
		}else {
			return employees;
		}
		
		
	}

	@Override
	public Employee deleteEmployeeById(Integer empId) throws EmployeeException {
		
		Optional<Employee> opt = empRepo.findById(empId);
		
		if(opt.isPresent()) {
			
			Employee existedEmp = opt.get();
			empRepo.delete(existedEmp);
			
			return existedEmp;
			
		}else {
			throw new EmployeeException("Employee with id:"+ empId+" Does not Exist !)");
		}
		
	}

	@Override
	public List<Employee> getEmployeeDetailsByAddress(String address) throws EmployeeException {
		
		List<Employee> emps = empRepo.findByAddress(address);
		
		if(emps.size()==0) {
			throw new EmployeeException("No Employees Found !");
		}else {
			return emps;
		}
		
	}

	@Override
	public Employee updateEmployee(Employee emp) throws EmployeeException {
		
		 Optional<Employee> opt = empRepo.findById(emp.getEmpId());
		 
		 if(opt.isPresent()) {
			 
			 Employee updatedEmp = empRepo.save(emp);
			 return updatedEmp;
			 
		 }else {
			 throw new EmployeeException("Employee with id:"+ emp.getEmpId()+" Does not Exist !)");
		 }
		
	}

	@Override
	public String getNameAndAddressOfEmplyeeById(Integer empId) throws EmployeeException {
		
		Employee emp = empRepo.getNameAndAddressById(empId);
		
		if(emp != null) {
			String name = emp.getEmpName();
			String address = emp.getAddress();
			
			return name+address+"";
		}else {
			throw new EmployeeException("Employee with id:"+ empId+" Does not Exist !)");
		}
		
	}

	@Override
	public Employee loginEmployee(String email, String password) throws EmployeeException {
		 
		Employee emp = empRepo.login(email, password);
		
		if(emp != null) {
			return emp;
		}else {
			throw new EmployeeException("Check Your Login Details");
		}
	}

	@Override
	public List<EmployeeDTO> getNameAddressSalaryOfAllEmployee() throws EmployeeException {
		
		
		
		List<EmployeeDTO> empDTO =  empRepo.getNameAddressSalaryOfAllEmployee();
		
		if(empDTO.size()==0) {
			throw new EmployeeException("Register Employees First !");
		}else {
			return empDTO;
		}
		
	}
	
	
}
