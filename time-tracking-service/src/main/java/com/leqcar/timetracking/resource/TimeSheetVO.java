package com.leqcar.timetracking.resource;

import java.util.List;

/**
 * Created by jongtenerife on 10/12/2016.
 */
public class TimeSheetVO {

    private String timeSheetId;
    private String timePeriodId;
    private String resourceId;
    private String note;

    private List<ItemVO> itemVOs;

    public String getTimeSheetId() {
        return timeSheetId;
    }

    public String getTimePeriodId() {
        return timePeriodId;
    }

    public void setTimeSheetId(String timeSheetId) {
        this.timeSheetId = timeSheetId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<ItemVO> getItemVOs() {
        return itemVOs;
    }

    public void setItemVOs(List<ItemVO> itemVOs) {
        this.itemVOs = itemVOs;
    }
}
