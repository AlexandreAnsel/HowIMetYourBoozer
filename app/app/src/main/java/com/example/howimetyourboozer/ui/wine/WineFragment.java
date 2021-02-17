package com.example.howimetyourboozer.ui.wine;

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

import java.util.ArrayList;

public class WineFragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener{

    private WineViewModel wineViewModel;
    MyRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wineViewModel = new ViewModelProvider(this).get(WineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        // data to populate the RecyclerView with
        ArrayList<Drink> drinks = new ArrayList<>();

        for (int i = 1; i < 21 ; i++) {
            drinks.add(new Drink(i,"Gewurztraminer ","Vin Blanc", 11.0f,"Un vin alsacien d'exception","https://www.alsace-vintage.com/media/catalog/product/cache/1/image/650x650/9df78eab33525d08d6e5fb8d27136e95/g/e/gewurzrtaminer_-_jean-marc_bernhard_1.png",10));
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