package com.leqcar.timetracking.domain.model;

import java.util.List;

// This class holds different type of projects, request, miscellaneous etc..
public class Item {

	private Long id;
	
	private String code;
	
	private String description;
	
	private Status status;
	
	private String note;
	
	public Item(String code, String description, Status status, String note, ItemType itemType,
			List<TimeEntry> timeEntries) {
		this.code = code;
		this.description = description;
		this.status = status;
		this.note = note;
		this.itemType = itemType;
		this.timeEntries = timeEntries;
	}

	private ItemType itemType;
	
	public Item() {
	}

	private List<TimeEntry> timeEntries;

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Status getStatus() {
		return status;
	}

	public String getNote() {
		return note;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public List<TimeEntry> getTimeEntries() {
		return timeEntries;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", description=" + description + ", status=" + status + ", note="
				+ note + ", itemType=" + itemType + ", timeEntries=" + timeEntries + "]";
	}	
}
