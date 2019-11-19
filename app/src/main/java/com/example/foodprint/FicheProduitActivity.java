package com.example.foodprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FicheProduitActivity extends AppCompatActivity {


    private Vegetable ProduitChoisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_produit);
        Intent recupProduitChoisi = getIntent();
        Vegetable ProduitChoisi = (Vegetable) recupProduitChoisi.getSerializableExtra("produitchoisi");   // on recupère l'objet "vegetables" correspondant au produit choisi

        //affichage de toutes les infos du legume en modifiant le layout

        TextView tvPeriode = findViewById(R.id.textPeriode);
        TextView tvEmpreinte = findViewById(R.id.textEmpreinte);

        String[] Liste_Mois = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
        int[] Liste_Mois_De_Saison = ProduitChoisi.getMois();
        int nombreMoisDeSaison = Liste_Mois_De_Saison.length;

        String mois_minimum = Liste_Mois[Liste_Mois_De_Saison[0]];   // premier mois de la liste des mois de Saison du produit
        String mois_maximum = Liste_Mois[Liste_Mois_De_Saison[nombreMoisDeSaison-1]];   // dernier mois de la liste des mois de Saison du produit

        tvPeriode.setText("De Saison de "+mois_minimum+" à "+mois_maximum);
        tvEmpreinte.setText("Empreinte carbone : "+ProduitChoisi.getEmpreinte_carbone());




    }

    // pour les 2 boutons qui suivent on va recuperer le legume qu'on a stocké dans le Intent (étiquette : "produitchoisi")
    //NB : dans le bouton Retour, on ne l'utile pas
    //     dans le bouton AJouter, on va fzire en sorte d'ajouter ce legume a la liste de course, en faisant encore un putExtra

    public void AjouterListe(View view) {
        Intent ajoutListe = getIntent();
        ajoutListe.setClass(this, Courses.class);
        ajoutListe.putExtra("produitAjout",ProduitChoisi);





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
