package com.leqcar.timetracking.resource;

import java.util.Map;
import java.util.UUID;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leqcar.timetracking.api.timesheet.CreateTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.ResourceId;
import com.leqcar.timetracking.api.timesheet.TimePeriodId;
import com.leqcar.timetracking.api.timesheet.TimeSheetId;

@RestController
@RequestMapping("/timesheets")
public class TimeTrackingResource {

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping
	public void createTimeSheet(@RequestBody Map<String, String> request) {
		commandGateway.send(new CreateTimeSheetCommand(new TimeSheetId(UUID.randomUUID().toString())
				, new TimePeriodId(request.get("timePeriodId"))
				, new ResourceId(request.get("resourceId"))
				, request.get("note")), LoggingCallback.INSTANCE);
	}
	
}
