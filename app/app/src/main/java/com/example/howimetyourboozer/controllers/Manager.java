package com.example.howimetyourboozer.controllers;

import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.howimetyourboozer.MainActivity;
import com.example.howimetyourboozer.database.model.AppDatabase;
import com.example.howimetyourboozer.database.model.Drink;
import com.example.howimetyourboozer.database.model.DrinkDAO;
import com.example.howimetyourboozer.ui.fragments.BeerFragment;
import com.example.howimetyourboozer.ui.fragments.RecyclerViewFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static Manager instance;

    private APIManager apiManager;
    private AppDatabase db;
    private void setDb() {
        if(mainActivity == null) return;
        db = Room.databaseBuilder(mainActivity.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        setDrinkDao();
    }

    private DrinkDAO drinkDAO;
    private void setDrinkDao(){
        if(db == null) return;
        drinkDAO = db.drinkDAO();
    }

    private MainActivity mainActivity;
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        setDb();
    }

    private RecyclerViewFragment recyclerViewFragment;
    public void setFragment(RecyclerViewFragment recyclerViewFragment) {
        this.recyclerViewFragment = recyclerViewFragment;
    }

    private List<Drink> beers;
    public List<Drink> getBeers() {
        return beers;
    }
    private int numberPageBeers = 1;

    private List<Drink> favorites;
    public List<Drink> getFavorites() {
        return favorites;
    }
    public void updateFavorites() {
        if(drinkDAO == null) return;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                favorites = drinkDAO.getAll();
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerViewFragment.refreshRecycler();
                    }
                });
            }
        });
    }

    public static Manager getInstance() {
        if(instance == null)
            instance = new Manager();
        return instance;
    }

    private Manager(){
        this.apiManager = new APIManager(this);
        beers = new ArrayList<>();
        favorites = new ArrayList<>();
    }

    public void incrementNumberPageBeers(){
        numberPageBeers++;
        getBeersFromAPI();
    }

    public void getBeersFromAPI() {
        if(beers.size() >= 50*numberPageBeers) return;

        try {
            apiManager.getBeersByPage(numberPageBeers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchBeers(String name) {
        try {
            apiManager.searchBeersByName(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBeers(List<Drink> listBeers){
        this.beers.addAll(listBeers);
        if(recyclerViewFragment != null){
            Log.i("Beers", "NB OF BEERS : " + this.beers.size());
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerViewFragment.refreshRecycler();
                }
            });
        }
    }

    public void addDrinkToFavorite(Drink d){
        if(drinkDAO == null) return;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                drinkDAO.insert(d);
                updateFavorites();
            }
        });

    }

    public void removeDrinkFromFavorite(Drink d) {
        if(drinkDAO == null) return;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                drinkDAO.delete(d);
                updateFavorites();
            }
        });
    }
}
