package com.masai.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.masai.service.UserDetailsServiceImpl;
import com.masai.utitlity.JwtAuthenticationEntryPoint;
import com.masai.utitlity.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	        .exceptionHandling().authenticationEntryPoint(entryPoint).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeRequests().antMatchers("/api/auth/**","/swagger-ui/**").permitAll()
	        .antMatchers("/api/test/**").permitAll()
	        .anyRequest().permitAll();
	    
	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	   
	    return http.build();
	  }

//		@Bean
//		public InMemoryUserDetailsManager userDetails() {
//		
//			InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//		  UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
//		    UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
//		    userDetailsService.createUser(admin);
//		    userDetailsService.createUser(user);
//		    return userDetailsService;
//		}
	  
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		  return authConfig.getAuthenticationManager();
	  }
	  
	  public DaoAuthenticationProvider authenticationProvider() {
		  DaoAuthenticationProvider authProvider  = new DaoAuthenticationProvider();
		  authProvider.setUserDetailsService(detailsServiceImpl);
		  authProvider.setPasswordEncoder(passwordEncoder());
		  
		  return authProvider;
	  }
	  
	  
	  
	 @Bean
	 public BCryptPasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	 }
	 
	 
}
