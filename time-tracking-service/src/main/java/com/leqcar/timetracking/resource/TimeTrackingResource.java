package com.leqcar.timetracking.resource;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leqcar.timetracking.api.timesheet.CreateTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.ItemCommand;
import com.leqcar.timetracking.api.timesheet.ItemDetail;
import com.leqcar.timetracking.api.timesheet.ResourceId;
import com.leqcar.timetracking.api.timesheet.SubmitTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.TimePeriodId;
import com.leqcar.timetracking.api.timesheet.TimeSheetId;
import com.leqcar.timetracking.dtos.TimeSheetDTO;
import com.leqcar.timetracking.service.TimeSheetService;

@RestController
@RequestMapping("/timesheets")
public class TimeTrackingResource {

	@Autowired
	private CommandGateway commandGateway;
	
	private TimeSheetService timeSheetService;

	public TimeTrackingResource(TimeSheetService timeSheetService) {
		this.timeSheetService = timeSheetService;
	}

	@PostMapping
	public void createTimeSheet(@RequestBody Map<String, String> request) {
		
		TimePeriodId id = new TimePeriodId(request.get("timePeriodId"));
		
		if (timeSheetService.hasUnsubmittedTimeSheet(id.toString())){
			throw new CannotCreateTimeSheetException("Previous timesheet should be submitted or created first");
		}
		
		commandGateway.send(new CreateTimeSheetCommand(new TimeSheetId(UUID.randomUUID().toString())
				, id
				, new ResourceId(request.get("resourceId"))
				, request.get("note")), LoggingCallback.INSTANCE);
	}

	@PostMapping("/{timeSheetId}/submit")
	public void submitTimeSheet(@PathVariable("timeSheetId") String timeSheetId, @RequestBody TimeSheetDTO request) {

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
