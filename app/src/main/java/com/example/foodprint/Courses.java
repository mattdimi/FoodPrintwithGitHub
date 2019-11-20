package com.example.foodprint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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



    class ListViewAdapter extends BaseAdapter {

        List<Vegetable> lstSource;    // liste de legumes sur laquelle on itere pour creer les boutons
        Context mContext;

        public ListViewAdapter(List<Vegetable> lstSource, Context mContext) {


            this.lstSource = lstSource;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return lstSource.size();
        }

        @Override
        public Object getItem(int position) {
            return lstSource.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        /// Cette méthode automatise la creartion des cases de la GridView
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {


                /// creation de tous les boutons
                final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.lignesdelaliste, null);

                Vegetable legume_affiche = (Vegetable) getItem(position);
                String nom_legume = legume_affiche.getNom();
                double quantite = legume_affiche.getQuantité();


                // parametrage des textes

                //nom legume
                TextView tv = convertView.findViewById(R.id.textViewligne);
                tv.setText(nom_legume);

                //quantite
                TextView tv2 = convertView.findViewById(R.id.textViewligne2);
                tv2.setText("Quantité : "+quantite+" g");


            }

            return convertView;

        }
    }


}
