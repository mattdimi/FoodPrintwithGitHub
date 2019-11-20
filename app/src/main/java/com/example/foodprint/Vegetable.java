package com.example.foodprint;

import java.io.Serializable;

public class Vegetable implements Serializable {
    int[] mois;
    String nom;
    int empreinte_carbone;
    //ImageView imageView;


    public Vegetable(int[] Mois, String Nom, int Empreinte_carbone /*, ImageView ImageView */){

        mois = Mois;
        nom = Nom;
        empreinte_carbone = Empreinte_carbone;
        //imageView = ImageView;

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

    /*
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    */





}
