package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.Employee;

public interface EmpRepository extends CrudRepository<Employee, Integer> {
	
	@Query("UPDATE Employee e SET e.sal = ?1 where e.empno = ?2")
	@Modifying
	@Transactional
	int  partialUpdate(double sal, int empno);

}
