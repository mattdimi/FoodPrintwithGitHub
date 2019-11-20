package com.example.foodprint;


import androidx.room.Entity;

@Entity
public class Unserialized_vegetable {
    int[] mois;
    String nom;
    int empreinte_carbone;
    int quantity;

    public Unserialized_vegetable(Vegetable vegetable){

    }


}
