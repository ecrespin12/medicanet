package com.example.medicanet.ui.paciente.historialMedico;


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
public class HistorialMedico extends Fragment {


    ListView lvHistorialMedico;
    public HistorialMedico() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pac_historial_medico, container, false);
        lvHistorialMedico = view.findViewById(R.id.lvHistorialMedico_pac);
        // Inflate the layout for this fragment
        return view;
    }

}
