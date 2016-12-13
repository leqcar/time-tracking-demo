package com.leqcar.timetracking.dtos;

import java.time.LocalDate;

/**
 * Created by jongtenerife on 10/12/2016.
 */
public class ItemDetailDTO {

    private String id;
    private LocalDate datePeriod;
    private Integer noOfHours;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(LocalDate datePeriod) {
        this.datePeriod = datePeriod;
    }

    public Integer getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(Integer noOfHours) {
        this.noOfHours = noOfHours;
    }
}
