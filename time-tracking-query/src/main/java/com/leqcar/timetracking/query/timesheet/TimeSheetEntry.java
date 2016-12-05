package com.leqcar.timetracking.query.timesheet;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TimeSheetEntry  {

	@Id
	private String id;
	
	private TimeSheetPeriodEntry timeSheetPeriodEntry;

	@Embedded
	private Resource resource;
	
	private String note;

	public TimeSheetEntry() {
	}
	
	
	
}
