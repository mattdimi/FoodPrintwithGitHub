package com.example.foodprint;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void newDay(Day day);

    @Query("Select * from days")
    List<Day> getAllDays();

    @Query("Select sum(steps) from days")
    int getTotalStepCount();

    @Query("Select steps from days where date = :date")
    int getStepsByDate(String date);

    @Delete
    void DeleteDayDyDate(Day day);
    @Update
    void updateDay(Day day);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void newVegetable(Unserialized_vegetable vegetable);

    @Query("Select * from vegetables")
    List<Unserialized_vegetable> getAllVegetables();

    @Delete
    void DeleteVegetable(Unserialized_vegetable vegetable);

    @Query("DELETE FROM vegetables")
    void DeleteAll();






}
