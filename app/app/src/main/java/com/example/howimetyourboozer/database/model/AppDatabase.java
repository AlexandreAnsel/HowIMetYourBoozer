package com.example.howimetyourboozer.database.model;

import androidx.room.*;

@Database(entities = {Drink.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DrinkDAO drinkDAO();
}