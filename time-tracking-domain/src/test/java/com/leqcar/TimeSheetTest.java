package com.leqcar;

import com.leqcar.timetracking.api.timesheet.CreateTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.SubmitTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.TimeSheetCreatedEvent;
import com.leqcar.timetracking.api.timesheet.TimeSheetSubmittedEvent;
import com.leqcar.timetracking.domain.model.TimeSheet;
import com.leqcar.timetracking.domain.model.TimeSheetCommandHandler;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jongtenerife on 04/12/2016.
 */
public class TimeSheetTest {

    private FixtureConfiguration<TimeSheet> fixture;

    @Before
    public void setup() {
        fixture = new AggregateTestFixture<>(TimeSheet.class);
        fixture.registerAnnotatedCommandHandler(new TimeSheetCommandHandler(
                fixture.getRepository(),
                fixture.getEventBus()));
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void testTimeSheetCreate() {
        fixture.givenNoPriorActivity()
                .when(new CreateTimeSheetCommand("123", "Hello"))
                .expectEvents(new TimeSheetCreatedEvent("123", "Hello", "UNSUBMITTED"));
    }

    @Test
    public void testTimeSheetPendingApproval() {
        fixture.given(new TimeSheetCreatedEvent("123", "Hello", "UNSUBMITTED"))
                .when(new SubmitTimeSheetCommand("123", "Hello"))
                .expectEvents(new TimeSheetSubmittedEvent("123", "Hello", "PENDING_APPROVAL"));
    }

}
