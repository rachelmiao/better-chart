package com.example.betterchart.chart;

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
}
