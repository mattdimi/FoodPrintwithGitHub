package com.example.foodprint;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vegetables")
public class Unserialized_vegetable {

    @PrimaryKey@NonNull
    String nom;
@ColumnInfo
    double emp;
@ColumnInfo
    double quantity;


    public Unserialized_vegetable(String nom, int emp, double quantity){
        this.nom = nom;
        this.emp     = emp;
        this.quantity  = quantity;

    }
    public Unserialized_vegetable(Vegetable vegetable){
        this.nom = vegetable.getNom();
        this.emp     = vegetable.getEmpreinte_carbone();
        this.quantity  = vegetable.getQuantity();
    }


}
