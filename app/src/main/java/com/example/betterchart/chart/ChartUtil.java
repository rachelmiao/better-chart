package com.example.betterchart.chart;

import org.threeten.bp.temporal.ChronoUnit;

import java.util.Comparator;

/**
 * Utility class for chart-related logic with static methods.
 */
class ChartUtil {

    // Returns days of a cycle (i.e. between start and end, inclusive of both)
    static long getNumCycleDays(Cycle cycle) {
        // The between method does not include the end date
        return ChronoUnit.DAYS.between(cycle.getStartDate(), cycle.getEndDate()) + 1;
    }

    static class DaysComparator implements Comparator<DayInfo> {
        @Override
        public int compare(DayInfo dayInfo1, DayInfo dayInfo2) {
            return dayInfo1.getDate().compareTo(dayInfo2.getDate());
        }
    }

}
