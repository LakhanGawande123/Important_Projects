package com.example.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.emp.exception.EmployeeAlreadyExistsException;
import com.example.emp.exception.EmployeeNotFoundException;
import com.example.emp.model.Employee;
import com.example.emp.payload.EmployeeDto;
import com.example.emp.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployeeData(Employee employee) {

		Employee existingEmployee = employeeRepository.findById(employee.getEid()).orElse(null);

		//EmployeeDto employeeDTO = modelMapper.map(existingEmployee, EmployeeDto.class); 
		if (existingEmployee == null) {
			employeeRepository.save(employee);
		} else {
			throw new EmployeeAlreadyExistsException("Employee already exists!!");
		}
		return existingEmployee;
	}

	public List<Employee> getAlldata() {
		List<Employee> emplList = new ArrayList<>();
		employeeRepository.findAll().forEach(emp -> emplList.add(emp));
		return emplList;
	}

	public Employee getSingleEmployeeData(int eid) {
		// TODO Auto-generated method stub
		Optional<Employee> empInfo = employeeRepository.findById(eid);

		Employee employee = null;

		if (empInfo.isPresent()) {

			employee = empInfo.get();

		} else {

			throw new EmployeeNotFoundException("The Employee info is not available for eid: " + eid);

		}

		return employee;
	}
	
	public Employee getEmployeeByCity(String city) {
		
		Employee empInfo = employeeRepository.findByAddressCity(city);

		return empInfo;
	}
	
	public Employee getEmployeeByName_Salary_Age_Department(String name, Double salary, String age, String department) throws EmployeeNotFoundException {
		
		Employee empInfo = employeeRepository.findByNameAndSalaryAndAgeAndDepartment(name, salary, age, department);
		//Employee employee = null;

		if (empInfo.getName().isEmpty() && empInfo.getAge().isEmpty() && empInfo.getDepartment().isEmpty()) {

			throw new EmployeeNotFoundException("The Employee info is not available ");

		}

		return empInfo;
	}

	public Employee updateEmployeeData(Employee employee) {

		Employee existingEmployee = employeeRepository.findById(employee.getEid()).orElse(null);

		if (existingEmployee == null) {
			throw new EmployeeNotFoundException("No Such Employee exists!!");
		} else
			existingEmployee.setName(employee.getName());
		existingEmployee.setSalary(employee.getSalary());
		Employee employee1 = employeeRepository.save(existingEmployee);

		return employee1;
	}
	
	public String deleteEmployeebyNameAndEid(String name, int eid) {
		// TODO Auto-generated method stub
		employeeRepository.deleteEmployeesByNameAndEid(name, eid);
		return "Employee removed !! " + name + eid;
	}

	public String deleteEmployeeData(int eid) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(eid);
		return "product removed !! " + eid;
	}

//	public String deleteEmployeebyName(String name) {
//		// TODO Auto-generated method stub
//		employeeRepository.deleteByName(name);
//		return "product removed !! " + name;
//	}

	public Employee documentData(Employee employee, int eid, String name, String jobType) {

		return employeeRepository.save(employee);
	}

	public Employee updateEmployeeDetails(Employee employee) {
		Employee existingDetails = getSingleEmployeeData(employee.getEid());
//		if (employee.getName() != null) {
//			existingDetails.setName(employee.getName());
//		}
		if (employee.getSalary() != 0.0) {
			existingDetails.setSalary(employee.getSalary());
		}
		if (employee.getJobRole() != null) {
			existingDetails.setJobRole(employee.getJobRole());
		}
		return employeeRepository.save(existingDetails);
	}

}
