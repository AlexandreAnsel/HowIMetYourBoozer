package com.example.howimetyourboozer.ui.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.howimetyourboozer.R;
import com.example.howimetyourboozer.database.model.Drink;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Drink> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context myContext;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<Drink> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.myContext = context;
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
        holder.textViewDrinkName.setText(drink.getName());
        holder.textViewDrinkType.setText(drink.getType());
        //Loading image using Glide framework
        Glide.with(myContext).load("https://cdn4.iconfinder.com/data/icons/proglyphs-food/512/Beer-512.png").into(holder.imageView);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewDrinkName;
        TextView textViewDrinkType;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textViewDrinkName = itemView.findViewById(R.id.textViewDrinkName);
            textViewDrinkType = itemView.findViewById(R.id.textViewDrinkType);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getName();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}