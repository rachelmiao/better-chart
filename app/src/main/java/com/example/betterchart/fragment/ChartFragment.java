package com.example.betterchart.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.betterchart.BuildConfig;
import com.example.betterchart.R;
import com.example.betterchart.chart.ChartRenderer;
import com.example.betterchart.chart.Cycle;
import com.example.betterchart.chart.DayInfo;
import com.example.betterchart.chart.FlowType;
import com.example.betterchart.chart.MucusData;
import com.example.betterchart.chart.Sticker;
import com.example.betterchart.data.SingletonRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartFragment extends Fragment {

    private static final String LOGGER = ChartFragment.class.getName();

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

        renderChartFromAirtable(chartContainer);

        return view;
    }

    // Note: This is for prototyping
    void renderChartFromAirtable(final ViewGroup chartContainer) {
        String url = BuildConfig.AIRTABLE_URL + "days?maxRecords=10&filterByFormula=%7Busername%7D%3D'rachelmiao'";
        final Map<String, String> headers = new HashMap<>();
        String airtableKey = BuildConfig.AIRTABLE_KEY;
        headers.put("Authorization", "Bearer " + airtableKey);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray records = response.getJSONArray("records");
                            List<DayInfo> days = new ArrayList<>();
                            for (int i = 0; i < records.length(); i++) {
                                JSONObject record = records.getJSONObject(i);
                                JSONObject fields = record.getJSONObject("fields");

                                Log.d(ChartFragment.class.getName(), "Fields: " + fields.toString());

                                DayInfo.Builder dayInfo = new DayInfo.Builder();

                                // If no date, continue
                                if (!fields.has("date")) {
                                    continue;
                                }
                                String date = fields.getString("date");
                                dayInfo.setDate(LocalDate.parse(date));

                                // Flow Type
                                if (fields.has("flowType")) {
                                    String flowTypeString = fields.getString("flowType");
                                    FlowType flowType = FlowType.fromString(getContext(), flowTypeString);
                                    dayInfo.setFlowType(flowType);
                                }

                                // Todo: set mucus etc
                                MucusData.Builder mucusData = new MucusData.Builder();
                                if (fields.has("mucusNumber")) {
                                    int mucusNumber = fields.getInt("mucusNumber");
                                    mucusData.setMucusNumber(mucusNumber);
                                }
                                List<MucusData.MucusType> types = new ArrayList<>();
                                if (fields.has("mucusTypes")) {
                                    JSONArray arr = fields.getJSONArray("mucusTypes");
                                    for (int j = 0; j < arr.length(); j++) {
                                        String typeString = arr.getString(j);
                                        MucusData.MucusType type = MucusData.MucusType.fromString(typeString);
                                        if (type != null) {
                                            types.add(type);
                                        }
                                    }
                                }
                                mucusData.setMucusTypes(types);
                                dayInfo.setMucusData(mucusData.create());

                                if (fields.has("isFirstDay")) {
                                    dayInfo.setIsFirstDay(fields.getBoolean("isFirstDay"));
                                }
                                days.add(dayInfo.create());
                            }

                            Cycle cycle = Cycle.fromDays(days);
                            ChartRenderer.render(cycle, chartContainer);

                        } catch (JSONException e) {
                            Log.e(LOGGER, e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOGGER, error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        SingletonRequestQueue.getInstance(getContext()).getRequestQueue().add(request);
    }

}
