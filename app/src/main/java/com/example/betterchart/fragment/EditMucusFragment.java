package com.example.betterchart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.betterchart.MainActivity;
import com.example.betterchart.R;
import com.example.betterchart.chart.MucusData;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

public class EditMucusFragment extends Fragment {

    private static final String TAG = EditMucusFragment.class.getName();
    private MainActivity mainActivity;

    private static int getInnerViewId(View outerView, int id) {
        return outerView.findViewById(id).getId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_mucus, container, false);

        mainActivity = (MainActivity) getActivity();

        // Set expanding views based on whether yes or no is pressed
        View pickUpYes = view.findViewById(R.id.pick_up_yes_button);
        View pickUpNo = view.findViewById(R.id.pick_up_no_button);
        final ViewGroup pickUpYesContainer = view.findViewById(R.id.pick_up_on_yes_container);
        final ViewGroup pickUpNoContainer = view.findViewById(R.id.pick_up_on_no_container);

        pickUpYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUpYesContainer.setVisibility(View.VISIBLE);
                pickUpNoContainer.setVisibility(View.GONE);
            }
        });

        pickUpNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUpNoContainer.setVisibility(View.VISIBLE);
                pickUpYesContainer.setVisibility(View.GONE);
            }
        });

        // Input always shown
        final RadioGroup pickUpGroup = view.findViewById(R.id.pick_up_group);
        final CheckBox lubricativeCheckbox = view.findViewById(R.id.checkbox_lubricative);
        final RadioGroup freqGroup = view.findViewById(R.id.frequency_group);

        // Input shown when pick up is yes
        final RadioGroup stretchGroup = view.findViewById(R.id.stretch_group);
        final RadioGroup consistencyGroup = view.findViewById(R.id.consistency_group);
        final RadioGroup colorGroup = view.findViewById(R.id.color_group);

        // Input shown only when pick up is no
        final RadioGroup appearanceGroup = view.findViewById(R.id.appearance_group); // if pick-up is no

        // Done button is enabled when either yes or no is pressed
        // TODO logic should expand to only allow done with satisfactory input
        final Button done = view.findViewById(R.id.edit_mucus_done);
        pickUpGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                done.setEnabled(group.getCheckedRadioButtonId() != -1);
            }
        });

        // StretchMap
        final BiMap<Integer, Integer> stretchMap = HashBiMap.create();
        stretchMap.put(0, -1);
        stretchMap.put(MucusData.MUCUS_STICKY, getInnerViewId(stretchGroup, R.id.stretch_sticky_button));
        stretchMap.put(MucusData.MUCUS_TACKY, getInnerViewId(stretchGroup, R.id.stretch_tacky_button));
        stretchMap.put(MucusData.MUCUS_STRETCHY, getInnerViewId(stretchGroup, R.id.stretch_stretchy_button));

        // FreqMap
        final BiMap<Integer, Integer> freqMap = HashBiMap.create();
        freqMap.put(0, -1);
        freqMap.put(MucusData.MUCUS_ONCE, getInnerViewId(freqGroup, R.id.frequency_1_button));
        freqMap.put(MucusData.MUCUS_TWICE, getInnerViewId(freqGroup, R.id.frequency_2_button));
        freqMap.put(MucusData.MUCUS_THRICE, getInnerViewId(freqGroup, R.id.frequency_3_button));
        freqMap.put(MucusData.MUCUS_ALL_DAY, getInnerViewId(freqGroup, R.id.frequency_4_button));


        // Pre-populate with any previously entered information
        MucusData existingData = mainActivity.getDayInfo().getMucusData();
        if (existingData != null) {
            List<MucusData.MucusType> existingTypes = existingData.getMucusTypes();
            if (existingData.isPickedUp()) {
                // Picked up button
                pickUpYes.performClick();

                // Stretch
                int mucusNumber = existingData.getMucusNumber();
                if (mucusNumber != -1) {
                    Integer stretchId = stretchMap.get(mucusNumber);
                    if (stretchId != null) {
                        stretchGroup.check(stretchId);
                    }
                }

                // Consistency
                if (existingTypes.contains(MucusData.MucusType.GUMMY)) {
                    consistencyGroup.check(getInnerViewId(consistencyGroup, R.id.consistency_gummy));
                } else if (existingTypes.contains(MucusData.MucusType.PASTY)) {
                    consistencyGroup.check(getInnerViewId(consistencyGroup, R.id.consistency_pasty));
                } else if (existingTypes.isEmpty()) {
                    consistencyGroup.check(getInnerViewId(consistencyGroup, R.id.consistency_none));
                }

                // Color
                if (existingTypes.contains(MucusData.MucusType.RED)) {
                    colorGroup.check(getInnerViewId(colorGroup, R.id.color_red));
                } else if (existingTypes.contains(MucusData.MucusType.BROWN)) {
                    colorGroup.check(getInnerViewId(colorGroup, R.id.color_brown));
                } else if (existingTypes.contains(MucusData.MucusType.CLEAR)) {
                    colorGroup.check(getInnerViewId(colorGroup, R.id.color_clear));
                } else if (existingTypes.contains(MucusData.MucusType.CLOUDY)) {
                    colorGroup.check(getInnerViewId(colorGroup, R.id.color_cloudy));
                } else if (existingTypes.contains(MucusData.MucusType.CLOUDY_CLEAR)) {
                    colorGroup.check(getInnerViewId(colorGroup, R.id.color_cloudy_clear));
                } else if (existingTypes.contains(MucusData.MucusType.YELLOW)) {
                    colorGroup.check(getInnerViewId(colorGroup, R.id.color_yellow));
                }
            } else {
                pickUpNo.performClick();

                // Appearance
                if (existingTypes.contains(MucusData.MucusType.DAMP)) {
                    appearanceGroup.check(getInnerViewId(appearanceGroup, R.id.appearance_damp_button));
                } else if (existingTypes.contains(MucusData.MucusType.WET)) {
                    appearanceGroup.check(getInnerViewId(appearanceGroup, R.id.appearance_wet_button));
                } else if (existingTypes.contains(MucusData.MucusType.SHINY)) {
                    appearanceGroup.check(getInnerViewId(appearanceGroup, R.id.appearance_shiny_button));
                }
            }

            // Sensation
            lubricativeCheckbox.setChecked(existingTypes.contains(MucusData.MucusType.LUBRICATIVE));

            // Frequency
            int freqNumber = existingData.getMucusFrequency();
            Integer freqId = freqMap.get(freqNumber);
            if (freqId != null) {
                freqGroup.check(freqId);
            }
        }

        // Done button collects information and updates dayinfo accordingly
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MucusData.Builder data = new MucusData.Builder();
                List<MucusData.MucusType> types = new ArrayList<>();

                // Sensation
                boolean isLubricative = lubricativeCheckbox.isChecked();
                if (isLubricative) {
                    types.add(MucusData.MucusType.LUBRICATIVE);
                }

                // Frequency
                int freqId = freqGroup.getCheckedRadioButtonId();
                if (freqMap.containsValue(freqId)) {
                    data.setMucusFrequency(freqMap.inverse().get(freqId));
                }

                boolean pickUpYes = pickUpGroup.getCheckedRadioButtonId() == pickUpGroup.findViewById(R.id.pick_up_yes_button).getId();
                data.setPickedUp(pickUpYes);
                if (pickUpYes) {
                    // Stretch
                    int stretchId = stretchGroup.getCheckedRadioButtonId();
                    if (stretchId == -1) {
                        data.setMucusNumber(0);
                    } else if (stretchMap.containsValue(stretchId)) {
                            data.setMucusNumber(stretchMap.inverse().get(stretchId));
                    }

                    // Consistency
                    int consistency = consistencyGroup.getCheckedRadioButtonId();
                    if (consistency == consistencyGroup.findViewById(R.id.consistency_gummy).getId()) {
                        types.add(MucusData.MucusType.GUMMY);
                    } else if (consistency == consistencyGroup.findViewById(R.id.consistency_pasty).getId()) {
                        types.add(MucusData.MucusType.PASTY);
                    }

                    // Color
                    int color = colorGroup.getCheckedRadioButtonId();
                    if (color == colorGroup.findViewById(R.id.color_yellow).getId()) {
                        types.add(MucusData.MucusType.YELLOW);
                    } else if (color == colorGroup.findViewById(R.id.color_cloudy).getId()) {
                        types.add(MucusData.MucusType.CLOUDY);
                    } else if (color == colorGroup.findViewById(R.id.color_cloudy_clear).getId()) {
                        types.add(MucusData.MucusType.CLOUDY_CLEAR);
                    } else if (color == colorGroup.findViewById(R.id.color_clear).getId()) {
                        types.add(MucusData.MucusType.CLEAR);
                    } else if (color == colorGroup.findViewById(R.id.color_red).getId()) {
                        types.add(MucusData.MucusType.RED);
                    } else if (color == colorGroup.findViewById(R.id.color_brown).getId()) {
                        types.add(MucusData.MucusType.BROWN);
                    }
                } else {
                    // Appearance
                    int appearance = appearanceGroup.getCheckedRadioButtonId();
                    if (appearance == appearanceGroup.findViewById(R.id.appearance_damp_button).getId()) {
                        types.add(MucusData.MucusType.DAMP);
                        data.setMucusNumber(isLubricative ? 10 : 2);
                    } else if (appearance == appearanceGroup.findViewById(R.id.appearance_wet_button).getId()) {
                        types.add(MucusData.MucusType.WET);
                        data.setMucusNumber(isLubricative ? 10 : 2);
                    } else if (appearance == appearanceGroup.findViewById(R.id.appearance_shiny_button).getId()) {
                        types.add(MucusData.MucusType.SHINY);
                        data.setMucusNumber(isLubricative ? 10 : 4);
                    }
                }
                data.setMucusTypes(types);
                mainActivity.setMucusData(data.create());

                // Go back to DayFragment
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }


}
