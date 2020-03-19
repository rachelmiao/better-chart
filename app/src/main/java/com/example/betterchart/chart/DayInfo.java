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
    private FlowType flowType;
    private MucusData mucusData;

    private DayInfo(Builder builder) {
        sticker = builder.sticker;
        date = builder.date;
        isFirstDay = builder.isFirstDay;
        flowType = builder.flowType;
        mucusData = builder.mucusData;
    }

    LocalDate getDate() {
        return date;
    }

    public Sticker getSticker() {
        return sticker;
    }

    boolean isFirstDay() {
        return isFirstDay;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public MucusData getMucusData() {
        return mucusData;
    }

    public static class Builder {
        private Sticker sticker;
        private LocalDate date;
        private boolean isFirstDay;
        private FlowType flowType;
        private MucusData mucusData;

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

        public Builder setFlowType(final FlowType flowType) {
            this.flowType = flowType;
            return this;
        }

        public Builder setMucusData(final MucusData mucusData) {
            this.mucusData = mucusData;
            return this;
        }

        public DayInfo create() {
            return new DayInfo(this);
        }
    }
}
