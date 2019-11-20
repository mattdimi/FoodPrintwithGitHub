package com.example.foodprint;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Settings extends MotherActivity implements View.OnClickListener{

    Toolbar toolbar;
    LayoutInflater inflater;
    EditText user_weight, user_height, user_age, user_target_weight, user_daily_step_target, user_daily_caloric_intake;


    Button height_text_view, weight_text_view, target_weight_text_view, target_calories,
            gender, target_steps, age_button;

    TextView popup_title;
    float weight;
    float height;
    float target_weight;
    int age;
    int bmr;
    int daily_step_target;
    int daily_caloric_intake;
    int max_progress;
    int calories_to_lose;
    int male_gender ;
    Float theoretical_calorie_deficit;
    View popup_view;
    String[] gender_array;
    ArrayAdapter arrayAdapter;
    Spinner spinner;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    AlertDialog.Builder alert_builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_settings);
        bottomNavigationView.setSelectedItemId(R.id.settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(super.mOnNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        calendar = Calendar.getInstance();
        alert_builder = new AlertDialog.Builder(this);
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

        inflater = getLayoutInflater();

        sharedPreferences = getSharedPreferences("settings_activity_prefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        height_text_view = findViewById(R.id.height_textview);
        height_text_view.setOnClickListener(this);
        weight_text_view = findViewById(R.id.weight_textview);
        weight_text_view.setOnClickListener(this);
        target_weight_text_view = findViewById(R.id.target_weight_textview);
        target_weight_text_view.setOnClickListener(this);
        target_calories = findViewById(R.id.caloric_intake);
        target_calories.setOnClickListener(this);
        gender = findViewById(R.id.gender_textview);
        gender.setOnClickListener(this);
        target_steps = findViewById(R.id.dailysteptarget);
        target_steps.setOnClickListener(this);
        age_button = findViewById(R.id.age_textview);
        age_button.setOnClickListener(this);

        weight = sharedPreferences.getFloat(getString(R.string.weight), 0);
        height = sharedPreferences.getFloat(getString(R.string.height), 0);
        target_weight = sharedPreferences.getFloat(getString(R.string.your_target_weight), 0);
        daily_step_target = sharedPreferences.getInt(getString(R.string.daily_step_target), 0);
        daily_caloric_intake = sharedPreferences.getInt(getString(R.string.Daily_caloric_intake), 0);
        age = sharedPreferences.getInt(getString(R.string.age), 0);
        male_gender = sharedPreferences.getInt(getString(R.string.gender), -1);

        user_weight = new EditText(this);
        user_height = new EditText(this);
        user_target_weight = new EditText(this);
        user_daily_step_target = new EditText(this);
        user_daily_caloric_intake = new EditText(this);

        if (male_gender==1) {
            bmr = 1800;
        } else if (male_gender==0) {
            bmr = 1400;
        }
        weight_text_view.setText(getString(R.string.weight));
        height_text_view.setText(getString(R.string.height));

        if (ValidData()) {
            refreshDisplay();
        }
        if (height!=0){
            height_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (weight!=0){
            weight_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (target_weight!=0){
            target_weight_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (age!=0){
            age_button.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (daily_caloric_intake!=0){
            target_calories.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (daily_step_target!=0){
            target_steps.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (male_gender !=-1){
            gender.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
    }

    public void refreshDisplay() {

        height = sharedPreferences.getFloat(getString(R.string.height), 0);
        weight = sharedPreferences.getFloat(getString(R.string.weight), 0);
        target_weight = sharedPreferences.getFloat(getString(R.string.your_target_weight), 0);
        daily_step_target = sharedPreferences.getInt(getString(R.string.daily_step_target), 0);
        daily_caloric_intake = sharedPreferences.getInt(getString(R.string.Daily_caloric_intake), 0);
        calories_to_lose = (int) (weight - target_weight) * 7000;
        editor.putInt("calories_to_lose", calories_to_lose);
        editor.commit();
        compute();

        if (height!=0){
            height_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (weight!=0){
            weight_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (target_weight!=0){
            target_weight_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (age!=0){
            age_button.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (daily_caloric_intake!=0){
            target_calories.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (daily_step_target!=0){
            target_steps.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }
        if (male_gender !=-1){
            gender.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (ValidData()) {
            refreshDisplay();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ValidData()) {
            refreshDisplay();
        }
    }

    public boolean ValidData() {
        if (weight == 0 || height == 0 || target_weight == 0 || daily_caloric_intake == 0 ||
                daily_step_target == 0 || age == 0 || male_gender ==-1) {
            return false;
        } else {
            return true;
        }
    }


    public void compute() {
        theoretical_calorie_deficit = daily_caloric_intake - (daily_step_target * weight * 10) / (2 * 4184) - bmr;
        sharedPreferences.edit().putFloat("theoretical_calorie_deficit", theoretical_calorie_deficit).commit();

        calories_to_lose = sharedPreferences.getInt("calories_to_lose", calories_to_lose);
        if (theoretical_calorie_deficit < 0) {
            max_progress = -(int) (calories_to_lose / theoretical_calorie_deficit);
            editor.putInt("days_remaining", max_progress).commit();

        } else {
            Toast.makeText(Settings.this, getString(R.string.you_should_eat_less), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        popup_view = inflater.inflate(R.layout.alertdialog_layout, null);
        switch (view.getId()) {
            case R.id.age_textview:
                popup_title = popup_view.findViewById(R.id.alert_title);
                popup_title.setText(getString(R.string.age));
                user_age = popup_view.findViewById(R.id.alert_text);
                user_age.setText(String.valueOf(sharedPreferences.getInt(getString(R.string.age), 0)));
                user_age.setInputType(InputType.TYPE_CLASS_NUMBER);
                user_age.setLines(1);
                alert_builder.setView(popup_view);
                alert_builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        age = Integer.parseInt(user_age.getText().toString());
                        editor.putInt(getString(R.string.age), age).commit();
                        if (age!=0){
                            age_button.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
                        }

                    }
                });
                alert_builder.show();
                break;
            case R.id.gender_textview:
                popup_view = inflater.inflate(R.layout.alertdialog_spinnerlayout, null);
                popup_title = popup_view.findViewById(R.id.alert_title);
                popup_title.setText(R.string.gender);
                spinner = popup_view.findViewById(R.id.alert_spinner);
                gender_array = new String[]{getString(R.string.woman), getString(R.string.man)};
                arrayAdapter = new ArrayAdapter
                        (Settings.this, android.R.layout.simple_list_item_1, gender_array);
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i) {
                            case 0:
                                male_gender = 0;
                                bmr = 1400;
                                break;
                            case 1:
                                male_gender = 1;
                                bmr = 1800;
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                spinner.setSelection(male_gender);
                alert_builder.setView(popup_view);
                alert_builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putInt(getString(R.string.gender), male_gender).commit();
                        if (male_gender !=-1){
                            gender.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
                        }
                    }
                });
                alert_builder.show();
                break;
            case R.id.height_textview:
                popup_title = popup_view.findViewById(R.id.alert_title);
                popup_title.setText(getString(R.string.height_no_m));
                user_height = popup_view.findViewById(R.id.alert_text);
                user_height.setText(String.valueOf(sharedPreferences.getFloat(getString(R.string.height), 0)));
                user_height.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                user_height.setLines(1);
                alert_builder.setView(popup_view);
                alert_builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        height = Float.parseFloat(user_height.getText().toString());
                        editor.putFloat(getString(R.string.height), height).commit();
                        if (height!=0){
                            height_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
                        }
                    }
                });
                alert_builder.show();
                break;
            case R.id.weight_textview:
                popup_title = popup_view.findViewById(R.id.alert_title);
                popup_title.setText(getString(R.string.weight_no_kg));
                user_weight = popup_view.findViewById(R.id.alert_text);
                user_weight.setText(String.valueOf(sharedPreferences.getFloat(getString(R.string.weight), 0)));
                user_weight.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                user_weight.setLines(1);
                alert_builder.setView(popup_view);
                alert_builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        weight = Float.parseFloat(user_weight.getText().toString());
                        editor.putFloat(getString(R.string.weight), weight).commit();
                        if (weight!=0){
                            weight_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
                        }
                    }
                });
                alert_builder.show();
                break;
            case R.id.target_weight_textview:
                popup_title = popup_view.findViewById(R.id.alert_title);
                popup_title.setText(getString(R.string.your_target_weight));
                user_target_weight = popup_view.findViewById(R.id.alert_text);
                user_target_weight.setText(String.valueOf(sharedPreferences.getFloat(getString
                        (R.string.your_target_weight), 0)));
                user_target_weight.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                user_target_weight.setLines(1);
                alert_builder.setView(popup_view);
                alert_builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        target_weight = Float.parseFloat(user_target_weight.getText().toString());
                        editor.putFloat(getString(R.string.your_target_weight), target_weight).commit();
                        if (target_weight!=0){
                            target_weight_text_view.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
                        }
                    }
                });
                alert_builder.show();
                break;
            case R.id.caloric_intake:
                popup_view = inflater.inflate(R.layout.alertdialog_layout, null);
                popup_title = popup_view.findViewById(R.id.alert_title);
                popup_title.setText(getString(R.string.Daily_caloric_intake));
                user_daily_caloric_intake = popup_view.findViewById(R.id.alert_text);
                user_daily_caloric_intake.setText(String.valueOf(sharedPreferences.getInt(
                        getString(R.string.Daily_caloric_intake), 0)));
                user_daily_caloric_intake.setLines(1);
                alert_builder.setView(popup_view);
                alert_builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().
                                putInt(getString(R.string.Daily_caloric_intake),
                                        Integer.parseInt(user_daily_caloric_intake.getText().
                                                toString())).commit();
                        if (daily_caloric_intake!=0){
                            target_calories.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
                        }
                    }
                });
                alert_builder.show();
                break;
            case R.id.dailysteptarget:
                popup_view = inflater.inflate(R.layout.alertdialog_layout, null);
                popup_title = popup_view.findViewById(R.id.alert_title);
                popup_title.setText(getString(R.string.daily_step_target));
                user_daily_step_target = popup_view.findViewById(R.id.alert_text);
                user_daily_step_target.setText(String.valueOf(sharedPreferences.getInt(getString(R.string.daily_step_target), 0)));
                user_daily_step_target.setLines(1);
                alert_builder.setView(popup_view);
                alert_builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().
                                putInt(getString(R.string.daily_step_target),
                                        Integer.parseInt(user_daily_step_target.getText().
                                                toString())).commit();
                        if (daily_step_target!=0){
                            target_steps.setBackground(getDrawable(R.drawable.rectangle_shape_adapter_view));
                        }
                    }
                });
                alert_builder.show();
                break;

        }
    }


}



