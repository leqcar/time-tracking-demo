package com.leqcar.timetracking.api.timesheet;

import java.io.Serializable;

/**
 * Created by jongtenerife on 05/12/2016.
 */
public class TimePeriodId implements Serializable {

	private static final long serialVersionUID = 691332902980363975L;
	
	private String id;

    public TimePeriodId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriodId that = (TimePeriodId) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return id;
    }
}
