package com.example.medicanet.ui.doctor.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicanet.R;

public class FragmentProgramarConsulta extends Fragment {

    View view;

    public FragmentProgramarConsulta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doc_programar_consulta, container, false);



        return view;
    }

}
