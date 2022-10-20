package com.globale.hr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globale.hr.Model.Employee;
import com.globale.hr.Repository.Impl.EmployeeJDBCRepo;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeJDBCRepo employeeJDBCRepo;

	@GetMapping("/count")
	public int CountEmployees() {

		return employeeJDBCRepo.count();
	}
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {

		return employeeJDBCRepo.findById(id);
	}
	@GetMapping()
	public List<Employee> findAll() {

		return employeeJDBCRepo.FindAll();
	}


}
