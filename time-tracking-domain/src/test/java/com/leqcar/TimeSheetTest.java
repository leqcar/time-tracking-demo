package com.leqcar;

import com.leqcar.timetracking.api.timesheet.*;
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

    private TimeSheetId timeSheetId;
    private TimePeriodId timePeriodId;
    private ResourceId resourceId;

    @Before
    public void setup() {
        fixture = new AggregateTestFixture<>(TimeSheet.class);
        fixture.registerAnnotatedCommandHandler(new TimeSheetCommandHandler(
                fixture.getRepository(),
                fixture.getEventBus()));
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());

        timeSheetId = new TimeSheetId();
        timePeriodId = new TimePeriodId("2");
        resourceId = new ResourceId("3333333");
    }

    @Test
    public void testTimeSheetCreate() {
        fixture.givenNoPriorActivity()
                .when(new CreateTimeSheetCommand(timeSheetId
                        , timePeriodId
                        , resourceId
                        , "Hello"))
                .expectEvents(new TimeSheetCreatedEvent(timeSheetId
                        , timePeriodId
                        , resourceId
                        , "Hello", "UNSUBMITTED"));
    }

    @Test
    public void testTimeSheetPendingApproval() {
        fixture.given(new TimeSheetCreatedEvent(timeSheetId
                    , timePeriodId
                    , resourceId
                    , "Hello", "UNSUBMITTED"))
                .when(new SubmitTimeSheetCommand(timeSheetId, "Hello"))
                .expectEvents(new TimeSheetSubmittedEvent(timeSheetId, "Hello", "PENDING_APPROVAL"));
    }

}
