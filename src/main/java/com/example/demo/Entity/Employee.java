package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="EMP")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
	@Id
	private Integer empno;
	
	private String ename;
	
	private Double sal;
	
	private Integer deptno;

}
