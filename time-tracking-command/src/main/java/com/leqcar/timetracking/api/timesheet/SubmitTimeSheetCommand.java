package com.leqcar.timetracking.api.timesheet;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class SubmitTimeSheetCommand {

    @TargetAggregateIdentifier
    private TimeSheetId timeSheetId;
    
    private ResourceId resourceId;
    private TimePeriodId timePeriodId;
    private List<ItemCommand> itemEntries;
    private String note;

    public SubmitTimeSheetCommand() {
    }

    public SubmitTimeSheetCommand(TimeSheetId timeSheetId, TimePeriodId timePeriodId,
                                  ResourceId resourceId, List<ItemCommand> itemEntries, String note) {
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

	public List<ItemCommand> getItemEntries() {
		return itemEntries;
	}

	public String getNote() {
		return note;
	}

}
