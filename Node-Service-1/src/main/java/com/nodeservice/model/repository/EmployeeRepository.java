package com.nodeservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nodeservice.model.TEmployee;

/**
 * @author sudarshan
 *
 */
public interface EmployeeRepository extends JpaRepository<TEmployee, Integer> {

	@Query("from TEmployee o where o.empEmail= ?1")
	public TEmployee employeesByEmail(String name);

}
