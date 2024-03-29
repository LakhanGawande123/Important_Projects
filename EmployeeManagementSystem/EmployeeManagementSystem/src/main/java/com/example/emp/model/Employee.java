package com.example.emp.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonStringType.class)
//@Table(name="EmployeeTable")
public class Employee {
	
	@Id
	@GeneratedValue
	//@Column(name="EmployeeID")
	private int eid;
	
	//@Column(name="EmployeeName")
	@NotEmpty(message = "name is required.")
	@Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
	private String name;
	
	@NotEmpty(message = "The Gender is required.")
	@NotNull
	@Size(min = 4, max = 6, message = "The length of Gender must be between 4 and 6 characters.")
	private String gender;
	
	@NotEmpty(message = "The Age is required.")
	@NotNull
	@Min(value=18, message = "Minimum Age is 18 required")
    @Max(value=60, message = "Maximum age is 60")
	private String age;
	
	@NotEmpty(message = "Mobile Number is required.")
	@NotNull
	@Size(min = 10, max = 10, message = "The length of Mobile Number must be 10 characters.")
	private String mobileNumber;
	
	@NotEmpty(message = "Experience is required.")
	@NotNull
	private String experience;
	
	//@Column(name="EmployeeSalary")
	@NotNull(message = "Please enter a valid salary")
    @Min(value=1000, message = "Salary must be atleast 1000.00")
    @Max(value=40000, message = "Salary should not be greater than 40000.00")
	private double salary;
	
	//@Column(name="JobRole")
	@NotEmpty(message = "The job role is required.")
	@NotNull(message = "Please enter a valid JobRole")
	private String jobRole;
	
	@NotEmpty(message = "Department is required.")
	@NotNull
	@Size(min = 2, max = 100, message = "The length of Department must be between 2 and 100 characters.")
	private String department;
	
	@NotEmpty(message = "Company name is required.")
	@NotNull
	@Size(min = 2, max = 100, message = "The length of Company name must be between 2 and 100 characters.")
	private String company;
	
	@Type(type = "jsonb") // See (2)
    @Column(name = "jsonb")
    private Address address;
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	
	

//	public Employee(int eid, String name, double salary, String jobRole) {
//		super();
//		this.eid = eid;
//		this.name = name;
//		this.salary = salary;
//		this.jobRole = jobRole;
//	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", name=" + name + ", gender=" + gender + ", age=" + age + ", mobileNumber="
				+ mobileNumber + ", experience=" + experience + ", salary=" + salary + ", jobRole=" + jobRole
				+ ", department=" + department + ", company=" + company + ", address=" + address + "]";
	}
	
}
