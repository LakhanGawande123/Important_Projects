package com.nodeservice.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.nodeservice.dto.Employee;
import com.nodeservice.exception.EmployeeCreationException;
import com.nodeservice.exception.NoRecordsFoundException;
import com.nodeservice.exception.RequestParamException;
import com.nodeservice.model.TEmployee;
import com.nodeservice.model.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#findAllEmployees()
	 */
	@Override
	public List<Employee> retrieveAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		List<TEmployee> tEmployees = repository.findAll();
		if (null == tEmployees || tEmployees.size() <= 0)
			throw new NoRecordsFoundException("No Employee Records Found!");

		for (TEmployee tEmployee : tEmployees) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(tEmployee, employee);
			employees.add(employee);
		}

		return employees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.star.sud.employee.service.IEmployeeService#getEmployeeById(int)
	 */
	@Override
	public MappingJacksonValue getEmployeeById(int empId) {
		Optional<TEmployee> tEmployee = repository.findById(empId);

		if (!tEmployee.isPresent())
			throw new NoRecordsFoundException("No records found for the id - " + empId);

		Employee employee = new Employee();
		BeanUtils.copyProperties(tEmployee.get(), employee);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("empName", "empEmail", "empExperience");

		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilterEmployee", filter);

		MappingJacksonValue jacksonValue = new MappingJacksonValue(employee);
		jacksonValue.setFilters(filterProvider);

		return jacksonValue;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.star.sud.employee.service.IEmployeeService#createEmployee(com.star.sud.
	 * employee.dto.Employee)
	 */
	@Override
	public ResponseEntity<Object> createEmployee(Employee employee) {

		if (null == employee)
			throw new EmployeeCreationException("input param is null or empty");

		TEmployee tEmployee = null;

		tEmployee = repository.employeesByEmail(employee.getEmpEmail());
		if (tEmployee != null)
			throw new EmployeeCreationException("Email entered is already exists");

		TEmployee entity = new TEmployee();
		BeanUtils.copyProperties(employee, entity);
		tEmployee = repository.save(entity);
		if (null == tEmployee)
			throw new EmployeeCreationException("Failed to Create Employee Record");

		URI localtion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tEmployee.getEmpId()).toUri();

		return ResponseEntity.created(localtion).build();
	}

	/**
	 * @param empId
	 * @return
	 */
	public void deleteEmployee(Integer empId) {

		if (empId == null)
			throw new RequestParamException("request param is null or empty");

		Optional<TEmployee> tEmployee = repository.findById(empId);

		if (!tEmployee.isPresent())
			throw new NoRecordsFoundException("No records found for the id - " + empId);

		repository.delete(tEmployee.get());

	}

}
