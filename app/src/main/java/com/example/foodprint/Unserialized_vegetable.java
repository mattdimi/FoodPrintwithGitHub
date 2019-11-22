package com.example.foodprint;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "vegetables")
public class Unserialized_vegetable {

    @PrimaryKey@NonNull
    String nom;
@ColumnInfo
    double emp;
@ColumnInfo
    double quantity;


    public Unserialized_vegetable(String nom, double emp, double quantity){
        this.nom = nom;
        this.emp     = emp;
        this.quantity  = quantity;

    }
    public Unserialized_vegetable(Vegetable vegetable){
        this.nom = vegetable.getNom();
        this.emp     = vegetable.getEmpreinte_carbone();
        this.quantity  = vegetable.getQuantité();
    }


    public static class Vegetable implements Serializable {
        int[] mois;
        String nom;
        double empreinte_carbone;
        String mnemonic;
        double quantité;


        public Vegetable(int[] Mois, String Nom, double Empreinte_carbone, String fichier_image, double Quantité ){

            mois = Mois;
            nom = Nom;
            empreinte_carbone = Empreinte_carbone;
            mnemonic = fichier_image;
            quantité = Quantité;


        }


        public int[] getMois() {
            return mois;
        }

        public void setMois(int[] mois) {
            this.mois = mois;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public double getEmpreinte_carbone() {
            return empreinte_carbone;
        }

        public void setEmpreinte_carbone(double empreinte_carbone) {
            this.empreinte_carbone = empreinte_carbone;
        }

        public String getMnemonic() {
            return mnemonic ;
        }

        public void setMnemonic(String mnemonic) {
            this.mnemonic = mnemonic;
        }

        public double getQuantité() {
            return quantité;
        }

        public void setQuantité(double quantité) {
            this.quantité = quantité;
        }
    }
}
