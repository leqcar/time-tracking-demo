package com.leqcar.timetracking;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.leqcar.timetracking.query.profile.UserProfileEntry;
import com.leqcar.timetracking.query.profile.UserProfileRepository;
import com.leqcar.timetracking.query.references.PeriodEntry;
import com.leqcar.timetracking.query.references.ReferenceRepository;

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
			List<PeriodEntry> periodEntries = new LinkedList<>();
			LocalDate now = LocalDate.of(2017, 01, 02);
			LocalDate startDate = now.with(ChronoField.DAY_OF_WEEK, 1);
			LocalDate endDate = startDate.plus(6, ChronoUnit.DAYS);
			Stream.iterate(new LocalDate[]{startDate, endDate},
							dt -> new LocalDate[]{dt[1].plus(1, ChronoUnit.DAYS), dt[1].plus(7, ChronoUnit.DAYS)})
			.limit(12)
			.forEach(dt -> {
				String periods = dt[0].toString()
						.concat(" to " )
						.concat(dt[1].toString());
				System.out.println("PERIODS : " + periods);
				periodEntries.add(new PeriodEntry(periods, LocalDate.now()));

			});
			timePeriodRepository.save(periodEntries);
		};
	}
}
