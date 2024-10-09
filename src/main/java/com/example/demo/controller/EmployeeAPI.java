package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Employee;
import com.example.demo.service.EmpService;

@RestController
@RequestMapping("/api")
public class EmployeeAPI {
	
	@Autowired
	EmpService service;
	
	//produces: defines the type of data, also called media type,
	//the method will return in the response.
	@GetMapping( value = "/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee getEmployeeById(@PathVariable int id) {
		return service.fetchEmpById(id);
	}
	
	@GetMapping(value = "/employees", produces = "application/json" )
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees() {
		return service.fetchAll();
	}
	
	//consumes: defines the type of data, the method can accept
	//in the request body
	@PostMapping(value = "/add", consumes = "application/json" )
	@ResponseStatus(HttpStatus.CREATED)
	public String addEmployee(@RequestBody Employee e) {
		return service.addEmp(e);
	}
	

}
