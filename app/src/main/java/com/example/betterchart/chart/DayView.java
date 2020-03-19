package com.example.betterchart.chart;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.betterchart.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Corresponds to XML layout and acts as a wrapper that allows an entry for a single day to be
 * programmatically manipulated. Shows sticker, date, and optional notes.
 */
public class DayView extends LinearLayout {

    public DayView(Context context) {
        super(context);
        inflate(getContext(), R.layout.day_view, this);
    }

    public void setStickerColor(int color) {
        ChartUtil.setBackgroundColor(getContext(), findViewById(R.id.sticker), color);
    }

    public void setStickerDate(LocalDate date) {
        TextView dateText = (TextView) findViewById(R.id.sticker_date);
        dateText.setText(ChartUtil.getDateString(date));
    }

}
