package com.example.howimetyourboozer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.howimetyourboozer.R;
import com.example.howimetyourboozer.controllers.Manager;
import com.example.howimetyourboozer.ui.recyclerview.MyRecyclerViewAdapter;

public class FavoritesFragment extends RecyclerViewFragment{

    @Override
    public void getDataForRecycler(){
        manager.asyncUpdateFavorites();
    }

    @Override
    public void refreshRecycler(){
        adapter.setDataSet(manager.getFavorites());
    }
}