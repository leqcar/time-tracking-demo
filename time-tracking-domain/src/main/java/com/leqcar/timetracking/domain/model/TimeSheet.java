package com.leqcar.timetracking.domain.model;

import com.leqcar.timetracking.api.timesheet.TimeSheetCreatedEvent;
import com.leqcar.timetracking.api.timesheet.TimeSheetSubmittedEvent;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventhandling.EventHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@AggregateRoot
public class TimeSheet {

	@AggregateIdentifier
	private String timeSheetId;

	private String note;

	private TimePeriod timePeriod;

	private ResourceProfile resourcePerson;

	private TimeSheetStatus timeSheetStatus;

	public TimeSheet() {
	}

	public TimeSheet(String id, String note) {
		apply(new TimeSheetCreatedEvent(id, note, TimeSheetStatus.UNSUBMITTED.toString()));
	}

	@EventHandler
	public void on(TimeSheetCreatedEvent event) {
		this.timeSheetId  = event.getTimeSheetId();
		this.note = event.getNote();
		this.timeSheetStatus = TimeSheetStatus.UNSUBMITTED;
	}

	public void submit(String note) {
		apply(new TimeSheetSubmittedEvent(timeSheetId, note, TimeSheetStatus.PENDING_APPROVAL.toString()));
	}

	@EventHandler
	public void on(TimeSheetSubmittedEvent event) {
		this.note = event.getNote();
		this.timeSheetStatus = TimeSheetStatus.PENDING_APPROVAL;
	}

	public String getTimeSheetId() {
		return timeSheetId;
	}

	public String getNote() {
		return note;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public ResourceProfile getResourcePerson() {
		return resourcePerson;
	}

	public TimeSheetStatus getTimeSheetStatus() {
		return timeSheetStatus;
	}

	@Override
	public String toString() {
		return "TimeSheet [timeSheetId=" + timeSheetId + ", note=" + note + ", timePeriod=" + timePeriod + ", resourcePerson="
				+ resourcePerson + ", timeSheetStatus=" + timeSheetStatus + "]";
	}

}
