package com.leqcar.timetracking.domain.model;

import com.leqcar.timetracking.api.timesheet.CreateTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.SubmitTimeSheetCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class TimeSheetCommandHandler {

    private Repository<TimeSheet> repository;
    private EventBus eventBus;

    public TimeSheetCommandHandler(Repository<TimeSheet> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @CommandHandler
    public void handle(CreateTimeSheetCommand command) throws Exception {
        repository.newInstance(() ->
                new TimeSheet(command.getTimeSheetId()
                        , command.getNote()));
    }

    @CommandHandler
    public void handle(SubmitTimeSheetCommand command) throws Exception {
        Aggregate<TimeSheet> timeSheetAggregate = repository.load(command.getTimeSheetId());
        timeSheetAggregate.execute(timeSheet -> timeSheet.submit(command.getNote()));
    }
}
