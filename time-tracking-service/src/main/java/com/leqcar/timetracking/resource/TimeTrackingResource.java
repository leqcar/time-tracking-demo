package com.leqcar.timetracking.resource;

import com.leqcar.timetracking.api.timesheet.*;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

	@PostMapping("/{timeSheetId}/submit")
	public void submitTimeSheet(@PathVariable("timeSheetId") String timeSheetId, @RequestBody TimeSheetVO request) {

		List<ItemCommand> itemCommandList = new LinkedList<>();
		List<ItemDetail> itemDetailList = new LinkedList<>();

		request.getItemVOs().stream().forEach(item -> {
			item.getItemDetailVOs().stream()
					.forEach(d -> {
						itemDetailList.add(new ItemDetail(d.getId(),
								d.getDatePeriod(),
								d.getNoOfHours()));
					});
			itemCommandList.add(new ItemCommand(item.getItemCode(),
					item.getItemDescription(),
					itemDetailList,
					item.getStatus(),
					item.getNote()));
		});

		commandGateway.send(new SubmitTimeSheetCommand(new TimeSheetId(timeSheetId),
				new TimePeriodId(request.getTimePeriodId()),
				new ResourceId(request.getResourceId()),
				itemCommandList,
				request.getNote()));
	}
}
