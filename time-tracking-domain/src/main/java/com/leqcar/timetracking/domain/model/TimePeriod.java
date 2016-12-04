package com.leqcar.timetracking.domain.model;

import com.leqcar.timetracking.api.timesheet.TimePeriodId;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.List;

@Aggregate
public class TimePeriod {

	private TimePeriodId timePeriodId;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;

	private List<Item> items;

	public TimePeriod() {
	}

	public TimePeriod(TimePeriodId timePeriodId, LocalDate dateFrom, LocalDate dateTo, List<Item> items) {
		this.timePeriodId = timePeriodId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.items = items;
	}

	public TimePeriodId getTimePeriodId() {
		return timePeriodId;
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
		return "TimePeriod{" +
				"timePeriodId=" + timePeriodId +
				", dateFrom=" + dateFrom +
				", dateTo=" + dateTo +
				", items=" + items +
				'}';
	}
}
