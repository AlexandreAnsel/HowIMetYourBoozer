package com.example.howimetyourboozer.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.howimetyourboozer.R;
import com.example.howimetyourboozer.controllers.Manager;
import com.example.howimetyourboozer.ui.recyclerview.MyRecyclerViewAdapter;

import java.util.ArrayList;

public abstract class RecyclerViewFragment extends Fragment {

    protected MyRecyclerViewAdapter adapter;
    protected RecyclerView recyclerView;

    protected Manager manager;
    protected View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        manager = Manager.getInstance();
        manager.setFragment(this);
        this.getDataForRecycler();
        // set up the RecyclerView
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    manager.incrementNumberPageBeers();
                }
            }
        });
        adapter = new MyRecyclerViewAdapter(root.getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        refreshRecycler();
        return root;
    }

    protected abstract void getDataForRecycler();

    public abstract void refreshRecycler();
}
