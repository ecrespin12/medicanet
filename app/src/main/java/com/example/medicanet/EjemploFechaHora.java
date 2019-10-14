package com.example.medicanet;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicanet.metodos.MetodosDavid;

public class EjemploFechaHora extends Fragment {
    EditText edtFecha,edtHora;
    Button btnFecha,btnHora;

    public EjemploFechaHora() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_ejm_fecha_hora, container, false);

        edtFecha=view.findViewById(R.id.ejemplo_edtFecha);
        edtHora=view.findViewById(R.id.ejemplo_edtHora);
        btnFecha=view.findViewById(R.id.ejemplo_btnAgregarFecha);
        btnHora=view.findViewById(R.id.ejemplo_btnAgregarHora);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MetodosDavid.fecha(getContext(),edtFecha);
            }
        });
        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MetodosDavid.hora(getContext(),edtHora);
                Toast.makeText(getContext(),"agregando hora",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"agregando hora",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
