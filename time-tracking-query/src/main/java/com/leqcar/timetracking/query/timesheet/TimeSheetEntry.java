package com.leqcar.timetracking.query.timesheet;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TimeSheetEntry  {

	@Id
	private String timeSheetId;
	
	@Embedded
	private TimeSheetPeriodEntry timeSheetPeriodEntry;

	@Embedded
	private Resource resource;
	
	private String note;

	public TimeSheetEntry() {
	}

	public TimeSheetEntry(String timeSheetId, TimeSheetPeriodEntry timeSheetPeriodEntry, Resource resource,
			String note) {
		this.timeSheetId = timeSheetId;
		this.timeSheetPeriodEntry = timeSheetPeriodEntry;
		this.resource = resource;
		this.note = note;
	}

	public String getTimeSheetId() {
		return timeSheetId;
	}

	public TimeSheetPeriodEntry getTimeSheetPeriodEntry() {
		return timeSheetPeriodEntry;
	}

	public Resource getResource() {
		return resource;
	}

	public String getNote() {
		return note;
	}

	@Override
	public String toString() {
		return "TimeSheetEntry [timeSheetId=" + timeSheetId + ", timeSheetPeriodEntry=" + timeSheetPeriodEntry
				+ ", resource=" + resource + ", note=" + note + "]";
	}
}
