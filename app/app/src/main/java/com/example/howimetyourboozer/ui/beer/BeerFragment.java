package com.example.howimetyourboozer.ui.beer;

import android.os.Bundle;
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

public class BeerFragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener{

    private BeerViewModel beerViewModel;
    MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    private Manager manager;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        beerViewModel = new ViewModelProvider(this).get(BeerViewModel.class);
        root = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        manager = Manager.getInstance();
        manager.setFragment(this);
        manager.getBeersFromAPI();
        // set up the RecyclerView
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(dividerItemDecoration);
        refreshRecycler();
        return root;
    }

    public void refreshRecycler(){
        adapter = new MyRecyclerViewAdapter(root.getContext(), manager.getBeers());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getView().getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}