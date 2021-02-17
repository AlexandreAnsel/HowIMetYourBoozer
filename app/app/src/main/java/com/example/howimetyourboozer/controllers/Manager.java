package com.example.howimetyourboozer.controllers;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.howimetyourboozer.MainActivity;
import com.example.howimetyourboozer.database.model.Drink;
import com.example.howimetyourboozer.ui.beer.BeerFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static Manager instance;

    private APIManager apiManager;

    private MainActivity mainActivity;
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private BeerFragment fragment;
    public void setFragment(BeerFragment fragment) {
        this.fragment = fragment;
    }

    private List<Drink> beers;
    public List<Drink> getBeers() {
        return beers;
    }

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
        if(fragment != null){
            Log.i("Beers", "NB OF BEERS : " + this.beers.size());
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fragment.refreshRecycler();
                }
            });
        }
    }
}
