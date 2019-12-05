package com.example.medicanet.ui.custom_preferencias;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicanet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomPreferencesFragment extends PreferenceFragment {


    public CustomPreferencesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.custom_preferences);
    }



}
