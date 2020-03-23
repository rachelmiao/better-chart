package com.example.betterchart.chart;

import java.util.Arrays;
import java.util.List;

public class MucusData {

    // Mucus frequency constants
    public static final int MUCUS_ONCE = 1;
    public static final int MUCUS_TWICE = 2;
    public static final int MUCUS_THRICE = 3;
    public static final int MUCUS_ALL_DAY = 4;

    // Mucus number constants
    public static final int MUCUS_DRY = 0;
    public static final int MUCUS_STICKY = 6;
    public static final int MUCUS_TACKY = 8;
    public static final int MUCUS_STRETCHY = 10;


    private int mucusNumber;  // This number is: 0, 2, 4, 6, 8, 10, or -1 (not set).
    private List<MucusType> mucusTypes;
    private int mucusFrequency;
    private boolean pickedUp;

    public int getMucusNumber() {
        return mucusNumber;
    }

    public List<MucusType> getMucusTypes() {
        return mucusTypes;
    }

    public int getMucusFrequency() {
        return mucusFrequency;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }


    private MucusData(Builder builder) {
        this.mucusNumber = builder.mucusNumber;
        this.mucusTypes = builder.mucusTypes;
        this.mucusFrequency = builder.mucusFrequency;
        this.pickedUp = builder.pickedUp;
    }

    boolean isPeakType() {
        // If clear, stretchy, or lubricative, return true.
        boolean isClear = mucusTypes != null && mucusTypes.contains(MucusType.CLEAR);
        boolean isStretchy = (mucusNumber == MUCUS_STICKY || mucusNumber == MUCUS_TACKY || mucusNumber == MUCUS_STRETCHY);
        boolean isLubricative = mucusTypes != null && mucusTypes.contains(MucusType.LUBRICATIVE);

        return isClear || isStretchy || isLubricative;
    }

    boolean isNonPeakType() {
        return (mucusNumber >= 0 && mucusNumber <= 4);
    }

    boolean hasSpotting() {
        return mucusTypes != null &&
                (mucusTypes.contains(MucusType.BROWN) || mucusTypes.contains(MucusType.RED));
    }

    public String getDisplayString() {
        // Mucus Number + Mucus Type Letters + Frequency
        // e.g. 10 KL x3

        boolean isLubricative = mucusTypes.contains(MucusType.LUBRICATIVE);

        StringBuilder types = new StringBuilder();
        for (MucusType t : mucusTypes) {
            // If not lubricative, don't display damp or shiny
            if (!isLubricative) {
                if (t == MucusType.DAMP || t == MucusType.SHINY) {
                    continue;
                }
            }
            types.append(t.getTypeString());
        }

        String freq = "";
        if (mucusFrequency == MUCUS_ONCE) {
            freq = "x1";
        } else if (mucusFrequency == MUCUS_TWICE) {
            freq = "x2";
        } else if (mucusFrequency == MUCUS_THRICE) {
            freq = "x3";
        } else if (mucusFrequency == MUCUS_ALL_DAY) {
            freq = "AD";  // all day
        }

        return mucusNumber + types.toString().toUpperCase() + " " + freq;
    }

    public static class Builder {

        private int mucusNumber = -1;
        private List<MucusType> mucusTypes;
        private int mucusFrequency;
        private boolean pickedUp;

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

        public Builder setPickedUp(boolean pickedUp) {
            this.pickedUp = pickedUp;
            return this;
        }

        public MucusData create() {
            return new MucusData(this);
        }
    }

    public enum MucusType {
        WET("w"),
        DAMP("d"),
        SHINY("s"),
        LUBRICATIVE("l"),
        BROWN("b"),
        CLOUDY("c"),
        CLEAR("k"),
        CLOUDY_CLEAR("c/k"),
        GUMMY("g"),
        PASTY("p"),
        RED("r"),
        YELLOW("y");

        private String s;

        MucusType(String s) {
            this.s = s;
        }

        public String getTypeString() {
            return s;
        }

        public static MucusType fromString(String from) {
            for (MucusType mt : MucusType.values()) {
                if (from.equalsIgnoreCase(mt.s)) {
                    return mt;
                }
            }
            return null;
        }

        private static boolean isColor(MucusType type) {
            MucusType[] colors = {BROWN, CLOUDY, CLEAR, CLOUDY_CLEAR, RED, YELLOW};
            return Arrays.asList(colors).contains(type);
        }

        private static boolean isAppearance(MucusType type) {
            MucusType[] appearances = {WET, SHINY, DAMP};
            return Arrays.asList(appearances).contains(type);
        }
    }
}
