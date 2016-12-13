package com.leqcar.timetracking.domain.model;

import com.leqcar.timetracking.api.timesheet.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class TimeSheet {

	@AggregateIdentifier
	private TimeSheetId timeSheetId;

	private String note;

	private TimePeriodId timePeriodId;

	private ResourceId resourceId;

	@AggregateMember
	private List<Item> itemEntries = new ArrayList<>();
	
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
			throw new IllegalArgumentException("Atleast one(1) item entry expected");
		}
		
		Integer totalHours = calculateTotalHours(cmd);
		if (!isWithinValidNoOfHours(totalHours)) {
			throw new IllegalStateException("Total no. of hours is not within the specified min or max limit");		
		}
		apply(new TimeSheetSubmittedEvent(cmd.getTimeSheetId(), 
				cmd.getNote(), 
				TimeSheetStatus.PENDING_APPROVAL.toString(),
				cmd.getItemEntries()));
						
	}

	private void addItemTimeEntries(List<ItemCommand> itemCommands) {
		if (this.itemEntries == null) {
			itemEntries = new ArrayList<>();
		}
		itemCommands.forEach(item -> {
			List<TimeEntry> timeEntries = item.getItemDetails().stream()
					.map(d -> {
						return new TimeEntry(d.getId(), d.getDatePeriod(), d.getNoOfHours());
					})
					.collect(Collectors.toList());

			itemEntries.add(new Item(timeSheetId.toString(),
					item.getItemCode(),
					item.getItemDescription(),
					null,
					null,
					timeEntries,
					item.getNote()));
		});
	}

	private boolean isWithinValidNoOfHours(Integer totalHours) {
		return totalHours >= MIN_NO_OF_HRS && totalHours <= MAX_NO_OF_HRS;
	}

	private Integer calculateTotalHours(SubmitTimeSheetCommand cmd) {
		return cmd.getItemEntries().stream()
				.mapToInt(item -> item.getItemDetails().stream()
						.map(itemDetail -> {
							if (itemDetail.exceedsDayHoursLimit()) {
								throw new IllegalArgumentException("Your work effort per day has exceeded!");
							}
							return itemDetail.getNoOfHours();
						})
						.reduce(0, (x,y) -> x + y))
				.sum();
	}

	@EventSourcingHandler
	public void on(TimeSheetSubmittedEvent event) {
		addItemTimeEntries(event.getItemCommands());
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
				"timeSheetId=" + timeSheetId +
				", note='" + note + '\'' +
				", timePeriodId=" + timePeriodId +
				", resourceId=" + resourceId +
				", itemEntries=" + itemEntries +
				", timeSheetStatus=" + timeSheetStatus +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TimeSheet timeSheet = (TimeSheet) o;

		if (timeSheetId != null ? !timeSheetId.equals(timeSheet.timeSheetId) : timeSheet.timeSheetId != null)
			return false;
		if (note != null ? !note.equals(timeSheet.note) : timeSheet.note != null) return false;
		if (timePeriodId != null ? !timePeriodId.equals(timeSheet.timePeriodId) : timeSheet.timePeriodId != null)
			return false;
		if (resourceId != null ? !resourceId.equals(timeSheet.resourceId) : timeSheet.resourceId != null) return false;
		if (itemEntries != null ? !itemEntries.equals(timeSheet.itemEntries) : timeSheet.itemEntries != null)
			return false;
		return timeSheetStatus == timeSheet.timeSheetStatus;
	}

	@Override
	public int hashCode() {
		int result = timeSheetId != null ? timeSheetId.hashCode() : 0;
		result = 31 * result + (note != null ? note.hashCode() : 0);
		result = 31 * result + (timePeriodId != null ? timePeriodId.hashCode() : 0);
		result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
		result = 31 * result + (itemEntries != null ? itemEntries.hashCode() : 0);
		result = 31 * result + (timeSheetStatus != null ? timeSheetStatus.hashCode() : 0);
		return result;
	}
}
