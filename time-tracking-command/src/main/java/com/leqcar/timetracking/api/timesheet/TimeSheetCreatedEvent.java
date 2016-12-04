package com.leqcar.timetracking.api.timesheet;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class TimeSheetCreatedEvent {

    private String status;
    private String timeSheetId;
    private String note;

    public TimeSheetCreatedEvent() {
    }

    public TimeSheetCreatedEvent(String timeSheetId, String note, String status) {
        this.timeSheetId = timeSheetId;
        this.note = note;
        this.status = status;
    }

    public String getTimeSheetId() {
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
        return "TimeSheetCreatedEvent{" +
                "status='" + status + '\'' +
                ", timeSheetId='" + timeSheetId + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
