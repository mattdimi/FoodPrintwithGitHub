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

    int[] mois = new int[]{0, 1};
    String name1 = "aubergine";
    String name2 = "poivron";
    String name3 = "ananas";
    int emp = 0;
    // ImageView im =


    Vegetable aubergine = new Vegetable(mois, name1, emp /*, im */);
    Vegetable ananas = new Vegetable(mois, name2, emp  /*, im */);
    Vegetable poivron = new Vegetable(mois, name3, emp /*, im */);

    Vegetable[] array_characters = new Vegetable[]{aubergine, poivron, ananas};

    ///

    List<Vegetable> lstSource = new ArrayList<>();


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


        setUpList();
        GridView LagridView;
        LagridView = findViewById(R.id.MygridView);
        LagridView.getColumnWidth();
        GridViewAdapter adapter = new GridViewAdapter(lstSource, this);
        LagridView.setAdapter(adapter);

    }

    private void setUpList() {

        for (Vegetable item : array_characters) {
            lstSource.add(item);

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


                /// Affichage de la photo des fruits   ---> A COMPLETER

                final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.ananas);  // changer cette ligne pour mettre la bonne image associée a chaque legume


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

                        startActivity(passageSuite);         // on passe a l'activité FicheProduit

                    }
                });


            }
            return convertView;

        }
    }
}
