package com.leqcar.timetracking.query.timesheet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="timesheetentries", path="timesheetentries")
public interface TimeSheetRepository extends JpaRepository<TimeSheetEntry, String>{

	public List<TimeSheetEntry> findByResourceIdAndTimeSheetPeriodEntryStatusIdIsNull(@Param("resourceId") String resourceId);
}
