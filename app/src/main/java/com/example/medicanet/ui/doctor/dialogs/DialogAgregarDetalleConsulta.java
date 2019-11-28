package com.example.medicanet.ui.doctor.dialogs;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.os.Handler;
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

    EditText edtNombre;
    EditText edtDescripcion;
    Button btnGuardar;
    ImageView btnCerrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.dialog_doc_agregar_detalle_consulta, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        edtNombre=view.findViewById(R.id.edtNombre_doc_modal_agregar_detalle_consulta);
        edtDescripcion=view.findViewById(R.id.edtDescripcion_doc_modal_agregar_detalle_consulta);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_detalle_consulta);
        btnCerrar=view.findViewById(R.id.btnCerrar_doc_modal_agregar_detalle);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGuardar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                btnGuardar.setTextColor(Color.BLACK);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnGuardar.setBackgroundResource(R.drawable.boton_style_modal);
                        btnGuardar.setTextColor(Color.WHITE);

                        //logica
                        Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_LONG).show();
                    }
                },100);
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCerrar.setImageResource(R.drawable.eliminar2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnCerrar.setImageResource(R.drawable.eliminar1);

                        //logica
                        dismiss();
                    }
                },100);

            }
        });

        return  view;
    }

}
