package com.example.medicanet.ui.doctor.dialogs;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicanet.R;

public class DialogAgregarDetalleConsulta extends DialogFragment {

    Spinner spTipo;
    EditText edtDescripcion;
    Button btnGuardar;
    ImageView btnCerrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.dialog_doc_agregar_detalle_consulta, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        spTipo=view.findViewById(R.id.spTipo_doc_modal_agregar_detalle_consulta);
        edtDescripcion=view.findViewById(R.id.edtDescripcion_doc_modal_agregar_detalle_consulta);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_detalle_consulta);
        btnCerrar=view.findViewById(R.id.btnCerrar_doc_modal_agregar_detalle);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_LONG).show();
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return  view;
    }

}
