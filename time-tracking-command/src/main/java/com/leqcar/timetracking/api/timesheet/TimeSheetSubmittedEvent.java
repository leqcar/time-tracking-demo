package com.leqcar.timetracking.api.timesheet;

import java.util.List;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class TimeSheetSubmittedEvent {

    private TimeSheetId timeSheetId;
    private String note;
    private String status;
    private List<ItemCommand> itemCommands;

    public TimeSheetSubmittedEvent(TimeSheetId timeSheetId, String note, String status, List<ItemCommand> itemCommands) {
        this.timeSheetId = timeSheetId;
        this.note = note;
        this.status = status;
        this.itemCommands = itemCommands;
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

    public List<ItemCommand> getItemCommands() {
        return itemCommands;
    }

    @Override
    public String toString() {
        return "TimeSheetSubmittedEvent{" +
                "timeSheetId=" + timeSheetId +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", itemCommands=" + itemCommands +
                '}';
    }
}
