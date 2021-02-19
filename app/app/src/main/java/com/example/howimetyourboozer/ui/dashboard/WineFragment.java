package com.example.howimetyourboozer.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.howimetyourboozer.R;

public class WineFragment extends Fragment {

    private WineViewModel wineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wineViewModel =
                new ViewModelProvider(this).get(WineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wine, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        wineViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}