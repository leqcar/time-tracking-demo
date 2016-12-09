package com.leqcar.timetracking.query.references;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class ReferenceEntry {

	@Id
	private String id;
	private String description;
	private LocalDate effectiveDate;
	
	public ReferenceEntry() {
	}

	public ReferenceEntry(String id, String description, LocalDate effectiveDate) {
		this.id = id;
		this.description = description;
		this.effectiveDate = effectiveDate;
	}
	
	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	

}
