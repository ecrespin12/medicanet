package com.example.medicanet.ui.paciente.datosMedicos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.medicanet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatosMedicos extends Fragment {


    ListView lvDatosMedicos;
    public DatosMedicos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pac_datos_medicos, container, false);
        lvDatosMedicos = view.findViewById(R.id.lvDatosMedicos_pac);
        return view;

    }

}
