package com.leqcar.timetracking.api.timesheet;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class TimeSheetCreatedEvent {

    private TimeSheetId timeSheetId;
    private TimePeriodId timePeriodId;
    private ResourceId resourceId;
    private String status;
    private String note;

    public TimeSheetCreatedEvent() {
    }

    public TimeSheetCreatedEvent(TimeSheetId timeSheetId, TimePeriodId timePeriodId, ResourceId resourceId, String note, String status) {
        this.timeSheetId = timeSheetId;
        this.timePeriodId = timePeriodId;
        this.resourceId = resourceId;
        this.note = note;
        this.status = status;
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
                ", timeSheetId=" + timeSheetId +
                ", note='" + note + '\'' +
                '}';
    }
}
