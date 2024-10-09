package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Employee;

public interface EmpRepository extends CrudRepository<Employee, Integer> {

}
