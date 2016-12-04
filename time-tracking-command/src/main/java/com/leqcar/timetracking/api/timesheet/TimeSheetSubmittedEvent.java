package com.leqcar.timetracking.api.timesheet;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class TimeSheetSubmittedEvent {

    private TimeSheetId timeSheetId;
    private String note;
    private String status;

    public TimeSheetSubmittedEvent(TimeSheetId timeSheetId, String note, String status) {
        this.timeSheetId = timeSheetId;
        this.note = note;
        this.status = status;
    }

    public TimeSheetId getTimeSheetId() {
        return timeSheetId;
    }

    public String getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TimeSheetSubmittedEvent{" +
                "timeSheetId='" + timeSheetId + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
