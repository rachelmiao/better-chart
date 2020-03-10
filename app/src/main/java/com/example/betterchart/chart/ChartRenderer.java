package com.example.betterchart.chart;

import android.view.ViewGroup;

import androidx.annotation.VisibleForTesting;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

/*
 * Produces a visual representation of a single chart with stickers.
 */
public class ChartRenderer {

    /**
     * Takes in a Cycle and a container and produces a visual chart based on the cycle, which is
     * then attached to the container.
     * Cycle is guaranteed to be in sorted asc order.
     */
    public static void render(Cycle cycle, ViewGroup container) {

        // Iterate over each day in the range from the start date to the end date, even ones without
        // entries.
        LocalDate date = cycle.getStartDate();
        long numCycleDays = ChartUtil.getNumCycleDays(cycle);

        // We need a separate iterator for the DayInfo list so that we can advance it only
        // when an entry matches the date being drawn.
        int j = 0;
        for (int i = 0; i < numCycleDays; i++) {
            // Check if there is an entry for that day in the cycle, adjust the stamp accordingly.
            Sticker sticker = Sticker.UNDEFINED;
            DayInfo dayInfo = cycle.getDays().get(j);
            if (dayInfo.getDate().equals(date)) {
                sticker = dayInfo.getSticker();
                j++;  // Increment DayInfo list iterator
            }
            date = date.plusDays(1);  // Increment date

            // Create the sticker view with the correct color
            DayView dayView = new DayView(container.getContext());
            dayView.setStickerColor(sticker.getColor());

            // Attach to the parent container
            container.addView(dayView);
        }

    }
}
