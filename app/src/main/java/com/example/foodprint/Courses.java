package com.example.foodprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Courses extends MotherActivity {

    ArrayList<String> Liste_Courses = new ArrayList<>();

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



        Intent produitajouté = getIntent();
        Vegetable ProduitAjouté = (Vegetable) produitajouté.getSerializableExtra("produitAjout");
        int positionDuProduit = produitajouté.getIntExtra("position du fruit", 0);

        // ajout du produit à la liste de courses
        if(ProduitAjouté != null){
            Liste_Courses.add(ProduitAjouté.getNom());
        }


        ListView listView = findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Liste_Courses);

        listView.setAdapter(arrayAdapter);


    }

    public void AjouterProduit(View view) {
        Intent afficherliste = new Intent();
        afficherliste.setClass(this,ListeVegetablesActivity.class);
        startActivity(afficherliste);



    }
}
