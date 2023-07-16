package com.example.emp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp.model.Employee;
import com.example.emp.repo.EmployeeRepository;
import com.example.emp.service.EmployeeService;

@RestController
//@Validated
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository lRepo;

	// add new Employee
	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		Employee employeeCreated = employeeService.saveEmployeeData(employee);
		return new ResponseEntity<Employee>(employeeCreated, HttpStatus.CREATED);
		
		//return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);

	}

	// get All employee
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployees() {
		return employeeService.getAlldata();
	}

	// get particular employee
	@GetMapping("/getEmployee/{eid}")
	public Employee getSingleEmployee(@PathVariable("eid") int eid) {
		Employee employee = employeeService.getSingleEmployeeData(eid);
		return employee;
	}
	
	@GetMapping("/getEmployee/{name}/{salary}/{age}/{department}")
	public ResponseEntity<Employee> getEmployeeByNameSalaryAgeDepartment(@PathVariable("name") String name,
			@PathVariable("salary") Double salary, @PathVariable("age") String age, @PathVariable("department") String department) {

		Employee employee = employeeService.getEmployeeByName_Salary_Age_Department(name, salary, age, department);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	// update employee data
	@PutMapping("/employeeUpdate")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployeeData(employee);
		//return "Employee data updated successfully...........";
	}

	// delete employee data
	@DeleteMapping("/delete/{eid}")
	public String deleteEmployee(@PathVariable("eid") int eid) {

		return employeeService.deleteEmployeeData(eid);
	}
	
	@DeleteMapping("/delete/{name}/{eid}")
	public String deleteEmployee1(@PathVariable("name") String name, @PathVariable("eid") int eid) {

		return employeeService.deleteEmployeebyNameAndEid(name, eid);
	}


	// update employee data
	@PutMapping("/employeeUpdate1/{eid}/{name}")
	public String updateSpecificEmployee(@RequestBody Employee employee, @PathVariable int eid, @PathVariable String name) {
		employeeService.updateEmployeeDetails(employee);
		return "Employee dat updated successfully...........";
	}

	// Update employee by name & eid
	@PutMapping("/Employee/update/{salary}/{name}/{eid}")
	public ResponseEntity<String> updatePriceByName(@PathVariable double salary, @PathVariable String name,
			@PathVariable int eid) {
		return new ResponseEntity<String>(lRepo.updateSalaryByName(salary, name, eid) + " record(s) updated.",
				HttpStatus.OK);
	}

}
