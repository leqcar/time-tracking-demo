package com.leqcar.timetracking.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ResourceProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String employeeId;
	
	private String name;
	
	private String department;
	
	private String managerApprover;

	
	public ResourceProfile() {
	}

	public ResourceProfile(String employeeId, String name, String department, String managerApprover) {
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.managerApprover = managerApprover;
	}

	public Long getId() {
		return id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public String getManagerApprover() {
		return managerApprover;
	}

	@Override
	public String toString() {
		return "ResourceProfile [id=" + id + ", employeeId=" + employeeId + ", name=" + name + ", department="
				+ department + ", managerApprover=" + managerApprover + "]";
	}
	
}
