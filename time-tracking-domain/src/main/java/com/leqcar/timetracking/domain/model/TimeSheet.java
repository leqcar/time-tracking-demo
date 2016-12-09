package com.leqcar.timetracking.domain.model;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.leqcar.timetracking.api.timesheet.CreateTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.ResourceId;
import com.leqcar.timetracking.api.timesheet.SubmitTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.TimePeriodId;
import com.leqcar.timetracking.api.timesheet.TimeSheetCreatedEvent;
import com.leqcar.timetracking.api.timesheet.TimeSheetId;
import com.leqcar.timetracking.api.timesheet.TimeSheetSubmittedEvent;

@Aggregate
public class TimeSheet {

	@AggregateIdentifier
	private TimeSheetId timeSheetId;

	private String note;

	private TimePeriodId timePeriodId;

	private ResourceId resourceId;

	private List<Item> itemEntries;
	
	private TimeSheetStatus timeSheetStatus;

	private final static int MIN_NO_OF_HRS = 40;
	private final static int MAX_NO_OF_HRS = 170;
	
	public TimeSheet() {
	}

	@CommandHandler
	public TimeSheet(CreateTimeSheetCommand cmd) {
		
		apply(new TimeSheetCreatedEvent(cmd.getTimeSheetId(), 
				cmd.getTimePeriodId(), 
				cmd.getResourceId(), 
				cmd.getNote(), 
				TimeSheetStatus.UNSUBMITTED.toString()));
	}

	@EventSourcingHandler
	public void on(TimeSheetCreatedEvent event) {
		this.timeSheetId  = event.getTimeSheetId();
		this.timePeriodId = event.getTimePeriodId();
		this.resourceId = event.getResourceId();
		this.timeSheetStatus = TimeSheetStatus.UNSUBMITTED;
		this.note = event.getNote();
	}

	@CommandHandler
	public void submit(SubmitTimeSheetCommand cmd) {
		if (cmd.getItemEntries().size() <= 0) {
			throw new NoItemEntryException("Atleast one(1) item entry expected");
		}
		
		Integer totalHours = calculateTotalHours(cmd);
		if (!isWithinValidNoOfHours(totalHours)) {
			throw new IllegalStateException("Total no. of hours is not within the specified min or max limit");		
		} 
		apply(new TimeSheetSubmittedEvent(cmd.getTimeSheetId(), 
				cmd.getNote(), 
				TimeSheetStatus.PENDING_APPROVAL.toString()));
						
	}

	private boolean isWithinValidNoOfHours(Integer totalHours) {
		return totalHours >= MIN_NO_OF_HRS && totalHours <= MAX_NO_OF_HRS;
	}

	private Integer calculateTotalHours(SubmitTimeSheetCommand cmd) {
		return cmd.getItemEntries().stream()
				.mapToInt(item -> item.getItemDetails().stream()
						.map(itemDetail -> itemDetail.getNoOfHours())
						.reduce(0, (x,y) -> x + y))
				.sum();
	}

	@EventSourcingHandler
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

	public TimePeriodId getTimePeriodId() {
		return timePeriodId;
	}

	public ResourceId getResourceId() {
		return resourceId;
	}

	public TimeSheetStatus getTimeSheetStatus() {
		return timeSheetStatus;
	}

	public List<Item> getItemEntries() {
		return itemEntries;
	}

	@Override
	public String toString() {
		return "TimeSheet{" +
				"timeSheetId='" + timeSheetId + '\'' +
				", note='" + note + '\'' +
				", timePeriodId=" + timePeriodId +
				", resourceId=" + resourceId +
				", timeSheetStatus=" + timeSheetStatus +
				'}';
	}
}
