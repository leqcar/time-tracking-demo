package com.leqcar;

import com.leqcar.timetracking.api.timesheet.*;
import com.leqcar.timetracking.api.timesheet.ItemCommand;
import com.leqcar.timetracking.domain.model.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .expectException(IllegalArgumentException.class);
    }
    
    @Test
    public void testTimeSheetPendingApprovalBelowMinimumTotalHours() {
    	ItemDetail seed = new ItemDetail(UUID.randomUUID().toString(), LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), 1);
		List<ItemDetail> itemDetail = Stream.iterate(seed, n -> {
					ItemDetail next = new ItemDetail(n.getId(), n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
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
            		Arrays.asList(new ItemCommand("itemCode", "itemDesc", itemDetail, null, null)),
            		"Hello"))
            .expectException(IllegalStateException.class);
    }
    
    @Test
    public void testTimeSheetPendingApprovalAboveMaximumTotalHours() {
    	ItemDetail seed = new ItemDetail(UUID.randomUUID().toString(), LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), 50);
		List<ItemDetail> itemDetail = Stream.iterate(seed, n -> {
					return new ItemDetail(n.getId(), n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
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
            		Arrays.asList(new ItemCommand("itemCode", "itemDesc", itemDetail, null, null)),
            		"Hello"))
            .expectException(IllegalStateException.class);
    }
    
    @Test
    public void testTimeSheetPendingApprovalSubmitted() {
    	ItemDetail seed = new ItemDetail(UUID.randomUUID().toString(), LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), 8);
		List<ItemDetail> itemDetails = Stream.iterate(seed, n -> {
					return new ItemDetail(n.getId(), n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
				})
    			.limit(7)
    			.collect(Collectors.toList());

		List<ItemCommand> itemCommands = Arrays.asList(new ItemCommand("itemCode",
				"itemDesc",
				itemDetails,
				null,
				null));

		fixture.given(new TimeSheetCreatedEvent(timeSheetId
                    , timePeriodId
                    , resourceId
                    , "Hello", "UNSUBMITTED"))
                .when(new SubmitTimeSheetCommand(timeSheetId, 
                		timePeriodId, 
                		resourceId,
						itemCommands,
                		"Hello"))
                .expectEvents(new TimeSheetSubmittedEvent(timeSheetId, "Hello", "PENDING_APPROVAL", itemCommands));
    }
    
	@Test
	public void testTimeSheetPendingApprovalWithTwoItemsSubmitted() {
		ItemDetail seed = new ItemDetail(UUID.randomUUID().toString(), LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1), 8);
		List<ItemDetail> taskItemDetails = Stream.iterate(seed, n -> {
			return new ItemDetail(n.getId(), n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
		})
				.limit(3)
				.collect(Collectors.toList());

		ItemDetail nextSeed = new ItemDetail(UUID.randomUUID().toString(), LocalDate.now().with(ChronoField.DAY_OF_WEEK, 4), 8);
		List<ItemDetail> vlItemDetails = Stream.iterate(nextSeed , n -> {
			return new ItemDetail(n.getId(), n.getDatePeriod().plus(1, ChronoUnit.DAYS), n.getNoOfHours());
		})
				.limit(2)
				.collect(Collectors.toList());

		List<ItemCommand> items = Arrays.asList(new ItemCommand("itemTask", "itemTaskDescription", taskItemDetails, null, null),
				new ItemCommand("itemVL","itemVLDescription", vlItemDetails, null, null));

		fixture.given(new TimeSheetCreatedEvent(timeSheetId
				, timePeriodId
				, resourceId
				, "Hello", "UNSUBMITTED"))
				.when(new SubmitTimeSheetCommand(timeSheetId,
						timePeriodId,
						resourceId,
						items,
						"Hello"))
				.expectEvents(new TimeSheetSubmittedEvent(timeSheetId, "Hello", "PENDING_APPROVAL", items));

	}
}
