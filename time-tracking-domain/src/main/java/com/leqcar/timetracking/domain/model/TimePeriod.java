package com.leqcar.timetracking.domain.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TimePeriod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private List<Item> items;

	public TimePeriod() {
	}

	public TimePeriod(LocalDate dateFrom, LocalDate dateTo, List<Item> items) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public List<Item> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "TimePeriod [id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", items=" + items + "]";
	}
	
}
