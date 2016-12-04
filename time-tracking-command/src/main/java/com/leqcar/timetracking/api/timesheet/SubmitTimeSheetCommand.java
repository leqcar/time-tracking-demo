package com.leqcar.timetracking.api.timesheet;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class SubmitTimeSheetCommand {

    @TargetAggregateIdentifier
    private String timeSheetId;
    private String note;

    public SubmitTimeSheetCommand() {
    }

    public SubmitTimeSheetCommand(String timeSheetId, String note) {
        this.timeSheetId = timeSheetId;
        this.note = note;
    }

    public String getTimeSheetId() {
        return timeSheetId;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "SubmitTimeSheetCommand{" +
                "timeSheetId='" + timeSheetId + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}