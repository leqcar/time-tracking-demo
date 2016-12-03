package com.leqcar.timetracking.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String note;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private TimePeriod timePeriod;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private ResourceProfile resourcePerson;
	
	@Enumerated(EnumType.STRING)
	private TimeSheetStatus timeSheetStatus;

	public TimeSheet() {
	}

	public TimeSheet(String note, TimePeriod timePeriod, ResourceProfile resourcePerson,
			TimeSheetStatus timeSheetStatus) {
		this.note = note;
		this.timePeriod = timePeriod;
		this.resourcePerson = resourcePerson;
		this.timeSheetStatus = timeSheetStatus;
	}

	public Long getId() {
		return id;
	}

	public String getNote() {
		return note;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public ResourceProfile getResourcePerson() {
		return resourcePerson;
	}

	public TimeSheetStatus getTimeSheetStatus() {
		return timeSheetStatus;
	}

	@Override
	public String toString() {
		return "TimeSheet [id=" + id + ", note=" + note + ", timePeriod=" + timePeriod + ", resourcePerson="
				+ resourcePerson + ", timeSheetStatus=" + timeSheetStatus + "]";
	}
	
}
