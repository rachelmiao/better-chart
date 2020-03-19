package com.example.betterchart.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.betterchart.MainActivity;
import com.example.betterchart.R;
import com.example.betterchart.chart.DayInfo;
import com.example.betterchart.chart.FlowType;

public class DayFragment extends Fragment {

    public DayFragment() {
        // Required empty public constructor
    }

    public static DayFragment newInstance() {
        return new DayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day, container, false);

        DayInfo di = ((MainActivity) getActivity()).getDayInfo();

        // Display existing flow Type
        FlowType flowType = di.getFlowType();
        // Set sticker color
        GradientDrawable background = (GradientDrawable) v.findViewById(R.id.fragment_day_sticker).getBackground();
        background.setColor(di.getSticker().getColor());

        // Set text
        String displayText = "Flow: " + getString(flowType.getStringInt());  // TODO make string.format
        TextView flowText = v.findViewById(R.id.fragment_day_flow_text);
        flowText.setText(displayText);

        View addFlow = v.findViewById(R.id.add_flow_button);
        addFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DayFragment.class.getName(), "addFlow called");

                Fragment f = new AddBloodFragment();
                ((MainActivity) getActivity()).openFragment(f);
            }
        });

        return v;
    }

}
