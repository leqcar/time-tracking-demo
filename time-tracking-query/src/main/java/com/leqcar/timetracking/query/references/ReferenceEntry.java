package com.leqcar.timetracking.query.references;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class ReferenceEntry {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String description;

	private LocalDate effectiveDate;
	
	public ReferenceEntry() {
	}

	public ReferenceEntry(String description, LocalDate effectiveDate) {
		this.description = description;
		this.effectiveDate = effectiveDate;
	}
	
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	

}
