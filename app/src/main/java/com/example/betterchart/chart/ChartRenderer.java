package com.example.betterchart.chart;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.VisibleForTesting;

import com.example.betterchart.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.Iterator;

/*
 * Produces a visual representation of a single chart with stickers.
 */
public class ChartRenderer {

    /**
     * Takes in a Cycle and a container and produces a chart, which is attached to the container.
     * Cycle is guaranteed to be in sorted asc order.
     * @return a View containing the chart.
     */
    public static View render(Cycle cycle, ViewGroup container) {
        // Inflate a chart view which will contain all the days
        View chartView = View.inflate(container.getContext(), R.layout.chart_view, container);

        // Draw rectangles from the start date to the end date, filling in the correct color
        // if the day's info is specified.
        Iterator<DayInfo> dayIterator = cycle.getDays().iterator();
        DayInfo currentDayInfo = dayIterator.next();
        LocalDate currentDate = currentDayInfo.getDate();

        for (int i = 0; i < getNumCycleDays(cycle); i++) {
            System.out.println("currentDayInfo: " + currentDayInfo.getDate());
            System.out.println("currentDate: " + currentDate);
            // Check if there is an entry for that day in the cycle, adjust the stamp accordingly.
            Sticker sticker = Sticker.UNDEFINED;
            if (currentDayInfo.getDate().equals(currentDate)) {
                System.out.println("Entry for this date exists.");
                // There is an entry
                sticker = currentDayInfo.getSticker();
                currentDayInfo = dayIterator.next();
            }

            // Create the sticker view with the correct color
            System.out.println("Sticker color: " + sticker.getColor());
            // Attach to the parent container
        }

        return null;
    }

    // TODO: potentially move into util
    // Returns days of a cycle (i.e. between start and end, inclusive)
    @VisibleForTesting
    static long getNumCycleDays(Cycle cycle) {
        return ChronoUnit.DAYS.between(cycle.getStartDate(), cycle.getEndDate());
    }
}
