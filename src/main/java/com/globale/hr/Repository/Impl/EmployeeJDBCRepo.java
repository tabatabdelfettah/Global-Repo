package com.globale.hr.Repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.globale.hr.Mapper.EmployeeMapper;
import com.globale.hr.Model.Employee;
import com.globale.hr.Repository.EmployeeRepos;

@Component
public class EmployeeJDBCRepo implements EmployeeRepos {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {

		return jdbcTemplate.queryForObject("SELECT count(*) FROM  hrglobal.employees;", Integer.class);
	}

	@Override
	public Employee findById(long id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("SELECT id ,name,salary FROM hrglobal.employees where id= ?",
				new Object[] { id }, new EmployeeMapper()

		);
	}

	@Override
	public List<Employee> FindAll() {
		return jdbcTemplate.query("SELECT id ,name,salary FROM hrglobal.employees ", new EmployeeMapper());

		
	}

	@Override
	public int insert(Employee employee) {
		
		return jdbcTemplate.update("insert into hrglobal.employees(id,name,salary) values(?,?,?)",
				new Object[] {employee.getId(),employee.getName(),employee.getSalary()});
	}

	@Override
	public int update(Employee employee) {
		
		return jdbcTemplate.update("update hrglobal.employees set name= ?,salary= ?",
				new Object[] {employee.getName(),employee.getSalary()});
	}

	@Override
	public int delete(long id) {

		return jdbcTemplate.update("delete from  hrglobal.employees where id= ?",
				new Object[] {id});
	}

}
