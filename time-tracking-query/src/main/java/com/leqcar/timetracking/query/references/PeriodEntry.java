package com.leqcar.timetracking.query.references;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class PeriodEntry extends ReferenceEntry{
	
	public PeriodEntry() {
		super();
	}
	
	public PeriodEntry(String id, String description, LocalDate effectiveDate) {
		super(id, description, effectiveDate);
	}


}
