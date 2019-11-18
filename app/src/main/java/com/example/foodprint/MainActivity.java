package com.example.foodprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends MotherActivity implements  SensorEventListener{
    BottomNavigationView navigationView;
    private final String CHANNEL_ID = "My_Notification_Chanel";


    TextView steps_today,calories_today,distance_today,weight_main,
            days_remaining_main,percentage_view,main_user_weight, main_distance_unit;

    Toolbar toolbar;
    SensorManager sm;
    ProgressBar steps;
    SharedPreferences sharedPreferences;

    int total_steps_today,reboot_steps,effective_calories_today,
            reboot_steps_before_today,max_progress,
            percentage_achieved,calories_to_lose,days_remaining;

    float theoretical_calorie_deficit,height,distance,weight;

    private boolean in_m, in_kg, in_km;

    Sensor step_counter;

    Calendar calendar;

    SimpleDateFormat simpleDateFormat;

    static MyDatabase myDatabase;

    DecimalFormat decimalFormat;

    NotificationCompat.Builder notificationBuilder;

    Unit_Converter unit_converter;

    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView =  findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.navigation_home);
        navigationView.setOnNavigationItemSelectedListener(super.mOnNavigationItemSelectedListener);








                unit_converter = new Unit_Converter();


                steps_today = findViewById(R.id.steps_taken_today);
                distance_today = findViewById(R.id.distance_today);
                calories_today = findViewById(R.id.calories_today);
                weight_main = findViewById(R.id.weight_main);
                days_remaining_main = findViewById(R.id.days_remaining_main);
                percentage_view = findViewById(R.id.pourcentage_view);
                steps = findViewById(R.id.steps_progress);

                toolbar = findViewById(R.id.toolbar);
                toolbar.setTitle(R.string.app_name);
                setSupportActionBar(toolbar);


                if (step_counter == null) {
                    registerStepCounter();
                }
                sharedPreferences = getSharedPreferences("settings_activity_prefs",MODE_PRIVATE);
                max_progress = sharedPreferences.getInt(getString(R.string.daily_step_target),1);
                height = sharedPreferences.getFloat(getString(R.string.height),0f);
                steps.setMax(max_progress);
                steps.setSecondaryProgress(max_progress);

                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                calendar = Calendar.getInstance();

                days_remaining = sharedPreferences.getInt("days_remaining",0);
                calories_to_lose = sharedPreferences.getInt("calories_to_lose",0);
                theoretical_calorie_deficit = sharedPreferences.getFloat("theoretical_calorie_deficit",0);

                in_kg = sharedPreferences.getBoolean("in_kg",true);
                in_km = sharedPreferences.getBoolean("in_km",true);
                in_m = sharedPreferences.getBoolean("in_m",true);

                main_distance_unit = findViewById(R.id.main_distance_unit);
                main_user_weight = findViewById(R.id.main_weight_textview);

                if (in_kg){
                    main_user_weight.setText(getString(R.string.weight));
                    weight = sharedPreferences.getFloat(getString(R.string.weight),0);
                }else{
                    main_user_weight.setText(getString(R.string.weight_lbs));
                    weight = unit_converter.to_lbs(sharedPreferences.getFloat(getString(R.string.weight),0));
                }

                if (in_km){
                    main_distance_unit.setText(getString(R.string.km));
                }else{
                    main_distance_unit.setText(getString(R.string.miles));
                }

                decimalFormat = new DecimalFormat();
                decimalFormat.setMaximumFractionDigits(2);
                decimalFormat.setMinimumFractionDigits(2);
                decimalFormat.setDecimalSeparatorAlwaysShown(true);

                myDatabase = Room.databaseBuilder(MainActivity.this,MyDatabase.class,"DayDb")
                        .allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build();

                refresh_display();



            }


            private void registerStepCounter(){
                sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                step_counter = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                sm.registerListener(this, step_counter, SensorManager.SENSOR_DELAY_UI);
            }



            private void refresh_display() {
                steps_today.setText(String.valueOf(total_steps_today));
                distance_today.setText(String.valueOf(distance));
                calories_today.setText(String.valueOf(effective_calories_today));
                weight_main.setText(String.valueOf(weight));
                days_remaining_main.setText(String.valueOf(days_remaining));
                percentage_view.setText(percentage_achieved+" %");

            }







            @Override
            protected void onResume() {
                super.onResume();
                if (step_counter == null) {
                    registerStepCounter();
                }
                compute(reboot_steps);
            }
            @Override
            protected void onRestart() {
                super.onRestart();
                if (step_counter == null) {
                    registerStepCounter();
                }
                compute(reboot_steps);
            }

            @Override
            protected void onPause() {
                super.onPause();
                sm.unregisterListener(this,step_counter);
            }

            @Override
            protected void onStop() {
                super.onStop();
                sm.unregisterListener(this,step_counter);

            }




            public void compute(int rebootSteps){
                reboot_steps_before_today = sharedPreferences.getInt(simpleDateFormat.format(calendar.getTime()),0);

                if (reboot_steps_before_today ==0){
                    sharedPreferences.edit().putInt(simpleDateFormat.format(calendar.getTime()),reboot_steps).commit();
                    total_steps_today=0;
                }
                else{
                    total_steps_today = Math.abs(rebootSteps-reboot_steps_before_today);
                }
                steps_today.setText(String.valueOf(total_steps_today));
                percentage_achieved = (total_steps_today*100/max_progress);

                if (in_km && in_m){
                    distance = total_steps_today*(height/2)/1000f;
                    distance = Float.parseFloat(decimalFormat.format(distance));}
                else if (!in_km && in_m){
                    distance = unit_converter.to_mile(total_steps_today*(height/2)/1000f);
                    distance = Float.parseFloat(decimalFormat.format(distance));
                }
                else if (!in_m && in_km ){
                    distance = total_steps_today*(unit_converter.to_meter(height)/2)/1000f;
                    distance = Float.parseFloat(decimalFormat.format(distance));
                }
                else{
                    distance = unit_converter.to_mile(total_steps_today*(unit_converter.to_meter(height)/2)/1000f);
                    distance = Float.parseFloat(decimalFormat.format(distance));
                }
                distance_today.setText(String.valueOf(distance));
                if (in_kg && in_km)
                {
                    effective_calories_today = (int) (distance * weight * 0.726);
                }
                else if (!in_kg && in_km)
                {
                    effective_calories_today = (int) (distance * unit_converter.to_kg(weight)*0.726);
                }
                else if (in_kg && !in_km)
                {
                    effective_calories_today = (int) (unit_converter.to_km(distance) * (weight)*0.726);
                }
                else
                {
                    effective_calories_today = (int) (unit_converter.to_km(distance) * unit_converter.to_kg(weight)*0.726);
                }
                calories_today.setText(String.valueOf(effective_calories_today));

                Day day = new Day();
                day.setDate(simpleDateFormat.format(calendar.getTime()));
                day.setSteps(total_steps_today);
                day.setCalories(effective_calories_today);
                day.setDistance(distance);
                MainActivity.myDatabase.myDao().newDay(day);


                if (total_steps_today<=max_progress){
                    steps.setProgress(total_steps_today);}
                else{
                    steps.setProgress(max_progress);
                }
                weight_main.setText(String.valueOf(weight));
                days_remaining_main.setText(String.valueOf(days_remaining));
                percentage_view.setText(percentage_achieved+" %");
            }


            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                reboot_steps = (int) sensorEvent.values[0];
                compute(reboot_steps);
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }




        }


