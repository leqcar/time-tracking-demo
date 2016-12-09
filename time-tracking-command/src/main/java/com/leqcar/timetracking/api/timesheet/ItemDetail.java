package com.leqcar.timetracking.api.timesheet;

import java.time.LocalDate;

public class ItemDetail {
	private LocalDate datePeriod;
	private Integer noOfHours;
	
	public ItemDetail(LocalDate datePeriod, Integer noOfHours) {
		this.datePeriod = datePeriod;
		this.noOfHours = noOfHours;
	}

	public LocalDate getDatePeriod() {
		return datePeriod;
	}

	public Integer getNoOfHours() {
		return noOfHours;
	}
}
