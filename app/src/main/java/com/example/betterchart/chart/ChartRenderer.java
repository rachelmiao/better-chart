package com.example.betterchart.chart;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.VisibleForTesting;

import com.example.betterchart.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;
import org.w3c.dom.Text;

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

        // Draw rectangles from the start date to the end date, filling in the correct color
        // if the day's info is specified.
        // For now, add a text view with █ characters

        LocalDate date = cycle.getStartDate();
        int j = 0;  // separate iterator for DayInfo list
        // Iterate over each day in the range, even ones without entries
        for (int i = 0; i < getNumCycleDays(cycle); i++) {
            // Check if there is an entry for that day in the cycle, adjust the stamp accordingly.
            Sticker sticker = Sticker.UNDEFINED;
            DayInfo dayInfo = cycle.getDays().get(j);
            if (dayInfo.getDate().equals(date)) {
                // There is an entry
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
        return null;
    }

    // TODO: potentially move into util
    // Returns days of a cycle (i.e. between start and end, inclusive of both)
    @VisibleForTesting
    static long getNumCycleDays(Cycle cycle) {
        // The between method does not include the end date
        return ChronoUnit.DAYS.between(cycle.getStartDate(), cycle.getEndDate()) + 1;
    }
}
