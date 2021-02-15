package com.example.howimetyourboozer.ui.wine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WineViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is wine fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}