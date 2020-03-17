package com.example.betterchart.chart;

import org.threeten.bp.LocalDate;

/**
 * Builder-pattern datatype that wraps each day's information, such as sticker color, date, etc.
 * Consider making the date a key to ensure uniqueness
 */
public class DayInfo {
    private Sticker sticker;
    private LocalDate date;
    private boolean isFirstDay;

    private DayInfo(Builder builder) {
        sticker = builder.sticker;
        date = builder.date;
        isFirstDay = builder.isFirstDay;
    }

    LocalDate getDate() {
        return date;
    }

    Sticker getSticker() {
        return sticker;
    }

    boolean isFirstDay() {
        return isFirstDay;
    }

    public static class Builder {
        private Sticker sticker;
        private LocalDate date;
        private boolean isFirstDay;

        public Builder setSticker(final Sticker sticker) {
            this.sticker = sticker;
            return this;
        }

        public Builder setDate(final LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setIsFirstDay(final boolean isFirstDay) {
            this.isFirstDay = isFirstDay;
            return this;
        }

        public DayInfo create() {
            return new DayInfo(this);
        }
    }
}
