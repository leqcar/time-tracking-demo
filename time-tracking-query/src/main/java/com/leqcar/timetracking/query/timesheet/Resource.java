package com.leqcar.timetracking.query.timesheet;

import javax.persistence.Embeddable;

@Embeddable
public class Resource {

	private String id;
	private String name;

	public Resource() {
	}

	public Resource(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + "]";
	}

}