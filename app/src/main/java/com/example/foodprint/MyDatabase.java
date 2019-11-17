package com.example.foodprint;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Day.class},version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract MyDao myDao();
}
