package com.shadab.Employee.dao;

import java.util.List;

import com.shadab.Employee.model.Employee;

public interface EmployeeDAO {
	public Employee getEmployee(Integer empId);
	public Integer addEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Integer empId);
	public List<Employee> getAllEmployees();
}
