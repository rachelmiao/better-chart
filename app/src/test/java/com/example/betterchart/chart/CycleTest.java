package com.example.betterchart.chart;

import org.junit.Test;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CycleTest {

    @Test
    public void fromDaysSortsByDate() {
        DayInfo day1 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 9))
                .setIsFirstDay(false).create();
        DayInfo day2 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 8))
                .setIsFirstDay(true).create();
        DayInfo day3 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 10))
                .setIsFirstDay(false).create();
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);

        // Call the from Days constructor on an out-of-order list.
        Cycle.fromDays(daysList);

        // The list should be re-arranged in ascending date order.
        assertEquals(daysList.get(0).getDate(), LocalDate.of(2020, 3, 8));
        assertEquals(daysList.get(1).getDate(), LocalDate.of(2020, 3, 9));
        assertEquals(daysList.get(2).getDate(), LocalDate.of(2020, 3, 10));
    }

    @Test
    public void fromDaysSetsStartDate() {
        DayInfo day1 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 9))
                .setIsFirstDay(false).create();
        DayInfo day2 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 8))
                .setIsFirstDay(true).create();
        DayInfo day3 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 10))
                .setIsFirstDay(false).create();
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);

        // Call the from Days constructor.
        Cycle cycle = Cycle.fromDays(daysList);

        // The start date should be set to the earliest date.
        assertEquals(cycle.getStartDate(), LocalDate.of(2020, 3, 8));
    }

    @Test
    public void fromDaysSetsEndDate() {
        DayInfo day1 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 9))
                .setIsFirstDay(false).create();
        DayInfo day2 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 8))
                .setIsFirstDay(true).create();
        DayInfo day3 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 10))
                .setIsFirstDay(false).create();
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);

        // Call the from Days constructor.
        Cycle cycle = Cycle.fromDays(daysList);

        // The end date should be set to the latest date.
        assertEquals(cycle.getEndDate(), LocalDate.of(2020, 3, 10));
    }

    @Test
    public void fromDaysHandlesEmptyList() {
        // Call constructor on empty list.
        Cycle cycle = Cycle.fromDays(new ArrayList<DayInfo>());

        assertNotNull(cycle);
        assertTrue(cycle.getDays().isEmpty());
    }

    @Test
    public void fromDaysHandlesNullList() {
        // Call constructor on null list.
        Cycle cycle = Cycle.fromDays(null);

        assertNotNull(cycle);
        assertTrue(cycle.getDays().isEmpty());
    }
}
