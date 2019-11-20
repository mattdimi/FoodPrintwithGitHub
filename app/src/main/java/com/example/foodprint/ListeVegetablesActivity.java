package com.example.foodprint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

//VERSION MASTER 10h19
public class ListeVegetablesActivity extends MotherActivity {


    // CREATION de la liste de legumes sur laquelle on va iterer pour creer les BOUTONS du gridview


    /// Ici pour l'exemple j'ai juste changer le nom des legumes, j'ai mis tout pareil pour le reste





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vegetables);
         BottomNavigationView navigationView  = findViewById(R.id.nav_view_courses);
         navigationView.setSelectedItemId(R.id.liste_courses);
        navigationView.setOnNavigationItemSelectedListener(super.mOnNavigationItemSelectedListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        int[] mois = new int[]{0, 1};
        String name1 = "aubergine";
        String name2 = "poivron";
        String name3 = "ananas";
        float emp = 0;

        String nom_image = "banane";
        Vegetable aubergine = new Vegetable(new int[] {5,6,7}, getString(R.string.aubergine), emp,"aubergine", 0);
        Vegetable poivron = new Vegetable(mois,getString(R.string.poivron), emp, "poivron", 0);
        Vegetable abricot = new Vegetable(new int[] {5,6,7},getString(R.string.abricot), 0.312, "abricot", 0);
        Vegetable ananas = new Vegetable(new int[] {1,2,0,11},getString(R.string.ananas), 0.091, "ananas",0);
        Vegetable banane = new Vegetable(new int[] {10,11,9,1},getString(R.string.banane), 0.698, "banane",0);
        Vegetable cerise = new Vegetable(new int[] {4,5,6},getString(R.string.cerise), 0.584, "cerise",0);
        Vegetable citron = new Vegetable(new int[] {5,8},getString(R.string.citron), 0.529, "citron",0);
        Vegetable clementine = new Vegetable(new int[] {10,11,0,1},getString(R.string.clementine), 0.386, "clementine",0);
        Vegetable fraise = new Vegetable(new int[] {3,4,5},getString(R.string.fraise), 0.591, "fraise",0);
        Vegetable framboise = new Vegetable(new int[] {3,4,5,6,7,8,9,10},getString(R.string.framboise), 0.515, "abricot",0);
        Vegetable kiwi = new Vegetable(new int[] {11,1,2,3},getString(R.string.kiwi), 0.190, "kiwi",0);
        Vegetable melon = new Vegetable(new int[] {5,4},getString(R.string.melon), 0.313, "melon",0);
        Vegetable orange = new Vegetable(new int[] {0,1,10,11},getString(R.string.orange), 0.491, "orange",0);
        Vegetable pasteque = new Vegetable(new int[] {5,6,7},getString(R.string.pasteque), 0.36, "pasteque",0);
        Vegetable raisin = new Vegetable(new int[] {8,9},getString(R.string.raisin), 0.642, "raisin2",0);
        Vegetable tomate = new Vegetable(new int[] {4,5,6,7,8,9},getString(R.string.tomate), 0.343, "tomate",0);



        Vegetable[] array_characters = new Vegetable[]{aubergine, poivron, ananas, abricot, banane, cerise, citron, clementine, fraise, framboise, kiwi, melon, orange, pasteque, raisin, tomate};


        List<Vegetable> lstSource = new ArrayList<>();

        for (Vegetable item : array_characters) {
            lstSource.add(item);

        }
        GridView LagridView;
        LagridView = findViewById(R.id.MygridView);
        LagridView.getColumnWidth();
        GridViewAdapter adapter = new GridViewAdapter(lstSource, this);
        LagridView.setAdapter(adapter);

        }

    }


    class GridViewAdapter extends BaseAdapter {

        List<Vegetable> lstSource;    // liste de legumes sur laquelle on itere pour creer les boutons
        Context mContext;

        public GridViewAdapter(List<Vegetable> lstSource, Context mContext) {


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

                // On trouve le legume associé à la case
                final Vegetable ProduitChoisi = lstSource.get(position);
                // NB : ProduitChoisi est un objet de la class Vegetable, il a donc toutes les méthodes associées getMois, getNom, etc
                String nom_produit = ProduitChoisi.getNom();


                /// creation de tous les boutons
                final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.casesdelagrid, null);


                /// Affichage de la photo des fruits

                final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

                int resourceId = mContext.getResources().getIdentifier(ProduitChoisi.getMnemonic(), "drawable", mContext.getPackageName());
                imageView.setImageResource(resourceId);


                /// Affichager du nom du fruit sur le bouton. Quand on clique sur le bouton ça affiche la fiche du fruit


                // SI VOUS VOULEZ CHANGER LES STYLES DE BOUTON IL FAUT LE FAIRE DANS LE LAYOUT casesdelagrid.xml !!!


                final Button button = (Button) convertView.findViewById(R.id.button);

                button.setText(lstSource.get(position).getNom());    // on met comme texte sur le bouton le nom du legume en position "position" dans la liste des legumes
                button.setBackgroundColor(Color.RED);
                button.setTextColor(Color.WHITE);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //Toast.makeText(mContext, nom_produit, Toast.LENGTH_SHORT).show(); //affiche un toast avec le nom du produit selectionné

                        Intent passageSuite = new Intent();
                        passageSuite.setClass(mContext, FicheProduitActivity.class);
                        Toast.makeText(mContext, ProduitChoisi.getNom(), Toast.LENGTH_LONG).show();

                        passageSuite.putExtra("produitchoisi", ProduitChoisi);   // on passe dans le intent le vegetable associé au bouton

                        passageSuite.putExtra("position du fruit", position);

                        mContext.startActivity(passageSuite);         // on passe a l'activité FicheProduit

                    }
                });


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //Toast.makeText(mContext, nom_produit, Toast.LENGTH_SHORT).show(); //affiche un toast avec le nom du produit selectionné

                        Intent passageSuite = new Intent();
                        passageSuite.setClass(mContext, FicheProduitActivity.class);
                        Toast.makeText(mContext, ProduitChoisi.getNom(), Toast.LENGTH_LONG).show();

                        passageSuite.putExtra("produitchoisi", ProduitChoisi);   // on passe dans le intent le vegetable associé au bouton

                        passageSuite.putExtra("position du fruit", position);

                        mContext.startActivity(passageSuite);         // on passe a l'activité FicheProduit

                    }
                });


            }
            return convertView;

        }
    }

