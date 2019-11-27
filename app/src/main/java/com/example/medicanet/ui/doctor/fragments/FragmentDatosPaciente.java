package com.example.medicanet.ui.doctor.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicanet.R;

import clasesResponse.PacientesModel;

public class FragmentDatosPaciente extends Fragment {

    View view;
    TextView tvCodigo;
    String codigo;
    String nombre;
    Button btnVerHistorial;
    PacientesModel item;

    public FragmentDatosPaciente(PacientesModel item) {
        // Required empty public constructor
        this.item=item;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doc_datos_paciente, container, false);
        //CODIGO AGREGADO
        tvCodigo=view.findViewById(R.id.tvCodigo_fragment_doc_datos_paciente);
        btnVerHistorial=view.findViewById(R.id.btnVerHistorial_fragment_doc_datos_paciente);
        tvCodigo.setText(codigo);

        btnVerHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVerHistorial.setBackgroundResource(R.drawable.boton_redondeado);
                btnVerHistorial.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnVerHistorial.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnVerHistorial.setTextColor(Color.BLACK);

                        //Codigo para logica del boton
                        Toast.makeText(getContext(), "Ver historial", Toast.LENGTH_SHORT).show();
                    }
                }, 100);
            }
        });
        //FIN DE CODIGO
        return view;
    }

}
