package com.example.foodprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FicheProduitActivity extends AppCompatActivity implements View.OnClickListener{
        ImageButton add, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_produit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        Intent recupProduitChoisi = getIntent();
        Unserialized_vegetable.Vegetable ProduitChoisi = (Unserialized_vegetable.Vegetable) recupProduitChoisi.getSerializableExtra("produitchoisi");   // on recupère l'objet "vegetables" correspondant au produit choisi
        int position = recupProduitChoisi.getIntExtra("position du fruit", 0);

        //affichage de toutes les infos du legume en modifiant le layout

        TextView tvPeriode = findViewById(R.id.textPeriode);
        TextView tvEmpreinte = findViewById(R.id.textEmpreinte);


        add = findViewById(R.id.addbutton);
        back = findViewById(R.id.back_button);

        add.setOnClickListener(this);
        back.setOnClickListener(this);

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

        Intent recupProduitChoisi = getIntent();
        Unserialized_vegetable.Vegetable ProduitChoisi = (Unserialized_vegetable.Vegetable) recupProduitChoisi.getSerializableExtra("produitchoisi");   // on recupère l'objet "vegetables" correspondant au produit choisi
        Intent ajoutListe = getIntent();

        EditText et;
        String quantité;
        double quantité_number ;
         quantité_number = 0;


        try {

             et = findViewById(R.id.editTextQuantite);
             quantité = et.getText().toString();
            quantité_number = Double.parseDouble(quantité);
            ProduitChoisi.setQuantité(quantité_number);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (MainActivity.myDatabase !=null){
            MainActivity.myDatabase.myDao().newVegetable(new Unserialized_vegetable(ProduitChoisi));
        }


        ajoutListe.setClass(this, Courses.class);
        ajoutListe.putExtra("produitAjout", ProduitChoisi);
        ajoutListe.putExtra("quantité", quantité_number);

        startActivity(ajoutListe);
        finish();




    }

    public void RetourGrid(View view) {
        Intent retourgrid = getIntent();
        retourgrid.setClass(this, ListeVegetablesActivity.class);
        startActivity(retourgrid);
        finish();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addbutton:
                AjouterListe(view);
                break;
            case R.id.back_button:
                RetourGrid(view);
        }
    }
}
