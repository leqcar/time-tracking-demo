package com.leqcar.timetracking.domain.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TimeEntry {

	private String id;
		
	private LocalDate date;
	
	private DayOfWeek dayOfWeek;
	
	private Integer hours;
	
	public TimeEntry() {
	}

	public TimeEntry(LocalDate date, Integer hours) {
		this.date = date;
		this.dayOfWeek = date.getDayOfWeek();
		this.hours = hours;
	}

	public String getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getHours() {
		return hours;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	
}
