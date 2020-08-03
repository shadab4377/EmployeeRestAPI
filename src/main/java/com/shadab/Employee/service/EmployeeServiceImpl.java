package com.shadab.Employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shadab.Employee.dao.EmployeeDAO;
import com.shadab.Employee.model.Employee;

@Service(value="EmployeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDAO employeeDAO;
	@Override
	public Employee getEmployee(Integer empId) {
		Employee employee = employeeDAO.getEmployee(empId);
		return employee;
	}

	@Override
	public Integer addEmployee(Employee employee) {
		Integer id = employeeDAO.addEmployee(employee);
		return id;
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(Integer empId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = employeeDAO.getAllEmployees();
		return employeeList;
	}

}
