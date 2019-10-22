package com.example.medicanet.ui.doctor.datosPaciente;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.consultas.Consulta;

public class DatosPaciente extends Fragment {

    Button btnHistorial;
    Button btnIniciarConsulta;

    public DatosPaciente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_datos_paciente, container, false);

        //codigo agregado

        btnHistorial=view.findViewById(R.id.btnHistorial_fragment_doc_datos_paciente);
        btnIniciarConsulta=view.findViewById(R.id.btnIniciarConsulta_fragment_doc_datos_paciente);

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnIniciarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Consulta detalle=new Consulta();

                // Crea el nuevo fragmento y la transacción.
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, detalle);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });
        //fin codigo agregado
        return view;
    }

}
