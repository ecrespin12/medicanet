package com.example.medicanet.ui.doctor.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicanet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPacientes extends Fragment {


    public fragmentPacientes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lyout for this fragment
        return inflater.inflate(R.layout.fragment_doc_pacientes, container, false);
    }

}
