package com.example.howimetyourboozer.controllers;

import android.util.Log;

import com.example.howimetyourboozer.database.model.Drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONConverter {

    public static List<Drink> JSONToListDrinks(JSONObject jsonObject) throws JSONException {
        List<Drink> drinks = new ArrayList<>();

        JSONArray data;
        try {
            data = jsonObject.getJSONArray("data");
        } catch (JSONException e){
            return drinks;
        }

        for(int i = 0; i < data.length(); i++) {
            Log.i("Beers", "DRINK : " + i);
            drinks.add(JSONToDrink(data.getJSONObject(i)));
        }

        return drinks;
    }

    public static Drink JSONToDrink(JSONObject jsonObject) throws JSONException {

        String iconLink;
        try {
            JSONObject labels = jsonObject.getJSONObject("labels");
            try {
                iconLink = labels.getString("medium");
            } catch (JSONException e) {
                iconLink = labels.getString("icon");
            }
        } catch (JSONException e) {
            iconLink = "https://cdn4.iconfinder.com/data/icons/proglyphs-food/512/Beer-512.png";
        }

        String type;
        String description;
        try {
            JSONObject style = jsonObject.getJSONObject("style");
            type = style.getString("name");
            description = style.getString("description");
        } catch (JSONException e) {
            description = "Great drink !";
            type = "Drink";
        }

        try {
            description = jsonObject.getString("description");
        } catch (JSONException e) {
            Log.e("Drink", "No description");
        }

        Float abv;
        try {
            abv = Float.parseFloat(jsonObject.getString("abv"));
        } catch (JSONException e) {
            abv = 0.0f;
        }

        return new Drink(jsonObject.getString("id"), jsonObject.getString("nameDisplay"),
                type, abv,
                description, iconLink, 0);
    }
}
