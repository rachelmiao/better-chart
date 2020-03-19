package com.example.betterchart;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.betterchart.chart.DayInfo;
import com.example.betterchart.chart.FlowType;
import com.example.betterchart.chart.MucusData;
import com.example.betterchart.chart.Sticker;
import com.example.betterchart.fragment.ChartFragment;
import com.example.betterchart.fragment.DayFragment;
import com.example.betterchart.fragment.MeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.threeten.bp.LocalDate;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    private DayInfo.Builder dayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Default DayInfo with no sticker.
        dayInfo = new DayInfo.Builder()
                .setDate(LocalDate.now())
                .setFlowType(FlowType.NONE)
                .setMucusData(new MucusData.Builder().create())
                .setIsFirstDay(false)
                .setSticker(Sticker.UNDEFINED);

        openFragment(DayFragment.newInstance());
        configureBottomNavigation();
    }

    private void configureBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_day:
                                openFragment(DayFragment.newInstance());
                                return true;
                            case R.id.navigation_chart:
                                openFragment(ChartFragment.newInstance());
                                return true;
                            case R.id.navigation_me:
                                openFragment(MeFragment.newInstance());
                                return true;
                        }
                        return false;
                    }
                };
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public DayInfo getDayInfo() {
        return dayInfo.create();
    }

    public void setFlowType(FlowType flowType) {
        dayInfo.setFlowType(flowType);

        // Also need to update sticker.
        // TODO factor sticker logic out somewhere.
        dayInfo.setSticker(flowType == FlowType.NONE ? Sticker.UNDEFINED : Sticker.RED);

        // TODO update database
    }

}
