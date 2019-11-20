package com.example.foodprint;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends MotherActivity {
    ListView listView;
    ArrayList<Day> dayArrayList = new ArrayList<>();
    List<Day> dayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView navigationView = findViewById(R.id.nav_view5);
        navigationView.setSelectedItemId(R.id.navigation_dashboard);
        navigationView.setOnNavigationItemSelectedListener(super.mOnNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.list_view);

        if (MainActivity.myDatabase != null) {
            dayList = MainActivity.myDatabase.myDao().getAllDays();

            for (Day day : dayList) {
                dayArrayList.add(0, day);

            }
            DayListAdapter dayAdapter = new DayListAdapter(Dashboard.this, R.layout.adapter_view_layout, dayArrayList);
            listView.setAdapter(dayAdapter);


        }

    }
}