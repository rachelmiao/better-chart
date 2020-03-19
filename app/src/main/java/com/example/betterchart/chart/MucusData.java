package com.example.betterchart.chart;

import java.util.List;

public class MucusData {

    // Mucus frequency constants
    public static final int MUCUS_ONCE = 1;
    public static final int MUCUS_TWICE = 2;
    public static final int MUCUS_THRICE = 3;
    public static final int MUCUS_ALL_DAY = 4;

    private int mucusNumber;  // This number is: 0, 2, 4, 6, 8, 10, or -1 (not set).
    private List<MucusType> mucusTypes;
    private int mucusFrequency;

    public int getMucusNumber() {
        return mucusNumber;
    }

    public List<MucusType> getMucusTypes() {
        return mucusTypes;
    }

    public int getMucusFrequency() {
        return mucusFrequency;
    }


    private MucusData(Builder builder) {
        this.mucusNumber = builder.mucusNumber;
        this.mucusTypes = builder.mucusTypes;
        this.mucusFrequency = builder.mucusFrequency;
    }

    boolean isPeakType() {
        // If clear, stretchy, or lubricative, return true.
        boolean isClear = mucusTypes.contains(MucusType.CLEAR);
        boolean isStretchy = (mucusNumber >= 6);
        boolean isLubricative = mucusTypes.contains(MucusType.LUBRICATIVE);

        return isClear || isStretchy || isLubricative;
    }

    boolean isNonPeakType() {
        return (mucusNumber >= 0 && mucusNumber <= 4);
    }

    public static class Builder {

        private int mucusNumber = -1;
        private List<MucusType> mucusTypes;
        private int mucusFrequency;

        public Builder setMucusNumber(int number) {
            this.mucusNumber = number;
            return this;
        }

        public Builder setMucusTypes(List<MucusType> types) {
            this.mucusTypes = types;
            return this;
        }

        public Builder setMucusFrequency(int frequency) {
            this.mucusFrequency = frequency;
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
