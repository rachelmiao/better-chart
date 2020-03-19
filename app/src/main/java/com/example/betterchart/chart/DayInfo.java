package com.example.betterchart.chart;

import org.threeten.bp.LocalDate;

/**
 * Builder-pattern datatype that wraps each day's information, such as sticker color, date, etc.
 * Consider making the date a key to ensure uniqueness
 */
public class DayInfo {
    private Sticker sticker;
    private LocalDate date;  // Can be null if not set
    private boolean isFirstDay;
    private FlowType flowType;  // Can be null if not set
    private MucusData mucusData;  // Can be null if not set

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
        private Sticker sticker = Sticker.UNDEFINED;
        private LocalDate date;
        private boolean isFirstDay;
        private FlowType flowType;
        private MucusData mucusData;

        /**
         * This method is private because it should be generated from flowtype and mucusdata.
         */
        private Builder setSticker(final Sticker sticker) {
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
            updateSticker();
            return this;
        }

        public Builder setMucusData(final MucusData mucusData) {
            this.mucusData = mucusData;
            updateSticker();
            return this;
        }

        /**
         * Should be called whenever data update is made to FlowType or MucusData
         */
        private void updateSticker() {
            // If there is any blood (flowtype or brown mucus), sticker is red regardless of mucus.
            if (flowType != null && flowType != FlowType.NONE) {
                setSticker(Sticker.RED);
                return;
            }

            // If no mucus, sticker is undefined
            if (mucusData == null) {
                setSticker(Sticker.UNDEFINED);
                return;
            }

            // If there is no blood, and there is peak-type mucus (clear, stretchy, OR
            // lubricative), sticker is white.
            if (mucusData.isPeakType()) {
                setSticker(Sticker.WHITE);
                return;
            }

            // If no blood and there is non peak-type mucus, sticker is green.
            if (mucusData.isNonPeakType()) {
                setSticker(Sticker.GREEN);
                return;
            }

            setSticker(Sticker.UNDEFINED);
        }

        public DayInfo create() {
            return new DayInfo(this);
        }
    }
}
