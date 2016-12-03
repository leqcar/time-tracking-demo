package com.leqcar.timetracking.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
