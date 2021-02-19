package com.example.howimetyourboozer.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.howimetyourboozer.R;
import com.example.howimetyourboozer.controllers.Manager;
import com.example.howimetyourboozer.database.model.Drink;

public class DetailsActivity extends AppCompatActivity {

    private Drink drink;

    private ImageView icon;
    private TextView nameDrink;
    private ImageButton addFavorites;
    private TextView type;
    private TextView degree;
    private TextView description;

    private LinearLayout markLayout;
    private ImageView[] marks;

    private Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        manager = Manager.getInstance();

        Intent intent = getIntent();

        if(intent != null) {
            drink = (Drink) intent.getSerializableExtra("drink");
        } else {
            drink = new Drink("abc", "Default beer", "Red beer", 9.5f, "What a beer !", "https://cdn4.iconfinder.com/data/icons/proglyphs-food/512/Beer-512.png", 4);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.custom_tool_bar_details);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        nameDrink = actionBar.getCustomView().findViewById(R.id.nameDrink);
        addFavorites = actionBar.getCustomView().findViewById(R.id.addFavorites);
        icon = findViewById(R.id.icon);
        type = findViewById(R.id.type);
        degree = findViewById(R.id.degree);
        description = findViewById(R.id.description);

        markLayout = findViewById(R.id.markLayout);
        marks = new ImageView[5];
        int [] markId = {R.id.markOne, R.id.markTwo, R.id.markThree, R.id.markFour, R.id.markFive};

        for(int i = 0; i < 5; i++){
            marks[i] = findViewById(markId[i]);
        }

        nameDrink.setText(drink.getName());
        refreshFavorite();

        addFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        if(manager.getFavorites().contains(drink))
                            manager.removeDrinkFromFavorite(drink);
                        else
                            manager.addDrinkToFavorite(drink);
                        
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refreshFavorite();
                                refreshMarkLayout();
                            }
                        });
                    }
                });
            }
        });
        //Loading image using Glide framework
        Glide.with(this).load(drink.getIcon()).into(icon);
        type.setText(drink.getType());
        degree.setText(drink.getDegree() + "°");
        description.setText(drink.getDescription());

        refreshMarkLayout();
        updateDisplayMark();
        for(int i = 0; i < marks.length; i++) {
            final int index = i;
            marks[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drink.setMark(index+1);
                    manager.saveDrinkMark(drink);
                    updateDisplayMark();
                }
            });
        }
    }

    private void refreshMarkLayout() {
        if(manager.getFavorites().contains(drink)){
            markLayout.setVisibility(View.VISIBLE);
        } else {
            markLayout.setVisibility(View.GONE);
        }
    }

    private void updateDisplayMark() {
        int mark = drink.getMark();

        for(int i = 0; i < marks.length; i++){
            marks[i].setImageResource(R.drawable.ic_star_empty);
            if(mark > i)
                marks[i].setImageResource(R.drawable.ic_star_fill);
        }

    }

    private void refreshFavorite() {
        if(manager.getFavorites().contains(drink)){
            addFavorites.setImageResource(R.drawable.ic_heart_fill);
        } else {
            addFavorites.setImageResource(R.drawable.ic_heart_empty);
        }
    }
}