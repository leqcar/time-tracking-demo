package com.leqcar.timetracking.domain.model;

import com.leqcar.timetracking.api.timesheet.*;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventhandling.EventHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@AggregateRoot
public class TimeSheet {

	@AggregateIdentifier
	private TimeSheetId timeSheetId;

	private String note;

	@AggregateMember
	private TimePeriod timePeriod;

	@AggregateMember
	private ResourceProfile resourceProfile;

	private TimeSheetStatus timeSheetStatus;

	public TimeSheet() {
	}

	public TimeSheet(TimeSheetId timeSheetId, TimePeriodId timePeriodId, ResourceId resourceId, String note) {
		apply(new TimeSheetCreatedEvent(timeSheetId, timePeriodId, resourceId, note, TimeSheetStatus.UNSUBMITTED.toString()));
	}

	@EventHandler
	public void on(TimeSheetCreatedEvent event) {
		this.timeSheetId  = event.getTimeSheetId();
		this.timeSheetStatus = TimeSheetStatus.UNSUBMITTED;
		this.note = event.getNote();
	}

	public void submit(String note) {
		apply(new TimeSheetSubmittedEvent(timeSheetId, note, TimeSheetStatus.PENDING_APPROVAL.toString()));
	}

	@EventHandler
	public void on(TimeSheetSubmittedEvent event) {
		this.note = event.getNote();
		this.timeSheetStatus = TimeSheetStatus.PENDING_APPROVAL;
	}

	public TimeSheetId getTimeSheetId() {
		return timeSheetId;
	}

	public String getNote() {
		return note;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public ResourceProfile getResourceProfile() {
		return resourceProfile;
	}

	public TimeSheetStatus getTimeSheetStatus() {
		return timeSheetStatus;
	}

	@Override
	public String toString() {
		return "TimeSheet{" +
				"timeSheetId='" + timeSheetId + '\'' +
				", note='" + note + '\'' +
				", timePeriod=" + timePeriod +
				", resourceProfile=" + resourceProfile +
				", timeSheetStatus=" + timeSheetStatus +
				'}';
	}
}
