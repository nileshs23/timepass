package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.model.Employee;
import com.masai.model.EmployeeDTO;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	@Query("select e from Employee e WHERE e.address =:addr")
	public List<Employee> findByAddress(@Param("addr") String address);
	
	@Query("select e.empName,e.address from Employee e WHERE e.empId=:id")
	public Employee getNameAndAddressById(@Param("id") Integer empId);
	
	@Query("select e.empName,e.address,e.salary from Employee e")
	public List<EmployeeDTO> getNameAddressSalaryOfAllEmployee();
	
	@Query("select e from Employee e WHERE e.email=:mail AND e.password=:pass")
	public Employee login(@Param("mail") String email,@Param("pass") String password);
	
	
}
