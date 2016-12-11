package com.leqcar.timetracking.domain.model;

import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.EntityId;

import java.util.List;

// This class holds different type of projects, request, miscellaneous etc..
public class Item {

	@EntityId
	private String itemId;
	
	private String code;
	
	private String description;
	
	private Status status;
	
	private ItemType itemType;

	@AggregateMember
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
		return "Item{" +
				"itemId='" + itemId + '\'' +
				", code='" + code + '\'' +
				", description='" + description + '\'' +
				", status=" + status +
				", itemType=" + itemType +
				", timeEntries=" + timeEntries +
				", note='" + note + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		if (itemId != null ? !itemId.equals(item.itemId) : item.itemId != null) return false;
		if (code != null ? !code.equals(item.code) : item.code != null) return false;
		if (description != null ? !description.equals(item.description) : item.description != null) return false;
		if (status != item.status) return false;
		if (itemType != item.itemType) return false;
		if (timeEntries != null ? !timeEntries.equals(item.timeEntries) : item.timeEntries != null) return false;
		return note != null ? note.equals(item.note) : item.note == null;
	}

	@Override
	public int hashCode() {
		int result = itemId != null ? itemId.hashCode() : 0;
		result = 31 * result + (code != null ? code.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
		result = 31 * result + (timeEntries != null ? timeEntries.hashCode() : 0);
		result = 31 * result + (note != null ? note.hashCode() : 0);
		return result;
	}
}
