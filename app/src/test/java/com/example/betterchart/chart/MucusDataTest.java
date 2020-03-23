package com.example.betterchart.chart;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MucusDataTest {

    @Test
    public void isPeakType() {
        // Clear
        MucusData clear = new MucusData.Builder()
                .setMucusTypes(Collections.singletonList(MucusData.MucusType.CLEAR)).create();
        assertTrue(clear.isPeakType());

        // Mucus is sticky, tacky, or stretchy
        MucusData sticky = new MucusData.Builder().setMucusNumber(MucusData.MUCUS_STICKY).create();
        MucusData tacky = new MucusData.Builder().setMucusNumber(MucusData.MUCUS_TACKY).create();
        MucusData stretchy = new MucusData.Builder().setMucusNumber(MucusData.MUCUS_STRETCHY).create();
        assertTrue(sticky.isPeakType());
        assertTrue(tacky.isPeakType());
        assertTrue(stretchy.isPeakType());

        // Lubricative
        MucusData lubricative = new MucusData.Builder()
                .setMucusTypes(Collections.singletonList(MucusData.MucusType.LUBRICATIVE)).create();
        assertTrue(lubricative.isPeakType());

        // Mucus number is 0, 2, or 4 should return false
        MucusData mucus0 = new MucusData.Builder().setMucusNumber(0).create();
        MucusData mucus2 = new MucusData.Builder().setMucusNumber(2).create();
        MucusData mucus4 = new MucusData.Builder().setMucusNumber(4).create();
        assertFalse(mucus0.isPeakType());
        assertFalse(mucus2.isPeakType());
        assertFalse(mucus4.isPeakType());

        // Default mucus returns false
        MucusData defaultMucus = new MucusData.Builder().create();
        assertFalse(defaultMucus.isPeakType());
    }

    @Test
    public void isNonPeakType() {
        // Mucus number 0, 2, 4 should return true
        MucusData mucus0 = new MucusData.Builder().setMucusNumber(0).create();
        MucusData mucus2 = new MucusData.Builder().setMucusNumber(2).create();
        MucusData mucus4 = new MucusData.Builder().setMucusNumber(4).create();
        assertTrue(mucus0.isNonPeakType());
        assertTrue(mucus2.isNonPeakType());
        assertTrue(mucus4.isNonPeakType());

        // Default mucus returns false
        MucusData defaultMucus = new MucusData.Builder().create();
        assertFalse(defaultMucus.isNonPeakType());
    }

}
