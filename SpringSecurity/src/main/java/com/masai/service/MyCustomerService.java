package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.config.MySecurityUser;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

@Service
public class MyCustomerService implements UserDetailsService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Customer> customers = customerRepository.findByUsername(username);
		System.out.println(customers);
		if(customers.size()>0) {
			return new MySecurityUser(customers.get(0));
		}else {
			throw new UsernameNotFoundException(username+" user Not Found !");
		}	
	}
	
	public UserDetails createUser(Customer customer) {
		
		customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
		
		return new MySecurityUser(customerRepository.save(customer));
	}

}
