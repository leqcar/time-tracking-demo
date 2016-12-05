package com.leqcar.timetracking.api.timesheet;

import org.axonframework.common.IdentifierFactory;

import java.io.Serializable;

/**
 * Created by jongtenerife on 05/12/2016.
 */
public class TimeSheetId implements Serializable {

	private static final long serialVersionUID = 1997185323606530719L;
	
	private String id;

    public TimeSheetId(String id) {
        this.id = id;
    }

    public TimeSheetId() {
        this.id = IdentifierFactory.getInstance().generateIdentifier();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSheetId that = (TimeSheetId) o;

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
