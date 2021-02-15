package com.example.howimetyourboozer.controllers;

public class Manager {

    private static Manager instance;

    private APIManager apiManager;

    public static Manager getInstance() {
        if(instance == null)
            instance = new Manager();
        return instance;
    }

    public Manager(){
        this.apiManager = new APIManager();
    }
}
