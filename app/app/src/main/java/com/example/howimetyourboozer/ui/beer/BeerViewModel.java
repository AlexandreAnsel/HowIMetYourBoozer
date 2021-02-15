package com.example.howimetyourboozer.ui.beer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BeerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BeerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is beer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}