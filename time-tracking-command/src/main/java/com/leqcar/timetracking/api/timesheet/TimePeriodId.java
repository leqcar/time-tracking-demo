package com.leqcar.timetracking.api.timesheet;

import java.io.Serializable;

import org.springframework.util.Assert;

/**
 * Created by jongtenerife on 05/12/2016.
 */
public class TimePeriodId implements Serializable {

	private static final long serialVersionUID = 691332902980363975L;
	
	private String id;

    public TimePeriodId(String id) {
        this.id = id;
    }

    public String getId() {
		return id;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriodId that = (TimePeriodId) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

	public String previousTimePeriodId() {
		Assert.notNull(this.id, "Id must not be null");
		return String.valueOf(Integer.parseInt(this.id) - 1);
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
