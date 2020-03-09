package com.example.betterchart.chart;

import android.graphics.Color;

/**
 * Specifies the types of stickers and instructions on how they are to be drawn.
 */
public enum Sticker {
    RED("red"),
    GREEN("green"),
    WHITE("white");

    private String color;

    Sticker(String color) {
        this.color = color;
    }

    int getColor(Sticker sticker) {
        switch (sticker) {
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case WHITE:
                return Color.WHITE;
            default:
                return Color.WHITE;
        }
    }
}
