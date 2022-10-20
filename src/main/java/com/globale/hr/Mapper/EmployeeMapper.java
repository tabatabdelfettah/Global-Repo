package com.globale.hr.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globale.hr.Model.Employee;

public class EmployeeMapper  implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return  new Employee(rs.getLong("id"),rs.getString("name"),rs.getDouble("salary"));
	}

}
