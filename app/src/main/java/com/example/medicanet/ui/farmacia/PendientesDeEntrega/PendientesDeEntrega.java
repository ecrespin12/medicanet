package com.example.medicanet.ui.farmacia.PendientesDeEntrega;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicanet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendientesDeEntrega extends Fragment {


    public PendientesDeEntrega() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_far_entrega_medicamentos, container, false);
    }

}