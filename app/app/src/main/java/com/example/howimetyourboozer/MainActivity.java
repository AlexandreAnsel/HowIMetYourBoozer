package com.example.howimetyourboozer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.howimetyourboozer.controllers.Manager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private Manager manager;

    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.custom_tool_bar_edit);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        searchText = actionBar.getCustomView().findViewById(R.id.searchText);
        Button searchButton = actionBar.getCustomView().findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.clearBeers();
                if(searchText.getEditableText().toString().trim().equals("")){
                    manager.getBeersFromAPI();
                } else {
                    manager.search(searchText.getEditableText().toString());
                }
            }
        });

        manager = Manager.getInstance();
        manager.setMainActivity(this);
        manager.updateFavorites();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_beer, R.id.navigation_favorites)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public String getSearchText(){
        return searchText.getEditableText().toString();
    }

}