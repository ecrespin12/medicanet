package com.example.medicanet.ui.farmacia.HistorialEntregas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicanet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialEntrega extends Fragment {


    public HistorialEntrega() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_far_historial_entrega, container, false);
    }

}
