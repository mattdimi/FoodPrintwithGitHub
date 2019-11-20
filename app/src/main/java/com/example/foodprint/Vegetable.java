package com.example.foodprint;

import java.io.Serializable;

public class Vegetable implements Serializable {
    int[] mois;
    String nom;
    int empreinte_carbone;
    String mnemonic;


    public Vegetable(int[] Mois, String Nom, int Empreinte_carbone, String fichier_image ){

        mois = Mois;
        nom = Nom;
        empreinte_carbone = Empreinte_carbone;
        mnemonic = fichier_image;

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

    public String getMnemonic() {
        return mnemonic ;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }


}
