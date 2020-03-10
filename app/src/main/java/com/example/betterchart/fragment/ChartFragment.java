package com.example.betterchart.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betterchart.R;
import com.example.betterchart.chart.ChartRenderer;
import com.example.betterchart.chart.Cycle;
import com.example.betterchart.chart.DayInfo;
import com.example.betterchart.chart.Sticker;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    public ChartFragment() {
        // Required empty public constructor
    }

    public static ChartFragment newInstance() {
        return new ChartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ViewGroup chartContainer = view.findViewById(R.id.chart_container);
        renderChart(chartContainer);
        return view;
    }

    private void renderChart(View view) {
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
        ChartRenderer.render(myTestCycle, (ViewGroup) view);

    }

}
