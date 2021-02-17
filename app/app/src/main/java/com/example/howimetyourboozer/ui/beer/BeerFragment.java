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
import com.example.howimetyourboozer.database.model.Drink;
import com.example.howimetyourboozer.ui.recyclerview.MyRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BeerFragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener{

    private BeerViewModel beerViewModel;
    MyRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        beerViewModel = new ViewModelProvider(this).get(BeerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        // data to populate the RecyclerView with
        ArrayList<Drink> drinks = new ArrayList<>();

        for (int i = 1; i < 21 ; i++) {
            drinks.add(new Drink(i,"Triple Karmeliet","TRIPLE", 8.5f,"La bière des Hommes !",null,10));
        }
        // set up the RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),1);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new MyRecyclerViewAdapter(root.getContext(), drinks);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


      /*  beerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getView().getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}