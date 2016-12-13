package com.leqcar.timetracking.domain.model;

import org.axonframework.commandhandling.model.EntityId;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TimeEntry {

	private static final int HRS_PER_DAY_LIMIT = Integer.parseInt("17");

	@EntityId
	private String id;
		
	private LocalDate date;
	
	private transient DayOfWeek dayOfWeek;
	
	private Integer hours;
	
	public TimeEntry() {
	}

	public TimeEntry(String id, LocalDate date, Integer hours) {
		this.id = id;
		this.date = date;
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

	public boolean exceedsDayHoursLimit() {
		return this.hours.compareTo(HRS_PER_DAY_LIMIT) > 0 ? true : false;
	}
	
	@Override
	public String toString() {
		return "TimeEntry{" +
				"id='" + id + '\'' +
				", date=" + date +
				", dayOfWeek=" + dayOfWeek +
				", hours=" + hours +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TimeEntry timeEntry = (TimeEntry) o;

		if (id != null ? !id.equals(timeEntry.id) : timeEntry.id != null) return false;
		if (date != null ? !date.equals(timeEntry.date) : timeEntry.date != null) return false;
		if (dayOfWeek != timeEntry.dayOfWeek) return false;
		return hours != null ? hours.equals(timeEntry.hours) : timeEntry.hours == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
		result = 31 * result + (hours != null ? hours.hashCode() : 0);
		return result;
	}
}
