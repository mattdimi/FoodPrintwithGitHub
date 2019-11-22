package com.example.foodprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Comparaison extends AppCompatActivity {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    List<Day> dayList;
    ArrayList<BarEntry> entries =new ArrayList<>();
    ArrayList<String> labels=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparaison);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);


        barChart = findViewById(R.id.graph);

        String[] transportName = new String[]{"Train","Voiture moyenne", "Bus", "Avion"};
        if (MainActivity.myDatabase.myDao() !=null) {
            dayList = MainActivity.myDatabase.myDao().getAllDays();
            Day day = dayList.get(0);
            float distance = day.getDistance();
            for (int i=0;i<5;i++){
                int coeff = 0;
                switch (i){
                    case 0:
                        coeff = 14;
                        entries.add(new BarEntry(i, distance*coeff));
                        labels.add(transportName[i]);
                        break;
                    case 1:
                        coeff = 79;
                        entries.add(new BarEntry(i, distance*coeff));
                        labels.add(transportName[i]);
                        break;
                    case 2:
                        coeff = 55;
                        entries.add(new BarEntry(i, distance*coeff));
                        labels.add(transportName[i]);
                        break;
                    case 3:
                        coeff = 285;
                        entries.add(new BarEntry(i, distance*coeff));
                        labels.add(transportName[i]);
                        break;
                }

            }
        }

        barDataSet = new BarDataSet(entries, getString(R.string.carbon_consu));
        barDataSet.setColors(R.color.colorPrimary);
        setupGraph(barDataSet);
    }


    public void setupGraph(BarDataSet barDataSet){
        barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(true);
        barChart.setDrawGridBackground(false);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setAxisMinimum(0f);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        barChart.invalidate();
    }
}
