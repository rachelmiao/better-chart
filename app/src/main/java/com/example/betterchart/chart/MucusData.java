package com.example.betterchart.chart;

import java.util.List;

public class MucusData {

    // Mucus frequency constants
    private static final int MUCUS_ONCE = 1;
    private static final int MUCUS_TWICE = 2;
    private static final int MUCUS_THRICE = 3;
    private static final int MUCUS_ALL_DAY = 4;

    private int mucusNumber;  // This number should be one of: 0, 2, 4, 6, 8, 10
    private List<String> mucusTypes;
    private int mucusFrequency;
    private boolean isFirstDay;

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
