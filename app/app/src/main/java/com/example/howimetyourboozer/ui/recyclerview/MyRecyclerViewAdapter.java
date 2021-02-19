package com.example.howimetyourboozer.ui.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.howimetyourboozer.MainActivity;
import com.example.howimetyourboozer.R;
import com.example.howimetyourboozer.activities.DetailsActivity;
import com.example.howimetyourboozer.controllers.Manager;
import com.example.howimetyourboozer.database.model.Drink;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Drink> mData;
    private LayoutInflater mInflater;
    private Context myContext;

    private Manager manager;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<Drink> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.myContext = context;
        this.manager = Manager.getInstance();
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drink drink = mData.get(position);
        View.OnClickListener openDetailsActivity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailsActivity(drink);
            }
        };

        holder.textViewDrinkName.setText(drink.getName());
        holder.textViewDrinkName.setOnClickListener(openDetailsActivity);
        holder.textViewDrinkType.setText(drink.getType());
        holder.textViewDrinkType.setOnClickListener(openDetailsActivity);

        holder.imageView.setOnClickListener(openDetailsActivity);

        if(manager.getFavorites().contains(drink)){
            holder.imageButton.setImageResource(R.drawable.ic_heart_fill);
        } else {
            holder.imageButton.setImageResource(R.drawable.ic_heart_empty);
        }

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (manager.getFavorites().contains(drink))
                            manager.removeDrinkFromFavorite(drink);
                        else
                            manager.addDrinkToFavorite(drink);
                    }
                });
            }
        });
        //Loading image using Glide framework
        Glide.with(myContext).load(drink.getIcon()).into(holder.imageView);
    }

    private void openDetailsActivity(Drink drink){
        Log.i("Beers", "Click on : " + drink.getName());
        Intent intent = new Intent(myContext, DetailsActivity.class);
        intent.putExtra("drink", drink);
        myContext.startActivity(intent);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setDataSet(List<Drink> newList){
        mData.clear();
        mData.addAll(newList);
        this.notifyDataSetChanged();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDrinkName;
        TextView textViewDrinkType;
        ImageView imageView;
        ImageButton imageButton;

        ViewHolder(View itemView) {
            super(itemView);
            textViewDrinkName = itemView.findViewById(R.id.textViewDrinkName);
            textViewDrinkType = itemView.findViewById(R.id.textViewDrinkType);
            imageView = itemView.findViewById(R.id.imageView);
            imageButton = itemView.findViewById(R.id.imageButton);

        }
    }

    // convenience method for getting data at click position
    public Drink getItem(int id) {
        return mData.get(id);
    }
}