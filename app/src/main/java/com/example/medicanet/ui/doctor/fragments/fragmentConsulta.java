package com.example.medicanet.ui.doctor.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarCita;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarDetalleConsulta;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarMedicamentoConsulta;

public class fragmentConsulta extends Fragment {

    Button btnDetalles;
    Button btnAgregarCita;
    Button btnMedicamentos;
    Button btnTerminar;

    public fragmentConsulta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_consulta, container, false);

        //codigo agregado
        btnDetalles=view.findViewById(R.id.btnDetalles_fragment_doc_consulta);
        btnAgregarCita=view.findViewById(R.id.btnAgregarCita_fragment_doc_consulta);
        btnMedicamentos=view.findViewById(R.id.btnMedicamentos_fragment_doc_consulta);
        btnTerminar=view.findViewById(R.id.btnTerminar_fragment_doc_consulta);

        btnAgregarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAgregarCita.setBackgroundResource(R.drawable.boton_redondeado);
                btnAgregarCita.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnAgregarCita.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnAgregarCita.setTextColor(Color.BLACK);

                        //crear y mostrar un Dialog
                        DialogAgregarCita dialog = new DialogAgregarCita();
                        dialog.show(getFragmentManager(), "dialog_admin_ejemplo");
                    }
                },100);
            }
        });

        btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDetalles.setBackgroundResource(R.drawable.boton_redondeado);
                btnDetalles.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnDetalles.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnDetalles.setTextColor(Color.BLACK);

                        //Crear y mostrar un Dialog
                        DialogAgregarDetalleConsulta dialog = new DialogAgregarDetalleConsulta();
                        dialog.show(getFragmentManager(),"dialog_doc_agregar_detalle");
                    }
                },100);
            }
        });
        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMedicamentos.setBackgroundResource(R.drawable.boton_redondeado);
                btnMedicamentos.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnMedicamentos.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnMedicamentos.setTextColor(Color.BLACK);

                        //Crear y mostrar un Dialog
                        DialogAgregarMedicamentoConsulta dialog = new DialogAgregarMedicamentoConsulta();
                        dialog.show(getFragmentManager(),"dialog_doc_agregar_medicamento");
                    }
                },100);
            }
        });
        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTerminar.setBackgroundResource(R.drawable.boton_redondeado);
                btnTerminar.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnTerminar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnTerminar.setTextColor(Color.BLACK);

                        //Terminar
                        Toast.makeText(getContext(),"Terminando consulta",Toast.LENGTH_SHORT).show();
                    }
                },100);
            }
        });
        //fin codigo agregado

        return  view;
    }

}
