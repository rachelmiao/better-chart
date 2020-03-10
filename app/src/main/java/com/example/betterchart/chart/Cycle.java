package com.example.betterchart.chart;

import org.threeten.bp.LocalDate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represent a cycle, which can be incomplete.
 */
public class Cycle {

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
        // TODO: handle empty/null

        // Sort days in ascending order
        Collections.sort(days, new DaysComparator());

        // Set first date
        LocalDate startDate = days.get(0).getDate();

        // Set end date
        LocalDate endDate = days.get(days.size() - 1).getDate();

        return new Cycle(startDate, endDate, days);
    }

    static class DaysComparator implements Comparator<DayInfo> {
        @Override
        public int compare(DayInfo dayInfo1, DayInfo dayInfo2) {
            return dayInfo1.getDate().compareTo(dayInfo2.getDate());
        }
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
