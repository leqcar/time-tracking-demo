package com.leqcar.timetracking.api.timesheet;

import java.time.LocalDate;

public class ItemDetail {
	private static final Integer HRS_PER_DAY_LIMIT = Integer.parseInt("17");
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

	public boolean exceedsDayHoursLimit() {
		return this.noOfHours.compareTo(HRS_PER_DAY_LIMIT) > 0 ? true : false;
	}
	
    public String getId() {
        return id;
    }
}
