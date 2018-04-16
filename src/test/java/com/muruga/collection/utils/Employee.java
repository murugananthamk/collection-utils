package com.muruga.collection.utils;

public class Employee {

	private String fname;
	
	private String lname;
	
	private String dept;
	
	private double salary;
	
	public Employee() {

	}

	public Employee(String fname, String lname, String dept, double salary) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.dept = dept;
		this.salary = salary;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return String.format( "fname=%s, lname=%s, fept=%s, salary=%f", fname, lname, dept, salary );
	}
}
