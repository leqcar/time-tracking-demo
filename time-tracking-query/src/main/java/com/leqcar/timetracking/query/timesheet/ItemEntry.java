package com.leqcar.timetracking.query.timesheet;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemEntry {

	@Id
	private String id;

	public ItemEntry(String id) {
		this.id = id;
	}

	public ItemEntry() {
	}

	public String getId() {
		return id;
	}
	
}
