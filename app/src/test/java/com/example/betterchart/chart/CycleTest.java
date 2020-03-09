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
        DayInfo day3 = new DayInfo(Sticker.RED, Date.valueOf("2020-03;10"));
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);

        // Call the from Days constructor on an out-of-order list.
        Cycle.fromDays(daysList);

        assertEquals(daysList.get(0).getDate(), Date.valueOf("2020-03-08"));
        assertEquals(daysList.get(0).getDate(), Date.valueOf("2020-03-09"));
        assertEquals(daysList.get(0).getDate(), Date.valueOf("2020-03-10"));
    }
}
