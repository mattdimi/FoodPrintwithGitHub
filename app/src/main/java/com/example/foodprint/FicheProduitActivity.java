package com.example.foodprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FicheProduitActivity extends AppCompatActivity {


    private Vegetable ProduitChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_produit);
        Intent ProduitChoisi = getIntent();
        ProduitChoisi.getSerializableExtra("produitchoisi");   // on recupère l'objet "vegetables" correspondant au produit choisi


    }

    public void AjouterListe(View view) {
        Intent ajoutListe = getIntent();
        ajoutListe.setClass(this, Courses.class);

        // ajouter la putExtra du légume à ajouter

        startActivity(ajoutListe);
        finish();


    }

    public void RetourGrid(View view) {
        Intent retourgrid = getIntent();
        retourgrid.setClass(this, ListeVegetablesActivity.class);
        startActivity(retourgrid);
        finish();


    }
}
