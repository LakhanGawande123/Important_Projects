package com.example.demo.user.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.user.model.Employee;
import com.example.demo.user.repo.EmployeeRepo;
import com.example.demo.user.response.AddressResponse;
import com.example.demo.user.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepo employeeRepo;
  
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public EmployeeResponse getEmployeeById1(int id) {
    	  
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
  
        AddressResponse addressResponse = restTemplate.getForObject("http://localhost:9002/post/{id}", AddressResponse.class, id);
        employeeResponse.setAddressResponse(addressResponse);
  
        return employeeResponse;
    }
  
    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
    }
    
    public EmployeeResponse saveEmployee(Employee employee) {
        Employee employee1 = employeeRepo.save(employee);
        EmployeeResponse employeeResponse = mapper.map(employee1, EmployeeResponse.class);
        return employeeResponse;
    }

}