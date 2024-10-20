package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Employee;
import com.example.demo.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	EmpRepository repo;

	@Override
	public Employee fetchEmpById(int empno) {
		Optional<Employee>  opt = repo.findById(empno);
		if ( opt.isPresent() ) {
			return opt.get();
		}
		return null;
	}

	@Override
	public List<Employee> fetchAll() {
		return (List<Employee>) repo.findAll();
	}

	@Override
	public String addEmp(Employee emp) {
		try {
			repo.save(emp);
			return "Employee is added successfully";
		}
		catch(Exception ex) {
			return "Employee already exists";
		}
	}

	@Override
	public Employee updateEmp(Employee emp) {
		try {
			Employee e = repo.save(emp);
			return e;
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public void deleteEmp(int empno) {
		repo.deleteById(empno);
	}
	
	@Override
	public Employee partialUpdate(double sal, int empno) {
		int count = repo.partialUpdate(sal, empno);
		if(count == 0)
			return null;
		else
			return repo.findById(empno).get();
	}

}
