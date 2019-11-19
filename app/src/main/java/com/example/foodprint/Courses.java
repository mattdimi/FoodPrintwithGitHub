package com.example.foodprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Courses extends MotherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
         BottomNavigationView navigationView  = findViewById(R.id.nav_view_courses);
         navigationView.setSelectedItemId(R.id.liste_courses);
        navigationView.setOnNavigationItemSelectedListener(super.mOnNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

    }

    public void AjouterProduit(View view) {
        Intent afficherliste = new Intent();
        afficherliste.setClass(this,ListeVegetablesActivity.class);
        startActivity(afficherliste);



    }
}
