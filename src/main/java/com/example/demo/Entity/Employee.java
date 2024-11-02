package com.example.demo.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Employee model")
public class Employee {
	
	@Schema(description = "Unique ID of the employee", example ="7101")
	@Id
	private Integer empno;
	
	@Schema(description = "Name of the employee")
	private String ename;
	
	private Double sal;
	
	private Integer deptno;

}
