package com.example.betterchart.chart;

import android.graphics.Color;

/**
 * Specifies the types of stickers and instructions on how they are to be drawn.
 */
public enum Sticker {
    RED,
    GREEN,
    WHITE,
    UNDEFINED;

    int getColor() {
        switch (this) {
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case WHITE:
                return Color.WHITE;
            case UNDEFINED:
                // fall-through
            default:
                return Color.LTGRAY;
        }
    }
}
