package com.leqcar.timetracking.api.timesheet;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class CreateTimeSheetCommand {

    @TargetAggregateIdentifier
    private TimeSheetId timeSheetId;
    private String note;
    private TimePeriodId timePeriodId;
    private ResourceId resourceId;

    public CreateTimeSheetCommand() {
    }

    public CreateTimeSheetCommand(TimeSheetId timeSheetId, TimePeriodId timePeriodId, ResourceId resourceId, String note) {
        this.timeSheetId = timeSheetId;
        this.timePeriodId = timePeriodId;
        this.resourceId = resourceId;
        this.note = note;
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

    @Override
    public String toString() {
        return "CreateTimeSheetCommand{" +
                "timeSheetId=" + timeSheetId +
                ", note='" + note + '\'' +
                ", timePeriodId='" + timePeriodId + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
