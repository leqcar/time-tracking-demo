package com.leqcar.timetracking.api.timesheet;

import java.time.LocalDate;

public class ItemDetail {
	private String id;
	private LocalDate datePeriod;
	private Integer noOfHours;
	
	public ItemDetail(String id, LocalDate datePeriod, Integer noOfHours) {
		this.id = id;
		this.datePeriod = datePeriod;
		this.noOfHours = noOfHours;
	}

	public LocalDate getDatePeriod() {
		return datePeriod;
	}

	public Integer getNoOfHours() {
		return noOfHours;
	}

    public String getId() {
        return id;
    }
}
