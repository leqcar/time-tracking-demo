package com.leqcar.timetracking.domain.model;

import java.time.LocalDate;

public class TimeEntry {

	private Long id;
		
	private LocalDate date;
	
	private Integer hours;
	
	public TimeEntry() {
	}

	public TimeEntry(LocalDate date, Integer hours) {
		this.date = date;
		this.hours = hours;
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getHours() {
		return hours;
	}

	@Override
	public String toString() {
		return "TimeEntry [id=" + id + ", date=" + date + ", hours=" + hours + "]";
	}
	
	
}
