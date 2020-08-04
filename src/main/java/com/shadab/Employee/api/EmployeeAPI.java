package com.shadab.Employee.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shadab.Employee.model.Employee;
import com.shadab.Employee.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("EmployeeAPI")
public class EmployeeAPI {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Environment environment;	
	
	@GetMapping(value="getEmployee/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("empId") Integer empId){
		
		try{
			
		Employee employee = employeeService.getEmployee(empId);
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		
		}
		catch(Exception e){
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="getAllEmployees/")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		try{
			
		List<Employee> employeeList = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.OK);
		
		}
		catch(Exception e){
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value="addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) throws Exception{
		Integer empId;
		String message = "Employee details added succesfully with employee id: ";
		try{
			empId=employeeService.addEmployee(employee);
			message = message+empId;
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
		}
	}
	
	@DeleteMapping(value="deleteEmployee/{empId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("empId") Integer empId ){
		String message = "Employee deleted successfully with employee id: "+empId;
		try{
			employeeService.deleteEmployee(empId);
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
		}
	}

	
	@PatchMapping(value="updateEmployee/{empId}")
	public ResponseEntity<String> updateEmployee(@PathVariable("empId") Integer empId, @RequestBody Employee employee ){
		String message = "Employee updated successfully with employee id: "+empId;
		try{
			employeeService.updateEmployee(employee);
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
		}
}
}

