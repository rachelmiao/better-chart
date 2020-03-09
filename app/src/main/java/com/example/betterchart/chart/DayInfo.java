package com.example.betterchart.chart;

import java.util.Date;

/**
 * Datatype that wraps each day's information, such as sticker color, date, etc.
 */
public class DayInfo {
    private Sticker sticker;
    private Date date;

    public DayInfo(Sticker sticker, Date date) {
        this.sticker = sticker;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
