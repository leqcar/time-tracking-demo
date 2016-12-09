package com.leqcar.timetracking.query.timesheet;

import javax.persistence.Embeddable;

@Embeddable
public class Resource {

	private String resourceId;
	private String resourceName;

	public Resource() {
	}

	public Resource(String resourceId, String resourceName) {
		this.resourceId = resourceId;
		this.resourceName = resourceName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", resourceName=" + resourceName + "]";
	}
	
	

}