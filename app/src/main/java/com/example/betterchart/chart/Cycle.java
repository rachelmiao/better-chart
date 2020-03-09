package com.example.betterchart.chart;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Represent a cycle, which can be incomplete.
 */
public class Cycle {

    private Date startDate;
    private List<DayInfo> days;

    private Cycle(Date startDate, List<DayInfo> days) {
        this.startDate = startDate;
        this.days = days;
    }

    /**
     * This constructor ensures that startDate is indeed the earliest date in days and also sorts
     * the days in ascending order by date.
     * @param days
     * @return
     */
    public static Cycle fromDays(List<DayInfo> days) {
        // Sort days in ascending order
        Collections.sort(days, new DaysComparator());

        // Set first date
        Date startDate = days.get(0).getDate();

        return new Cycle(startDate, days);
    }

    static class DaysComparator implements Comparator<DayInfo> {
        @Override
        public int compare(DayInfo dayInfo1, DayInfo dayInfo2) {
            return dayInfo1.getDate().compareTo(dayInfo2.getDate());
        }
    }
}
