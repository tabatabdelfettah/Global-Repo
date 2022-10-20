package com.globale.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.globale.hr.Model.Employee;
import com.globale.hr.Repository.EmployeeRepos;

@Component
public class StarUpProjet implements CommandLineRunner{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EmployeeRepos employeeRepos;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		jdbcTemplate.execute("drop table if exists  hrglobal.employees");
		jdbcTemplate.execute("create table  hrglobal.employees (id serial,name varchar(100),salary numeric(15,2))");
		if((employeeRepos.count() == 0)){
		employeeRepos.insert(new Employee(11L,"tabat",50000.0));
		employeeRepos.insert(new Employee(22L,"tabat2",70000.0));
		employeeRepos.insert(new Employee(33L,"tabat3",80000.0));
		}
		
		
	}

}
