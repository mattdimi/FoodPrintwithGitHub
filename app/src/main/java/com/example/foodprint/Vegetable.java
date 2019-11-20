package com.example.foodprint;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.TypeConverter;

import java.io.Serializable;

public class Vegetable implements Serializable {
    int[] mois;
    String nom;
    int empreinte_carbone;
    Drawable illustration;


    public Vegetable(int[] Mois, String Nom, int Empreinte_carbone, Drawable Illustration ){

        mois = Mois;
        nom = Nom;
        empreinte_carbone = Empreinte_carbone;
        illustration = Illustration;

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
    public int getEmpreinte_carbone() {
        return empreinte_carbone;
    }
    public void setEmpreinte_carbone(int empreinte_carbone) {
        this.empreinte_carbone = empreinte_carbone;
    }

    public Drawable getDrawable() {
        return illustration ;
    }
    public void setDrawable(Drawable illustration) {
        this.illustration = illustration;
    }






}
