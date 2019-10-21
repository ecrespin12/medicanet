package com.example.medicanet.ui.paciente.medicamentos;


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
public class Medicamentos extends Fragment {


    public Medicamentos() {
        // Required empty public constructor
    }

    ListView lvMedicamentosPendientes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pac_medicamentos, container, false);
        lvMedicamentosPendientes = view.findViewById(R.id.lv_pendientes_entregas_pac);
        // Inflate the layout for this fragment
        return view;
    }
}
