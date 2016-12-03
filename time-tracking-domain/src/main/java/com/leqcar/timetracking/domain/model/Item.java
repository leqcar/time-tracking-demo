package com.leqcar.timetracking.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

// This class holds different type of projects, request, miscellaneous etc..
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Enumerated(EnumType.STRING)
	private ItemType itemType;
	
	public Item() {
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
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
