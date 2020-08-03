package com.shadab.Employee.service;

import java.util.List;

import com.shadab.Employee.model.Employee;

public interface EmployeeService {
	public Employee getEmployee(Integer empId);
	public Integer addEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Integer empId);
	public List<Employee> getAllEmployees();
}
