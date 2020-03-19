package com.example.betterchart.chart;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.betterchart.MainActivity;
import com.example.betterchart.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.P)
public class ChartRendererTest {

    private ViewGroup container;

    @Before
    public void setup() {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        container = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.day_view,
                null);
    }

    @Test
    public void render() {
        DayInfo day1 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 9))
                .setIsFirstDay(false).create();
        DayInfo day2 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 8))
                .setIsFirstDay(false).create();
        DayInfo day3 = new DayInfo.Builder()
                .setDate(LocalDate.of(2020, 3, 10))
                .setIsFirstDay(false).create();
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);
        ChartRenderer.render(Cycle.fromDays(daysList), container);

    }

}
