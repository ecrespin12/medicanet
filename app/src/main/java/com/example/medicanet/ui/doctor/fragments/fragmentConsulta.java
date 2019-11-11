package com.example.medicanet.ui.doctor.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
                //dialog/modal a invocar
                DialogAgregarCita dialog = new DialogAgregarCita();
                dialog.show(getFragmentManager(), "dialog_admin_ejemplo");
            }
        });
        btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAgregarDetalleConsulta dialog = new DialogAgregarDetalleConsulta();
                dialog.show(getFragmentManager(),"dialog_doc_agregar_detalle");
            }
        });
        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAgregarMedicamentoConsulta dialog = new DialogAgregarMedicamentoConsulta();
                dialog.show(getFragmentManager(),"dialog_doc_agregar_medicamento");
            }
        });
        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //fin codigo agregado

        return  view;
    }

}
