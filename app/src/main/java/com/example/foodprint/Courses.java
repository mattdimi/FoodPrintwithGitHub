package com.example.foodprint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Courses extends MotherActivity implements View.OnClickListener {

    ArrayList<Unserialized_vegetable> Liste_Courses = new ArrayList<>();
    ArrayList<String> liste = new ArrayList<>();
    Button new_list, add_item;
    ListView listView;
    double total_carbone = 0;
    TextView textView;
    DecimalFormat df;

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

        new_list = findViewById(R.id.new_list);
        new_list.setOnClickListener(this);

        add_item = findViewById(R.id.boutonAjouter);
        add_item.setOnClickListener(this);

        textView = findViewById(R.id.total_carbone);
        df = new DecimalFormat("#.###");




        // ajout du produit à la liste de courses
        if (MainActivity.myDatabase !=null){
                Liste_Courses = (ArrayList<Unserialized_vegetable>) MainActivity.myDatabase.myDao().getAllVegetables();
                for (Unserialized_vegetable vegetable : Liste_Courses)
                {

                    Double d = vegetable.emp*vegetable.quantity;
                    total_carbone +=d;
                    String s = df.format(d);
                    liste.add(0,vegetable.nom + " " + vegetable.quantity + " " + "kg" + " "+s + "kg de CO2" );

                }

        }


         listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Courses.this, android.R.layout.simple_list_item_1, liste);
//android.R.layout.simple_list_item_1
        listView.setAdapter(arrayAdapter);
        textView.setText(df.format(total_carbone));


    }

    public void AjouterProduit(View view) {
        Intent afficherliste = new Intent();
        afficherliste.setClass(this,ListeVegetablesActivity.class);
        startActivity(afficherliste);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_list:
                if (MainActivity.myDatabase!=null){
                        MainActivity.myDatabase.myDao().DeleteAll();
                        textView.setText(String.valueOf(0));

                 listView = findViewById(R.id.listView);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Courses.this, android.R.layout.simple_list_item_1, new String[]{});
//android.R.layout.simple_list_item_1
                listView.setAdapter(arrayAdapter);

                }
                break;
            case R.id.boutonAjouter:
                AjouterProduit(view);
                break;
        }
    }



    class ListViewAdapter extends BaseAdapter {

        ArrayList<Unserialized_vegetable> lstSource;    // liste de legumes sur laquelle on itere pour creer les boutons
        Context mContext;

        public ListViewAdapter(ArrayList<Unserialized_vegetable> lstSource, Context mContext) {


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


        /// Cette méthode automatise la creartion des cases de la ListeView
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {


                /// creation de toutes les cases
                final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.lignesdelaliste, null);

                Unserialized_vegetable.Vegetable legume_affiche = (Unserialized_vegetable.Vegetable) getItem(position);
                String nom_legume = legume_affiche.getNom();
                double quantite = legume_affiche.getQuantité();
                double empreinte_carbone = legume_affiche.getEmpreinte_carbone()*quantite;

                // parametrage des textes

                //nom legume
                TextView tv = convertView.findViewById(R.id.textViewligne);
                tv.setText(nom_legume+ " en quantité :" +quantite+ "kg  -> empreinte carbone de : "+empreinte_carbone+ " kg de CO2");


            }

            return convertView;

        }
    }


}
