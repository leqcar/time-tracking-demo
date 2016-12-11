package com.leqcar.timetracking;

import com.leqcar.timetracking.query.profile.UserProfileEntry;
import com.leqcar.timetracking.query.profile.UserProfileRepository;
import com.leqcar.timetracking.query.references.PeriodEntry;
import com.leqcar.timetracking.query.references.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TimeTrackingServiceApplication {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private ReferenceRepository<PeriodEntry> timePeriodRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TimeTrackingServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner dummyUserProfileCLR() {
		UserProfileEntry userProfile = new UserProfileEntry("3043682", "jong.tenerife", "Jong", "Tenerife");
		return arg -> userProfileRepository.save(userProfile);
	}
	
	@Bean
	CommandLineRunner dummyPeriodCLR() {
		return arg -> {
			PeriodEntry period = new PeriodEntry("1", "Jan-1-to-Jan-7", LocalDate.now());
			timePeriodRepository.save(period);
		};
	}
}
