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

public class DialogAgregarMedicamentoConsulta extends DialogFragment {

    ImageView imgCerrar;
    Spinner spTipoMedicamento;
    EditText edtDescripcion,edtCantidadPastillas,edtCantidadHoras;
    Button btnGuardar;

    public DialogAgregarMedicamentoConsulta() {
        this.setCancelable(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doc_agregar_medicamento_consulta, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        imgCerrar = view.findViewById(R.id.imgCerrar_doc_modal_agregar_medicamento);
        btnGuardar = view.findViewById(R.id.btnGuardar_doc_modal_agregar_medicamento);
        spTipoMedicamento = view.findViewById(R.id.spTipo_doc_modal_agregar_medicamento);
        edtDescripcion = view.findViewById(R.id.edtDescripcion_doc_modal_agregar_medicamento);
        edtCantidadPastillas = view.findViewById(R.id.edtCantidadPastillas_doc_modal_agregar_medicamento);
        edtCantidadHoras = view.findViewById(R.id.edtCantidadHoras_doc_modal_agregar_medicamento);

        imgCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Cerrando",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Guardando...",Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }

}