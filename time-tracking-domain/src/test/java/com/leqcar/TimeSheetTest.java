package com.leqcar;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.hibernate.transform.ToListResultTransformer;
import org.junit.Before;
import org.junit.Test;

import com.leqcar.timetracking.api.timesheet.CreateTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.Item;
import com.leqcar.timetracking.api.timesheet.ItemDetail;
import com.leqcar.timetracking.api.timesheet.ResourceId;
import com.leqcar.timetracking.api.timesheet.SubmitTimeSheetCommand;
import com.leqcar.timetracking.api.timesheet.TimePeriodId;
import com.leqcar.timetracking.api.timesheet.TimeSheetCreatedEvent;
import com.leqcar.timetracking.api.timesheet.TimeSheetId;
import com.leqcar.timetracking.api.timesheet.TimeSheetSubmittedEvent;
import com.leqcar.timetracking.domain.model.NoItemEntryException;
import com.leqcar.timetracking.domain.model.TimeSheet;

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
    public void testTimeSheetPendingApprovalWithNoItemEntry() {
        fixture.given(new TimeSheetCreatedEvent(timeSheetId
                    , timePeriodId
                    , resourceId
                    , "Hello", "UNSUBMITTED"))
                .when(new SubmitTimeSheetCommand(timeSheetId, 
                		timePeriodId, 
                		resourceId, 
                		Collections.emptyList(), 
                		"Hello"))
                .expectException(NoItemEntryException.class);
    }
    
    @Test
    public void testTimeSheetPendingApprovalBelowMinimumTotalHours() {
    	ItemDetail seed = new ItemDetail(LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), 1);
		List<ItemDetail> itemDetail = Stream.iterate(seed, n -> {
					ItemDetail next = new ItemDetail(n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
					return next;
				})
    			.limit(7)
    			.collect(Collectors.toList());

        fixture.given(new TimeSheetCreatedEvent(timeSheetId
                , timePeriodId
                , resourceId
                , "Hello", "UNSUBMITTED"))
            .when(new SubmitTimeSheetCommand(timeSheetId, 
            		timePeriodId, 
            		resourceId, 
            		Arrays.asList(new Item("itemCode", "itemDesc", itemDetail)), 
            		"Hello"))
            .expectException(IllegalStateException.class);
    }
    
    @Test
    public void testTimeSheetPendingApprovalAboveMaximumTotalHours() {
    	ItemDetail seed = new ItemDetail(LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), 50);
		List<ItemDetail> itemDetail = Stream.iterate(seed, n -> {
					return new ItemDetail(n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
				})
    			.limit(7)
    			.collect(Collectors.toList());

        fixture.given(new TimeSheetCreatedEvent(timeSheetId
                , timePeriodId
                , resourceId
                , "Hello", "UNSUBMITTED"))
            .when(new SubmitTimeSheetCommand(timeSheetId, 
            		timePeriodId, 
            		resourceId, 
            		Arrays.asList(new Item("itemCode", "itemDesc", itemDetail)), 
            		"Hello"))
            .expectException(IllegalStateException.class);
    }
    
    @Test
    public void testTimeSheetPendingApproval() {
    	ItemDetail seed = new ItemDetail(LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), 8);
		List<ItemDetail> itemDetail = Stream.iterate(seed, n -> {
					return new ItemDetail(n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
				})
    			.limit(7)
    			.collect(Collectors.toList());

    			
        fixture.given(new TimeSheetCreatedEvent(timeSheetId
                    , timePeriodId
                    , resourceId
                    , "Hello", "UNSUBMITTED"))
                .when(new SubmitTimeSheetCommand(timeSheetId, 
                		timePeriodId, 
                		resourceId, 
                		Arrays.asList(new Item("itemCode", "itemDesc", itemDetail)), 
                		"Hello"))
                .expectEvents(new TimeSheetSubmittedEvent(timeSheetId, "Hello", "PENDING_APPROVAL"));
    }
    

}
