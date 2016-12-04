package com.leqcar.timetracking.api.timesheet;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class SubmitTimeSheetCommand {

    @TargetAggregateIdentifier
    private TimeSheetId timeSheetId;
    private String note;

    public SubmitTimeSheetCommand() {
    }

    public SubmitTimeSheetCommand(TimeSheetId timeSheetId, String note) {
        this.timeSheetId = timeSheetId;
        this.note = note;
    }

    public TimeSheetId getTimeSheetId() {
        return timeSheetId;
    }

    public String getNote() {
        return note;
    }

}
