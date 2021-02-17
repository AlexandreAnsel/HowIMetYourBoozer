package com.example.howimetyourboozer.controllers;

import android.util.Log;

import com.example.howimetyourboozer.MainActivity;
import com.example.howimetyourboozer.database.model.Drink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static Manager instance;

    private APIManager apiManager;

    private MainActivity mainActivity;
    public void setActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private List<Drink> beers;
    private int numberPageBeers = 1;

    public static Manager getInstance() {
        if(instance == null)
            instance = new Manager();
        return instance;
    }

    private Manager(){
        this.apiManager = new APIManager(this);
        beers = new ArrayList<>();
    }

    public void incrementNumberPageBeers(){
        numberPageBeers++;
        if(beers.size() <= 50*numberPageBeers) getBeers();
    }

    public void getBeers() {
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
        if(mainActivity != null){
            Log.i("Beers", "NB OF BEERS : " + this.beers.size());
        }
    }
}
