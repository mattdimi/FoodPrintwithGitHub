package com.example.foodprint;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends MotherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navigationView = findViewById(R.id.nav_view_dashboard);
        navigationView.setSelectedItemId(R.id.navigation_dashboard);
        navigationView.setOnNavigationItemSelectedListener(super.mOnNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

    }
}
