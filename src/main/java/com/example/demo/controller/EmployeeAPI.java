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
	@PostMapping(value = "/create", consumes = "application/json" )
	@ResponseStatus(HttpStatus.CREATED)
	public String addEmployee(@RequestBody Employee e) {
		return service.addEmp(e);
	}
	
	@PutMapping( value = "/{id}", consumes = "application/json")
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
	
	@PatchMapping("/{id}")
	public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable int id, @RequestBody Map<String, Object> updates) {
		double salTobeUpdated = (double) updates.get("sal");
		Employee e = service.partialUpdate(salTobeUpdated, id);
		if ( e == null ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(e, HttpStatus.OK);
		
	}

}
