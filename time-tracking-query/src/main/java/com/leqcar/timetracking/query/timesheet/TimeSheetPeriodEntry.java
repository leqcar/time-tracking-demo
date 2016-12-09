package com.leqcar.timetracking.query.timesheet;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class TimeSheetPeriodEntry {
	
	private String timePeriodId;
	private String timePeriodDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private List<ItemEntry> itemEntries;
	
	public TimeSheetPeriodEntry() {
	}

	public TimeSheetPeriodEntry(String timePeriodId, String timePeriodDate, List<ItemEntry> itemEntries) {
		this.timePeriodId = timePeriodId;
		this.timePeriodDate = timePeriodDate;
		this.itemEntries = itemEntries;
	}

	public String getTimePeriodId() {
		return timePeriodId;
	}

	public String getTimePeriodDate() {
		return timePeriodDate;
	}

	public List<ItemEntry> getItemEntries() {
		return itemEntries;
	}

	@Override
	public String toString() {
		return "TimeSheetPeriodEntry [timePeriodId=" + timePeriodId + ", timePeriodDate=" + timePeriodDate
				+ ", itemEntries=" + itemEntries + "]";
	}
	
}