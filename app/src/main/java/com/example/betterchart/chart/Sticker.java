package com.example.betterchart.chart;

import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.example.betterchart.R;

/**
 * Specifies the types of stickers and instructions on how they are to be drawn.
 */
public enum Sticker {
    RED,
    GREEN,
    WHITE,
    UNDEFINED;

    /**
     * Note: These colors are resource references, not color ints
     */
    public int getColor() {
        switch (this) {
            case RED:
                return R.color.sticker_red;
            case GREEN:
                return R.color.sticker_green;
            case WHITE:
                return R.color.sticker_white;
            case UNDEFINED:
                // fall-through
            default:
                return R.color.sticker_grey;
        }
    }
}
