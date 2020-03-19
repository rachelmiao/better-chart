package com.example.betterchart.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.betterchart.MainActivity;
import com.example.betterchart.R;
import com.example.betterchart.chart.FlowType;

import java.util.HashMap;
import java.util.Map;

public class AddBloodFragment extends Fragment {

    private RadioGroup radioFlow;
    private MainActivity mainActivity;
    private Map<FlowType, RadioButton> buttonMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_blood, container, false);

        mainActivity = (MainActivity) getActivity();

        radioFlow = view.findViewById(R.id.radio_flow);

        for (int i = 0; i < radioFlow.getChildCount(); i++) {
            View v = radioFlow.getChildAt(i);
            if (v instanceof RadioButton) {
                RadioButton rb = (RadioButton) v;
                FlowType type = FlowType.fromString(getContext(), (String) rb.getText());
                buttonMap.put(type, rb);
            }
        }

        // Set radio button corresponding to existing dayinfo entry
        FlowType existingFlowType = mainActivity.getDayInfo().getFlowType();
        if (existingFlowType != null) {
            RadioButton rb = buttonMap.get(existingFlowType);
            rb.setChecked(true);
        }

        Button b = view.findViewById(R.id.add_blood_done_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton b = radioFlow.findViewById(radioFlow.getCheckedRadioButtonId());
                String selectedFlow = (String) b.getText();
                FlowType flowType = FlowType.fromString(getContext(), selectedFlow);

                // Set the FlowType.
                ((MainActivity) getActivity()).setFlowType(flowType);

                // Go back to DayFragment
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
