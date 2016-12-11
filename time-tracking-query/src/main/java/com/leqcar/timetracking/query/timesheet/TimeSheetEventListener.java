package com.leqcar.timetracking.query.timesheet;

import com.leqcar.timetracking.api.timesheet.TimeSheetSubmittedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.leqcar.timetracking.api.timesheet.TimeSheetCreatedEvent;
import com.leqcar.timetracking.query.profile.UserProfileEntry;
import com.leqcar.timetracking.query.profile.UserProfileRepository;
import com.leqcar.timetracking.query.references.ReferenceRepository;
import com.leqcar.timetracking.query.references.PeriodEntry;

@Component
public class TimeSheetEventListener {

	private TimeSheetRepository timeSheetRepository;
	private UserProfileRepository userProfileRepository;
	private ReferenceRepository<PeriodEntry> periodRepository;


	public TimeSheetEventListener(TimeSheetRepository timeSheetRepository, UserProfileRepository userProfileRepository,
			ReferenceRepository<PeriodEntry> periodRepository) {
		this.timeSheetRepository = timeSheetRepository;
		this.userProfileRepository = userProfileRepository;
		this.periodRepository = periodRepository;
	}

	@EventHandler
	public void on(TimeSheetCreatedEvent event) {
		
		UserProfileEntry userProfile = userProfileRepository.getOne(event.getResourceId().getId());
		PeriodEntry period = periodRepository.findOne(event.getTimePeriodId().getId());
		
		TimeSheetPeriodEntry timeSheetPeriodEntry = new TimeSheetPeriodEntry(period.getId(), 
				period.getDescription(), null);
		Resource resource = new Resource(userProfile.getUserIdentificationNumber(), userProfile.fullName());
		
		timeSheetRepository.save(new TimeSheetEntry(event.getTimeSheetId().toString()
				, timeSheetPeriodEntry
				, resource
				, event.getNote()));
	}

	@EventHandler
	public void on(TimeSheetSubmittedEvent event) {
		System.out.println(event);
	}
}
