package com.example.betterchart.chart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DayInfoTest {

    @Test
    public void setFlowTypeSetsRedWithFlow() {
        DayInfo.Builder di = new DayInfo.Builder();
        for (FlowType ft : FlowType.values()) {
            di.setFlowType(ft);
            if (ft == FlowType.NONE) {
                assertEquals(Sticker.UNDEFINED, di.create().getSticker());
            } else {
                assertEquals(Sticker.RED, di.create().getSticker());
            }
        }
    }

    @Test
    public void setMucusTypeSetsStickerPeakType() {
        MucusData mucusData = mock(MucusData.class);
        DayInfo.Builder di = new DayInfo.Builder().setFlowType(FlowType.NONE);
        when(mucusData.isPeakType()).thenReturn(true);
        di.setMucusData(mucusData);
        assertEquals(Sticker.WHITE, di.create().getSticker());
    }

    @Test
    public void setMucusTypeSetsStickerNonPeakType() {
        MucusData mucusData = mock(MucusData.class);
        DayInfo.Builder di = new DayInfo.Builder().setFlowType(FlowType.NONE);
        when(mucusData.isNonPeakType()).thenReturn(true);
        di.setMucusData(mucusData);
        assertEquals(Sticker.GREEN, di.create().getSticker());
    }

    @Test
    public void setMucusTypeNullMucus() {
        DayInfo di = new DayInfo.Builder().setFlowType(FlowType.NONE)
                .setMucusData(null).create();
        assertEquals(Sticker.UNDEFINED, di.getSticker());
    }
}