package com.example.betterchart.chart;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.widget.LinearLayout;

import com.example.betterchart.R;

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
