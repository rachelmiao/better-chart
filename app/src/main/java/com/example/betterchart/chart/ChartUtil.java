package com.example.betterchart.chart;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.betterchart.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.Comparator;

/**
 * Utility class for chart-related logic with static methods.
 */
public class ChartUtil {

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

    static String getDateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("M/dd"));
    }

    public static void setBackgroundColor(Context context, View view, int colorRes) {
        GradientDrawable background = (GradientDrawable) view.getBackground();
        background.setColor(ContextCompat.getColor(context, colorRes));
    }

}
