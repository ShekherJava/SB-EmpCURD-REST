package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Employee;

public interface EmpService {
	
	Employee fetchEmpById(int empno);
	List<Employee> fetchAll();
	String addEmp(Employee emp);
	String updateEmp(Employee emp);
	void deleteEmp(int empno);

}
