package com.example.betterchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.example.betterchart.chart.ChartRenderer;
import com.example.betterchart.chart.Cycle;
import com.example.betterchart.chart.DayInfo;
import com.example.betterchart.chart.Sticker;

import org.threeten.bp.LocalDate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderChart();
    }

    private void renderChart() {
        // For prototyping purposes only!
        DayInfo day1 = new DayInfo(Sticker.RED, LocalDate.of(2020, 3, 5));
        DayInfo day2 = new DayInfo(Sticker.RED, LocalDate.of(2020, 3, 6));
        DayInfo day3 = new DayInfo(Sticker.RED, LocalDate.of(2020, 3, 7));
        DayInfo day4 = new DayInfo(Sticker.GREEN, LocalDate.of(2020, 3, 8));
        DayInfo day5 = new DayInfo(Sticker.GREEN, LocalDate.of(2020, 3, 9));
        DayInfo day6 = new DayInfo(Sticker.WHITE, LocalDate.of(2020, 3, 11));
        List<DayInfo> daysList = new ArrayList<>();
        daysList.add(day1);
        daysList.add(day2);
        daysList.add(day3);
        daysList.add(day4);
        daysList.add(day5);
        daysList.add(day6);

        Cycle myTestCycle = Cycle.fromDays(daysList);
        ChartRenderer.render(myTestCycle, (ViewGroup) findViewById(R.id.container));

    }
}
