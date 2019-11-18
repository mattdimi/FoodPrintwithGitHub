package com.example.foodprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Courses extends MotherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(super.mOnNavigationItemSelectedListener);

        Toast.makeText(Courses.this, "courses", Toast.LENGTH_LONG).show();

    }

    public void AjouterProduit(View view) {
        Intent afficherlisteproduits = new Intent();
        afficherlisteproduits.setClass(this, ListeVegetablesActivity.class);
        startActivity(afficherlisteproduits);


    }
}
