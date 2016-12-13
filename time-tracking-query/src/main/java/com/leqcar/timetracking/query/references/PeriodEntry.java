package com.leqcar.timetracking.query.references;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class PeriodEntry extends ReferenceEntry{
	
	public PeriodEntry() {
		super();
	}
	
	public PeriodEntry(String description, LocalDate effectiveDate) {
		super(description, effectiveDate);
	}

	@Override
	public String toString() {
		return "PeriodEntry [getId()=" + getId() + ", getDescription()=" + getDescription() + ", getEffectiveDate()="
				+ getEffectiveDate() + "]";
	}
}
