package com.leqcar.timetracking.query.timesheet;

import java.util.List;

import javax.persistence.Embeddable;

@Embeddable
public class TimeSheetPeriodEntry {
	
	private String timePeriodId;
	private String timePeriodDate;
	
	@OneToMany
	private List<ItemEntry> itemEntries;
	

	public TimeSheetPeriodEntry() {
	}
}