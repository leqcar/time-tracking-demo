package com.leqcar.timetracking.domain.model;

import com.leqcar.timetracking.api.timesheet.ResourceId;

public class ResourceProfile {

	private ResourceId resourceId;
	
	private String employeeId;
	
	private String name;
	
	private String department;
	
	private String managerApprover;

	
	public ResourceProfile() {
	}

	public ResourceProfile(ResourceId resourceId, String employeeId, String name, String department, String managerApprover) {
		this.resourceId = resourceId;
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.managerApprover = managerApprover;
	}

	public ResourceId getResourceId() {
		return resourceId;
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
		return "ResourceProfile{" +
				"resourceId=" + resourceId +
				", employeeId='" + employeeId + '\'' +
				", name='" + name + '\'' +
				", department='" + department + '\'' +
				", managerApprover='" + managerApprover + '\'' +
				'}';
	}
}
