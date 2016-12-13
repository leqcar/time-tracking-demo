package com.leqcar.timetracking.service;

import org.springframework.stereotype.Service;

import com.leqcar.timetracking.query.timesheet.TimeSheetRepository;

@Service
public class TimeSheetService {

	private TimeSheetRepository timeSheetRepository;

	public TimeSheetService(TimeSheetRepository timeSheetRepository) {
		this.timeSheetRepository = timeSheetRepository;
	}
	
	public boolean hasUnsubmittedTimeSheet(String resourceId) {
		return timeSheetRepository.findByResourceIdAndTimeSheetPeriodEntryStatusIdIsNull(resourceId)
				.isEmpty();
	}
}
