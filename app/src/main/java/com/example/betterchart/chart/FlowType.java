package com.example.betterchart.chart;

import android.content.Context;

import com.example.betterchart.R;

public enum FlowType {

    HEAVY,
    MEDIUM,
    LIGHT,
    VERY_LIGHT,
    NONE;


    public static FlowType fromString(Context context, String string) {
        if (string.equals(context.getString(R.string.flow_type_heavy))) {
            return HEAVY;
        } else if (string.equals(context.getString(R.string.flow_type_medium))) {
            return MEDIUM;
        } else if (string.equals(context.getString(R.string.flow_type_light))) {
            return LIGHT;
        } else if (string.equals(context.getString(R.string.flow_type_very_light))) {
            return VERY_LIGHT;
        } else {
            return NONE;
        }
    }

    public int getStringInt() {
        switch(this) {
            case HEAVY:
                return R.string.flow_type_heavy;
            case MEDIUM:
                return R.string.flow_type_medium;
            case LIGHT:
                return R.string.flow_type_light;
            case VERY_LIGHT:
                return R.string.flow_type_very_light;
            case NONE:
                return R.string.flow_type_none;
            default:
                return -1; // change this
        }
    }

}
