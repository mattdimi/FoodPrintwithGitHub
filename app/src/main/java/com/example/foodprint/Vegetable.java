package com.example.foodprint;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Vegetable implements Serializable {
    int[] mois;
    String nom;
    double emp;
    double quantity;
    String mnemonic;


    public Vegetable(int[] Mois, String Nom, float Empreinte_carbone,  String mnemonic, double quantity){



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
        return emp;
    }

    public void setEmpreinte_carbone(int empreinte_carbone) {
        this.emp = empreinte_carbone;
    }





    public String getMnemonic() {
        return this.getMnemonic();
    }

    public double getQuantity() {
        return this.quantity;
    }
    public void setQuantity(double quantity){
    this.quantity = quantity;
    }
}
