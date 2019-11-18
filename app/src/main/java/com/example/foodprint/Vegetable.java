package com.example.foodprint;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;

public class Vegetable extends AppCompatButton{
    int[] mois;
    String nom;
    int empreinte_carbone;
    ImageView imageView;


    public Vegetable(Context context) {
        super(context);


    }
}
