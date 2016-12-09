package com.leqcar.timetracking.query.timesheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="timesheetentries", path="timesheetentries")
public interface TimeSheetRepository extends JpaRepository<TimeSheetEntry, String>{

}
