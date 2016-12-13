package com.leqcar.timetracking.dtos;

import java.util.List;

import com.leqcar.timetracking.dtos.ItemDTO;

/**
 * Created by jongtenerife on 10/12/2016.
 */
public class TimeSheetDTO {

    private String timeSheetId;
    private String timePeriodId;
    private String resourceId;
    private String note;

    private List<ItemDTO> itemVOs;

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

    public List<ItemDTO> getItemVOs() {
        return itemVOs;
    }

    public void setItemVOs(List<ItemDTO> itemVOs) {
        this.itemVOs = itemVOs;
    }
}
