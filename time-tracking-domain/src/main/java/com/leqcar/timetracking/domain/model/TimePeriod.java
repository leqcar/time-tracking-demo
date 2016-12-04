package com.leqcar.timetracking.domain.model;

import java.time.LocalDate;
import java.util.List;

public class TimePeriod {

	private Long id;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;

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
