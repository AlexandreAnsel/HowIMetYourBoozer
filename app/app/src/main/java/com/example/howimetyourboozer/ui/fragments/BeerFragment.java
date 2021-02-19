package com.example.howimetyourboozer.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.howimetyourboozer.R;
import com.example.howimetyourboozer.controllers.Manager;
import com.example.howimetyourboozer.database.model.Drink;
import com.example.howimetyourboozer.ui.recyclerview.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class BeerFragment extends RecyclerViewFragment{

    @Override
    public void getDataForRecycler(){
        manager.getBeersFromAPI();
    }

    @Override
    public void refreshRecycler(){
        Log.i("Beers", "BEERS : " + manager.getBeers().size());
        adapter.setDataSet(manager.getBeers());
    }
}