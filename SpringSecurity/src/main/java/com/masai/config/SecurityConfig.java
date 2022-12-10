package com.masai.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	  @Bean
	  public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
	
			http.authorizeHttpRequests( (auth)->auth
					.requestMatchers("/welcome","/register").permitAll()
				.requestMatchers("/user/**").authenticated()
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
					
			).csrf().disable()
			.httpBasic();
	
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
	 public PasswordEncoder PasswordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	 }
	 
	 
}
