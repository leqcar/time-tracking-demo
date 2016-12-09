package com.leqcar.timetracking.api.timesheet;

import java.util.List;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class SubmitTimeSheetCommand {

    @TargetAggregateIdentifier
    private TimeSheetId timeSheetId;
    
    private ResourceId resourceId;
    private TimePeriodId timePeriodId;
    private List<Item> itemEntries;
    private String note;

    public SubmitTimeSheetCommand() {
    }

    public SubmitTimeSheetCommand(TimeSheetId timeSheetId, TimePeriodId timePeriodId, 
    		ResourceId resourceId, List<Item> itemEntries, String note) {
		this.timeSheetId = timeSheetId;
		this.timePeriodId = timePeriodId;
		this.resourceId = resourceId;
		this.itemEntries = itemEntries;
		this.note = note;
	}

	public TimeSheetId getTimeSheetId() {
		return timeSheetId;
	}

	public TimePeriodId getTimePeriodId() {
		return timePeriodId;
	}

	public ResourceId getResourceId() {
		return resourceId;
	}

	public List<Item> getItemEntries() {
		return itemEntries;
	}

	public String getNote() {
		return note;
	}

}
