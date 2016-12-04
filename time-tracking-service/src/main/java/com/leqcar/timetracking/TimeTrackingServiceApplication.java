package com.leqcar.timetracking;

import com.leqcar.timetracking.api.timesheet.CreateTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.ResourceId;
import com.leqcar.timetracking.api.timesheet.TimePeriodId;
import com.leqcar.timetracking.api.timesheet.TimeSheetId;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@SpringBootApplication
public class TimeTrackingServiceApplication {

	private CommandBus commandBus;

	public TimeTrackingServiceApplication(CommandBus commandBus) {
		this.commandBus = commandBus;
	}

	public static void main(String[] args) {
		SpringApplication.run(TimeTrackingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner dummyCreateClr() {
		return args -> {
			commandBus.dispatch(asCommandMessage(new CreateTimeSheetCommand(new TimeSheetId()
					, new TimePeriodId("2")
					, new ResourceId("123456")
					, "Hello")));
		};
	}
}
