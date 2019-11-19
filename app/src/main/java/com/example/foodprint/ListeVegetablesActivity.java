package com.example.foodprint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

//VERSION MASTER 10h19
public class ListeVegetablesActivity extends AppCompatActivity {



    // CREATION de la liste de legumes sur laquelle on va iterer pour creer les BOUTONS du gridview


    // Ici pour l'exemple j'ai juste changer le nom des legumes, j'ai mis tout pareil pour le reste

    int[] mois = new int[] {0, 1};
    String name1 = "aubergine";
    String name2 = "poivron";
    String name3 = "ananas";
    int emp = 0;
    // ImageView im =





    Vegetable aubergine = new Vegetable(mois, name1, emp /*, im */);
    Vegetable ananas = new Vegetable(mois, name2, emp  /*, im */);
    Vegetable poivron = new Vegetable(mois, name3, emp /*, im */);

    Vegetable[] array_characters = new Vegetable[]{aubergine, poivron, ananas};

    List<Vegetable> lstSource = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vegetables);

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
            /*
            Log.i("passage", "heeeey creation");
            String msg = "";
            for(int i=0; i< lstSource.size(); i++){
                msg += lstSource.get(i);
            }
            Log.i("passage", "here : "+msg);
            */
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


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Button button;
            if (convertView == null) {

                // creation de tous les boutons

                button = new Button(mContext);
                button.setLayoutParams(new GridView.LayoutParams(385, 385));
                button.setPadding(8, 8, 8, 8);
                button.setText(lstSource.get(position).getNom());    // on met comme texte sur le bouton le nom du legume en position "position" dans la liste des legumes
                button.setBackgroundColor(Color.RED);
                button.setTextColor(Color.WHITE);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Vegetable ProduitChoisi = lstSource.get(position);
                        // NB : ProduitChoisi est un objet de la class Vegetable, il a donc toutes les méthodes associées getMois, getNom, etc

                        String nom_produit = ProduitChoisi.getNom();

                        //Toast.makeText(mContext, nom_produit, Toast.LENGTH_SHORT).show(); //affiche un toast avec le nom du produit selectionné

                        Intent passageSuite = new Intent();
                        passageSuite.setClass(mContext, FicheProduitActivity.class);
                        passageSuite.putExtra("produitchoisi", ProduitChoisi);   // on passe dans le intent le vegetable associé au bouton
                        startActivity(passageSuite);         // on passe a l'activité FicheProduit

                    }
                });

            } else
                button = (Button) convertView;
            return button;

        }

    }
}
