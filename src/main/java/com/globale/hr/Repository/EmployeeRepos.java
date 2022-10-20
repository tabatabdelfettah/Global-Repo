package com.globale.hr.Repository;

import java.util.List;

import com.globale.hr.Model.Employee;

public interface EmployeeRepos {

	int count();

	Employee findById(long id);

	List<Employee> FindAll();

	int insert(Employee employee);

	int update(Employee employee);

	int delete(long id);

}
