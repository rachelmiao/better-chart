package com.example.betterchart.chart;

import org.junit.Test;
import org.threeten.bp.LocalDate;

import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ChartUtilTest {

    @Test
    public void getNumCycleDays() {
        DayInfo day1 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 1))
                .setIsFirstDay(true).create();
        DayInfo day2 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 10))
                .setIsFirstDay(false).create();
        List<DayInfo> days = Arrays.asList(new DayInfo[]{day1, day2});
        Cycle cycle = Cycle.fromDays(days);

        // Should be 10 days inclusive.
        assertEquals(10, ChartUtil.getNumCycleDays(cycle));
    }

}
