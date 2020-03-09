package com.example.betterchart.chart;

import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class CycleTest {

    @Test
    public void fromDaysSortsByDate() {
        DayInfo day1 = new DayInfo(Sticker.RED, Date.valueOf("2020-03-09"));
        DayInfo day2 = new DayInfo(Sticker.GREEN, Date.valueOf("2020-03-08"));
        DayInfo day3 = new DayInfo(Sticker.RED, Date.valueOf("2020-03-10"));
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);

        // Call the from Days constructor on an out-of-order list.
        Cycle.fromDays(daysList);

        // The list should be re-arranged in ascending date order.
        assertEquals(daysList.get(0).getDate(), Date.valueOf("2020-03-08"));
        assertEquals(daysList.get(1).getDate(), Date.valueOf("2020-03-09"));
        assertEquals(daysList.get(2).getDate(), Date.valueOf("2020-03-10"));
    }

    @Test
    public void fromDaysSetsStartDate() {
        DayInfo day1 = new DayInfo(Sticker.RED, Date.valueOf("2020-03-09"));
        DayInfo day2 = new DayInfo(Sticker.GREEN, Date.valueOf("2020-03-08"));
        DayInfo day3 = new DayInfo(Sticker.RED, Date.valueOf("2020-03-10"));
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);

        // Call the from Days constructor.
        Cycle cycle = Cycle.fromDays(daysList);

        // The start date should be set to the earliest date.
        assertEquals(cycle.getStartDate(), Date.valueOf("2020-03-08"));
    }

    @Test
    public void fromDaysHandlesEmptyList() {
        // Call constructor on empty list.
        Cycle cycle = Cycle.fromDays(new ArrayList<DayInfo>());

        // TODO: figure out how this should be handled
    }
}
