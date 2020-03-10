package com.example.betterchart.chart;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

import com.example.betterchart.R;

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
        GradientDrawable background = (GradientDrawable) findViewById(R.id.sticker).getBackground();
        background.setColor(color);
    }

}
