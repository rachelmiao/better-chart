package com.example.betterchart.chart;

import java.util.List;

public class MucusData {

    // Mucus frequency constants
    public static final int MUCUS_ONCE = 1;
    public static final int MUCUS_TWICE = 2;
    public static final int MUCUS_THRICE = 3;
    public static final int MUCUS_ALL_DAY = 4;

    private int mucusNumber;  // This number should be one of: 0, 2, 4, 6, 8, 10
    private List<String> mucusTypes;
    private int mucusFrequency;
    private boolean isFirstDay;

    public int getMucusNumber() {
        return mucusNumber;
    }

    public List<String> getMucusTypes() {
        return mucusTypes;
    }

    public int getMucusFrequency() {
        return mucusFrequency;
    }

    public boolean isFirstDay() {
        return isFirstDay;
    }

    private MucusData(Builder builder) {
        this.mucusNumber = builder.mucusNumber;
        this.mucusTypes = builder.mucusTypes;
        this.mucusFrequency = builder.mucusFrequency;
        this.isFirstDay = builder.isFirstDay;
    }

    public static class Builder {

        private int mucusNumber;
        private List<String> mucusTypes;
        private int mucusFrequency;
        private boolean isFirstDay;

        public Builder setMucusNumber(int number) {
            this.mucusNumber = number;
            return this;
        }

        public Builder setMucusTypes(List<String> types) {
            this.mucusTypes = types;
            return this;
        }

        public Builder setMucusFrequency(int frequency) {
            this.mucusFrequency = frequency;
            return this;
        }

        public Builder setIsFirstDay(boolean isFirstDay) {
            this.isFirstDay = isFirstDay;
            return this;
        }

        public MucusData create() {
            return new MucusData(this);
        }
    }

    public enum MucusType {
        WET,
        DAMP,
        SHINY,
        LUBRICATIVE,
        BROWN,
        CLOUDY,
        CLEAR,
        CLOUDY_CLEAR,
        GUMMY,
        PASTY,
        RED,
        YELLOW;

        public String getTypeString() {
            switch (this) {
                case WET:
                    return "W";
                case DAMP:
                    return "D";
                case SHINY:
                    return "S";
                case LUBRICATIVE:
                    return "L";
                case BROWN:
                    return "B";
                case CLOUDY:
                    return "C";
                case CLEAR:
                    return "K";
                case CLOUDY_CLEAR:
                    return "C/K";
                case GUMMY:
                    return "G";
                case PASTY:
                    return "P";
                case RED:
                    return "R";
                case YELLOW:
                    return "Y";
                default:
                    return "";
            }
        }
    }
}
