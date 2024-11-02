package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Employee;
import com.example.demo.service.EmpService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name ="employees", description = "Operations related to employees")
@RestController
@RequestMapping("/api")
public class EmployeeAPI {
	
	@Autowired
	EmpService service;
	
	@GetMapping( value = "/hello")
	public String sayHello() {
		return "hello";
	}
	
	@Operation(summary = "Get employee by ID", description = "Fetch employee's details by its ID")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = " Employee successfully retrieved"),
					@ApiResponse(responseCode = "404", description = "Employee not found")
			})
	//produces: defines the type of data, also called media type,
	//the method will return in the response.
	@GetMapping( value = "/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee getEmployeeById(@Parameter(description="ID of the employee to retrieve") @PathVariable int id) {
		return service.fetchEmpById(id);
	}
	
	@GetMapping(value = "/employees", produces = "application/json" )
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees() {
		return service.fetchAll();
	}
	
	@Operation(summary = "Create a new employee", description = "creates a new employee by accepting data in json format")
	//consumes: defines the type of data, the method can accept
	//in the request body
	@PostMapping(value = "/create", consumes = "application/json" )
	@ResponseStatus(HttpStatus.CREATED)
	public String addEmployee(@RequestBody Employee e) {
		return service.addEmp(e);
	}
	
	@PutMapping( value = "/update/{id}", consumes = "application/json")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
		Employee existingEmployee = service.fetchEmpById(id);
		if ( existingEmployee == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//replace the existing employee date with the updated data
		updatedEmployee.setEmpno(id);
		Employee e = service.updateEmp(updatedEmployee);
		return new ResponseEntity<>(e, HttpStatus.OK);	
	}
	
	@PatchMapping("/patch/{id}")
	public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable int id, @RequestBody Map<String, Object> updates) {
		double salTobeUpdated = (double) updates.get("sal");
		Employee e = service.partialUpdate(salTobeUpdated, id);
		if ( e == null ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
		
	}
	
	@Hidden
	@GetMapping("/internal")
	public String internalEndpoint() {
		return "This is internal endpoint";
	}

}
