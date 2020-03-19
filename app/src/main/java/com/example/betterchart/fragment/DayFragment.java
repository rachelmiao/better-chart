package com.example.betterchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.betterchart.MainActivity;
import com.example.betterchart.R;
import com.example.betterchart.chart.ChartUtil;
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

        // Display existing flow Type, which could be null
        FlowType flowType = di.getFlowType();

        if (flowType != null) {
            // Set sticker color
            ChartUtil.setBackgroundColor(getContext(),
                    v.findViewById(R.id.fragment_day_sticker), di.getSticker().getColor());

            // Set text
            String displayText = "Flow: " + getString(flowType.getStringInt());  // TODO make string.format
            TextView flowText = v.findViewById(R.id.fragment_day_flow_text);
            flowText.setText(displayText);
        } else {
            ChartUtil.setBackgroundColor(getContext(),
                    v.findViewById(R.id.fragment_day_sticker), R.color.sticker_grey);
        }


        View addFlow = v.findViewById(R.id.add_flow_button);
        addFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new AddBloodFragment();
                ((MainActivity) getActivity()).openFragment(f);
            }
        });

        return v;
    }

}
