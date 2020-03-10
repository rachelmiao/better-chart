package com.example.betterchart.chart;

import android.util.Log;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represent a cycle, which can be incomplete.
 */
public class Cycle {

    private static final String LOGGER = Cycle.class.getName();
    private static final int MAX_CYCLE_DAYS = 60;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<DayInfo> days;

    private Cycle(LocalDate startDate, LocalDate endDate, List<DayInfo> days) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    /**
     * This constructor ensures that startDate is indeed the earliest date in days and also sorts
     * the days in ascending order by date. It also sets the end date.
     * @param days
     * @return
     */
    public static Cycle fromDays(List<DayInfo> days) {
        // Handle null list by setting it to empty.
        if (days == null) {
            Log.e(LOGGER, "days is null");
            days = new ArrayList<>();
        }

        // Handle empty list
        if (days.isEmpty()) {
            return new Cycle(LocalDate.MIN, LocalDate.MIN, new ArrayList<DayInfo>());
        }

        // TODO: handle cycles that exceed max length

        // Sort days in ascending order
        Collections.sort(days, new ChartUtil.DaysComparator());

        // Set first date
        LocalDate startDate = days.get(0).getDate();

        // Set end date
        LocalDate endDate = days.get(days.size() - 1).getDate();

        return new Cycle(startDate, endDate, days);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<DayInfo> getDays() {
        return days;
    }
}
