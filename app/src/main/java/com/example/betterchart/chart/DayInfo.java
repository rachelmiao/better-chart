package com.example.betterchart.chart;

import org.threeten.bp.LocalDate;

/**
 * Datatype that wraps each day's information, such as sticker color, date, etc.
 */
public class DayInfo {
    private Sticker sticker;
    private LocalDate date;

    public DayInfo(Sticker sticker, LocalDate date) {
        this.sticker = sticker;
        this.date = date;
    }

    LocalDate getDate() {
        return date;
    }

    Sticker getSticker() {
        return sticker;
    }
}
