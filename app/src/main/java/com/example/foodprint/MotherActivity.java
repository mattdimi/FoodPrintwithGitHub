package com.example.foodprint;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

class MotherActivity extends AppCompatActivity {


    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(MotherActivity.this, MainActivity.class);
                    startActivity(intent);
                    item.setChecked(true);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent1 = new Intent(MotherActivity.this, Dashboard.class);
                    startActivity(intent1);
                    item.setChecked(true);
                    return true;
                case R.id.settings:
                    Intent intent2 = new Intent(MotherActivity.this, Settings.class);
                    startActivity(intent2);
                    item.setChecked(true);
                    return true;
                case R.id.liste_courses:
                    Intent intent3 = new Intent(MotherActivity.this, Courses.class);
                    startActivity(intent3);
                    item.setChecked(true);
                    return true;
            }
            return false;
        }
    };


}