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



        Unserialized_vegetable.Vegetable aubergine = new Unserialized_vegetable.Vegetable(new int[] {5,6,7}, getString(R.string.aubergine), 0.944,"aubergine", 0);
        Unserialized_vegetable.Vegetable abricot = new Unserialized_vegetable.Vegetable(new int[] {5,6,7},getString(R.string.abricot), 0.312, "abricot", 0);
        Unserialized_vegetable.Vegetable ananas = new Unserialized_vegetable.Vegetable(new int[] {1,2,0,11},getString(R.string.ananas), 0.091, "ananas",0);
        Unserialized_vegetable.Vegetable banane = new Unserialized_vegetable.Vegetable(new int[] {10,11,9,1},getString(R.string.banane), 0.698, "banane",0);
        Unserialized_vegetable.Vegetable cerise = new Unserialized_vegetable.Vegetable(new int[] {4,5,6},getString(R.string.cerise), 0.584, "cerise",0);
        Unserialized_vegetable.Vegetable citron = new Unserialized_vegetable.Vegetable(new int[] {5,8},getString(R.string.citron), 0.529, "citron",0);
        Unserialized_vegetable.Vegetable clementine = new Unserialized_vegetable.Vegetable(new int[] {10,11,0,1},getString(R.string.clementine), 0.386, "clementine",0);
        Unserialized_vegetable.Vegetable fraise = new Unserialized_vegetable.Vegetable(new int[] {3,4,5},getString(R.string.fraise), 0.591, "fraise",0);
        Unserialized_vegetable.Vegetable framboise = new Unserialized_vegetable.Vegetable(new int[] {3,4,5,6,7,8,9,10},getString(R.string.framboise), 0.515, "framboise",0);
        Unserialized_vegetable.Vegetable kiwi = new Unserialized_vegetable.Vegetable(new int[] {11,1,2,3},getString(R.string.kiwi), 0.190, "kiwi",0);
        Unserialized_vegetable.Vegetable melon = new Unserialized_vegetable.Vegetable(new int[] {5,4},getString(R.string.melon), 0.313, "melon",0);
        Unserialized_vegetable.Vegetable orange = new Unserialized_vegetable.Vegetable(new int[] {0,1,10,11},getString(R.string.orange), 0.491, "orange",0);
        Unserialized_vegetable.Vegetable pasteque = new Unserialized_vegetable.Vegetable(new int[] {5,6,7},getString(R.string.pasteque), 0.36, "pasteque",0);
        Unserialized_vegetable.Vegetable raisin = new Unserialized_vegetable.Vegetable(new int[] {8,9},getString(R.string.raisin), 0.642, "raisin2",0);
        Unserialized_vegetable.Vegetable tomate = new Unserialized_vegetable.Vegetable(new int[] {4,5,6,7,8,9},getString(R.string.tomate), 0.343, "tomate",0);
        Unserialized_vegetable.Vegetable asperge = new Unserialized_vegetable.Vegetable(new int[] {3,4,5}, getString(R.string.asperge), 0.718, "asperge",0);
        Unserialized_vegetable.Vegetable avocat = new Unserialized_vegetable.Vegetable(new int[] {0,1,2,3,9,10,11}, getString(R.string.avocat), 1.2, "avocat",0);
        Unserialized_vegetable.Vegetable brocoli = new Unserialized_vegetable.Vegetable(new int[] {5,6,7,8,9,10}, getString(R.string.brocoli), 0.390, "brocoli",0);
        Unserialized_vegetable.Vegetable carotte = new Unserialized_vegetable.Vegetable(new int[] {7,8,9,10,11,0,1,2}, getString(R.string.carotte),0.258, "carotte",0);
        Unserialized_vegetable.Vegetable celeri = new Unserialized_vegetable.Vegetable(new int[] {6,7,8,9,10,11,0}, getString(R.string.celeri), 0.799, "celeri",0);
        Unserialized_vegetable.Vegetable champignon = new Unserialized_vegetable.Vegetable(new int[] {5,8,9,10,11,0,1,2,3,4},getString(R.string.champignon), 1.5, "chamignon",0);
        Unserialized_vegetable.Vegetable choux = new Unserialized_vegetable.Vegetable(new int[] {9,10,11,0,1,2,3}, getString(R.string.choux), 0.461, "choux",0);
        Unserialized_vegetable.Vegetable courgette = new Unserialized_vegetable.Vegetable(new int[] {4,5,6,7,8}, getString(R.string.courgette), 0.564, "courgette",0);
        Unserialized_vegetable.Vegetable mais = new Unserialized_vegetable.Vegetable(new int[] {6,7,8,9}, getString(R.string.mais), 0.701, "mais",0);
        Unserialized_vegetable.Vegetable navet = new Unserialized_vegetable.Vegetable(new int[] {9,10,11,0,1,2,3,4}, getString(R.string.navet), 0.728, "navet",0);
        Unserialized_vegetable.Vegetable oignon = new Unserialized_vegetable.Vegetable(new int[] {8,9,10,11,0,1,2,3}, getString(R.string.oignon), 0.485, "oignon",0);
        Unserialized_vegetable.Vegetable petit_pois = new Unserialized_vegetable.Vegetable(new int[] {4,5}, getString(R.string.petit_pois), 0.542, "petit_pois",0);
        Unserialized_vegetable.Vegetable poireau = new Unserialized_vegetable.Vegetable(new int[] {8,9,10,11,0,1,2,3}, getString(R.string.poireau), 0.29, "poireau",0);
        Unserialized_vegetable.Vegetable poivron = new Unserialized_vegetable.Vegetable(new int[] {5,6,7,8}, getString(R.string.poivron), 0.871, "poivron",0);
        Unserialized_vegetable.Vegetable pomme_de_terre = new Unserialized_vegetable.Vegetable(new int[] {8,9,10,11,0,1,2}, getString(R.string.pomme_de_terre), 0.585, "pomme_de_terre",0);
        Unserialized_vegetable.Vegetable radis = new Unserialized_vegetable.Vegetable(new int[] {2,3,4,5}, getString(R.string.radis),0.536, "radis",0);
        Unserialized_vegetable.Vegetable salade = new Unserialized_vegetable.Vegetable(new int[] {4,5,6,7,8}, getString(R.string.salade), 0.479, "salade",0);



        Unserialized_vegetable.Vegetable[] array_characters = new Unserialized_vegetable.Vegetable[]{ananas, abricot, banane, cerise, citron, clementine, fraise, framboise, kiwi, melon,
                orange, pasteque, raisin, tomate, aubergine, poivron, asperge, avocat,
                brocoli,carotte, celeri,champignon,choux,courgette,mais,navet,oignon,
                petit_pois,poireau,pomme_de_terre,radis,salade};


        List<Unserialized_vegetable.Vegetable> lstSource = new ArrayList<>();

        for (Unserialized_vegetable.Vegetable item : array_characters) {
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

    List<Unserialized_vegetable.Vegetable> lstSource;    // liste de legumes sur laquelle on itere pour creer les boutons
    Context mContext;

    public GridViewAdapter(List<Unserialized_vegetable.Vegetable> lstSource, Context mContext) {


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



        // On trouve le legume associé à la case
        final Unserialized_vegetable.Vegetable ProduitChoisi = lstSource.get(position);
        // NB : ProduitChoisi est un objet de la class Vegetable, il a donc toutes les méthodes associées getMois, getNom, etc
        String nom_produit = ProduitChoisi.getNom();


        /// creation de tous les boutons
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.casesdelagrid, null);


        /// Affichage de la photo des fruits

        final ImageView imageView = convertView.findViewById(R.id.imageView);

        int resourceId = mContext.getResources().getIdentifier(ProduitChoisi.getMnemonic(), "drawable", mContext.getPackageName());
        imageView.setImageResource(resourceId);


        /// Affichager du nom du fruit sur le bouton. Quand on clique sur le bouton ça affiche la fiche du fruit


        // SI VOUS VOULEZ CHANGER LES STYLES DE BOUTON IL FAUT LE FAIRE DANS LE LAYOUT casesdelagrid.xml !!!


        final Button button = convertView.findViewById(R.id.button);

        button.setText(lstSource.get(position).getNom());    // on met comme texte sur le bouton le nom du legume en position "position" dans la liste des legumes
        button.setBackgroundColor(Color.RED);
        button.setTextColor(Color.WHITE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent passageSuite = new Intent();
                passageSuite.setClass(mContext, FicheProduitActivity.class);

                passageSuite.putExtra("produitchoisi", ProduitChoisi);   // on passe dans le intent le vegetable associé au bouton

                passageSuite.putExtra("position du fruit", position);

                mContext.startActivity(passageSuite);         // on passe a l'activité FicheProduit

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent passageSuite = new Intent();
                passageSuite.setClass(mContext, FicheProduitActivity.class);

                passageSuite.putExtra("produitchoisi", ProduitChoisi);   // on passe dans le intent le vegetable associé au bouton

                passageSuite.putExtra("position du fruit", position);

                mContext.startActivity(passageSuite);         // on passe a l'activité FicheProduit

            }
        });



        return convertView;

    }
}
