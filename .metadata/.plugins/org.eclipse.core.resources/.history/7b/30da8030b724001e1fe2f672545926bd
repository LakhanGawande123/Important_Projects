package com.example.emp.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.emp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	

	// Get List of Specific Employee
	@Query("SELECT e FROM Employee e WHERE e.name = :name1 And e.salary = :salary1 AND e.age = :age1")
	public List<Employee> findByNameAndSalaryAndAge(@Param("name1") String name,
			@Param("salary1") Double salary, @Param("age1") String age);
	
	// Get specific Employee
	@Query("SELECT e FROM Employee e WHERE e.name = :name1 And e.salary = :salary1 AND e.age = :age1 AND e.department = :department1")
	public Employee findByNameAndSalaryAndAgeAndDepartment(@Param("name1") String name,
			@Param("salary1") Double salary, @Param("age1") String age, @Param("department1") String department);
	

	@Transactional
	@Modifying
	@Query("UPDATE Employee SET salary = :salary WHERE name = :name AND eid = :eid")
	Integer updateSalaryByName(double salary, String name, int eid);
	
	@Transactional
	@Modifying
	@Query("delete from Employee where name = :name AND eid = :eid")
	void deleteEmployeesByNameAndEid(@Param("name") String name, @Param("eid") int eid);
}
