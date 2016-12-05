package com.leqcar.timetracking.query.timesheet;

import javax.persistence.Embeddable;

@Embeddable
public class Resource {

	private String resourceId;
	private String resourceName;

	public Resource() {
	}

}