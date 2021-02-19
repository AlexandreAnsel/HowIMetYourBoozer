package com.example.howimetyourboozer.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIManager {

    private OkHttpClient client = new OkHttpClient();

    private final String apiGetBeersBaseUrl = "http://api.brewerydb.com/v2/beers?key=2a1ef87167c49ca69c4a0b7c781fac08&p=";
    private final String apiSearchBeersBaseUrl = "http://api.brewerydb.com/v2/search?key=2a1ef87167c49ca69c4a0b7c781fac08&type=beer&q=";

    private Manager manager;

    public APIManager(Manager manager) {
        this.manager = manager;
    }

    public void getBeersByPage(int page) throws IOException {
        Request request = new Request.Builder()
                .url(apiGetBeersBaseUrl + page)
                .build();

        client.newCall(request)
            .enqueue(new Callback() {
                @Override
                public void onFailure(final Call call, IOException e) {
                    // Error

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    String res = response.body().string();

                    try {
                        JSONObject resJson = new JSONObject(res);
                        manager.setBeers(JSONConverter.JSONToListDrinks(resJson));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Do something with the response
                }
            });
    }

    public void searchBeersByName(String name, int page) throws IOException {
        Request request = new Request.Builder()
                .url(apiSearchBeersBaseUrl + name + "&p=" + page)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        String res = response.body().string();

                        try {
                            JSONObject resJson = new JSONObject(res);
                            manager.setBeers(JSONConverter.JSONToListDrinks(resJson));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Do something with the response
                    }
                });
    }
}
