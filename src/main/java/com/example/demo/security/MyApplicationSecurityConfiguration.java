package com.example.demo.security;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyApplicationSecurityConfiguration {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/api/hello")
									  .permitAll()
									  .requestMatchers("/api/{id}", "/api/employees")
                                      .hasAnyRole("HR") 
				                      .requestMatchers("/api/update/{id}", "/api/create", "/api/patch/{id}")
				                      .hasAnyRole("ADMIN")
				                      
				).httpBasic(withDefaults());
		http.csrf(csrf -> csrf.disable());
		return  http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder  auth) throws Exception {
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .passwordEncoder(passwordEncoder);
	}

}
