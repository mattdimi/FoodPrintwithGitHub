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
    public void newDay(Day day);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void newVegetable(Vegetable vegetable);

    @Query("Select * from days")
    public List<Day> getAllDays();

    @Query("Select sum(steps) from days")
    public int getTotalStepCount();

    @Query("Select steps from days where date = :date")
    public int getStepsByDate(String date);

    @Delete
    public void DeleteDayDyDate(Day day);
    @Update
    public void updateDay(Day day);



}
