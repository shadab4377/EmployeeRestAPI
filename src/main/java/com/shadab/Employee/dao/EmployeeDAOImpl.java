package com.shadab.Employee.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shadab.Employee.entity.EmployeeEntity;
import com.shadab.Employee.model.Employee;

@Repository(value="EmployeeDAO")

public class EmployeeDAOImpl implements EmployeeDAO{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Employee getEmployee(Integer empId) {
		EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class,empId);
		
		Employee employee = new Employee();
		
		if(employeeEntity != null){
		
		employee.setEmpId(employeeEntity.getEmpId());
		employee.setName(employeeEntity.getName());
		employee.setDepartment(employeeEntity.getDepartment());
		employee.setAddress(employeeEntity.getAddress());
		employee.setDob(employeeEntity.getDob());
		employee.setPosition(employeeEntity.getPosition());
		employee.setGender(employeeEntity.getGender());
		employee.setReportsTo(employeeEntity.getReportsTo());
		employee.setSalary(employeeEntity.getSalary());
		employee.setTelephone(employeeEntity.getTelephone());
		
		}
		return employee;
	}

	@Override
	public Integer addEmployee(Employee employee) {
		
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setName(employee.getName());
		employeeEntity.setDepartment(employee.getDepartment());
		employeeEntity.setAddress(employee.getAddress());
		employeeEntity.setDob(employee.getDob());
		employeeEntity.setPosition(employee.getPosition());
		employeeEntity.setGender(employee.getGender());
		employeeEntity.setReportsTo(employee.getReportsTo());
		employeeEntity.setSalary(employee.getSalary());
		employeeEntity.setTelephone(employee.getTelephone());
		employeeEntity.setDatime(new Date());
		
		entityManager.persist(employeeEntity);
		return employeeEntity.getEmpId();
	}
	
	@Override
	public List<Employee> getAllEmployees(){
		List<EmployeeEntity> employeeEntityList = new ArrayList<>();
		Query query = entityManager.createQuery("select e from EmployeeEntity e");
		
		employeeEntityList = query.getResultList();
		if(employeeEntityList.isEmpty())
		return null;
		else{
			List<Employee> employeeList = new ArrayList<>();
			for(EmployeeEntity employeeEntity:employeeEntityList){
				Employee employee = new Employee();
				employee.setEmpId(employeeEntity.getEmpId());
				employee.setName(employeeEntity.getName());
				employee.setDepartment(employeeEntity.getDepartment());
				employee.setAddress(employeeEntity.getAddress());
				employee.setDob(employeeEntity.getDob());
				employee.setPosition(employeeEntity.getPosition());
				employee.setGender(employeeEntity.getGender());
				employee.setReportsTo(employeeEntity.getReportsTo());
				employee.setSalary(employeeEntity.getSalary());
				employee.setTelephone(employeeEntity.getTelephone());
				
				employeeList.add(employee);
			}
			return employeeList;
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		Query query = entityManager.createQuery("UPDATE EmployeeEntity e SET e.name = ?1, e.address = ?2, e.telephone = ?3, e.reportsTo = ?4, e.department = ?5, e.position = ?6, e.salary = ?7 WHERE e.empId = ?8");
		query.setParameter(1,employee.getName());
		query.setParameter(2,employee.getAddress());
		query.setParameter(3,employee.getTelephone());
		query.setParameter(4,employee.getReportsTo());
		query.setParameter(5,employee.getDepartment());
		query.setParameter(6,employee.getPosition());
		query.setParameter(7,employee.getSalary());
		query.setParameter(8,employee.getEmpId());
		
		query.executeUpdate();
	}

	@Override
	public void deleteEmployee(Integer empId) {
		EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, empId);
		if(employeeEntity != null)
			entityManager.remove(employeeEntity);
	}
	
}
