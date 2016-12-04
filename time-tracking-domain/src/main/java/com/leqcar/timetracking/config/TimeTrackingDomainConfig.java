package com.leqcar.timetracking.config;

import com.leqcar.timetracking.domain.model.TimeSheet;
import com.leqcar.timetracking.domain.model.TimeSheetCommandHandler;
import org.axonframework.commandhandling.AnnotationCommandHandlerAdapter;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jongtenerife on 04/12/2016.
 */
@Configuration
public class TimeTrackingDomainConfig {

    private EventStore eventStore;
    private CommandBus commandBus;

    public TimeTrackingDomainConfig(EventStore eventStore, CommandBus commandBus) {
        this.eventStore = eventStore;
        this.commandBus = commandBus;
    }

    @Bean
    TimeSheetCommandHandler timeSheetCommandHandler() {
        return new TimeSheetCommandHandler(timeSheetEventSourcingRepository(), eventStore);
    }

    @Bean
    AnnotationCommandHandlerAdapter handler() {
        AnnotationCommandHandlerAdapter handlerAdapter = new AnnotationCommandHandlerAdapter(timeSheetCommandHandler());
        handlerAdapter.subscribe(commandBus);
        return handlerAdapter;
    }


    @Bean
    Repository<TimeSheet> timeSheetEventSourcingRepository() {
        return new EventSourcingRepository<>(TimeSheet.class,  eventStore);
    }
}
