package com.leqcar.timetracking.domain.model;

import java.util.List;

// This class holds different type of projects, request, miscellaneous etc..
public class Item {

	private String itemId;
	
	private String code;
	
	private String description;
	
	private Status status;
	
	private ItemType itemType;
	
	private List<TimeEntry> timeEntries;
	
	private String note;
	
	
	public Item() {
	}

	public Item(String itemId, String code, String description, Status status, ItemType itemType,
			List<TimeEntry> timeEntries, String note) {
		this.itemId = itemId;
		this.code = code;
		this.description = description;
		this.status = status;
		this.itemType = itemType;
		this.timeEntries = timeEntries;
		this.note = note;
	}

	public String getItemId() {
		return itemId;
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
		return "Item [itemId=" + itemId + ", code=" + code + ", description=" + description + ", status=" + status
				+ ", itemType=" + itemType + ", timeEntries=" + timeEntries + ", note=" + note + "]";
	}
}
