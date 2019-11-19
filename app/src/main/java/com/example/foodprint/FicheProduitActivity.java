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

    // pour les 2 boutons qui suivent on va recuperer le legume qu'on a stocké dans le Intent (étiquette : "produitchoisi")
    //NB : dans le bouton Retour, on ne l'utile pas
    //     dans le bouton AJouter, on va fzire en sorte d'ajouter ce legume a la liste de course, en faisant encore un putExtra

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
