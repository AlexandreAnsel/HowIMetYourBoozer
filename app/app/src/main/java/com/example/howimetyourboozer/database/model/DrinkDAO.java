package com.example.howimetyourboozer.database.model;


import androidx.room.*;

import java.util.List;

@Dao
public interface DrinkDAO {
    @Query("SELECT * FROM drink")
    List<Drink> getAll();

    @Query("SELECT * FROM drink WHERE name LIKE '%' || :name || '%'")
    List<Drink> searchFavorites(String name);

    @Query("SELECT * FROM drink WHERE id=:id")
    Drink get(String id);

    @Update
    void update(Drink drink);

    @Insert
    void insert(Drink drink);

    @Delete
    void delete(Drink drink);
}
