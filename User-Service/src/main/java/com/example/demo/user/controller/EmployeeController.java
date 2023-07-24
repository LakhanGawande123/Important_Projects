package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.model.Employee;
import com.example.demo.user.response.EmployeeResponse;
import com.example.demo.user.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
    private EmployeeService employeeService;
	
	@GetMapping("/user/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeDetails1(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.getEmployeeById1(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
  
    @GetMapping("/users/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
    
    @PostMapping("/user")
    private ResponseEntity<EmployeeResponse> saveEmployeeDetails(@RequestBody Employee employee) {
        EmployeeResponse employee1 = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee1);
    }

}