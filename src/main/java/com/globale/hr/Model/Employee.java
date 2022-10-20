package com.globale.hr.Model;

public class Employee {

	private long id;
	private String name;
	private double salary;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(long id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

}
