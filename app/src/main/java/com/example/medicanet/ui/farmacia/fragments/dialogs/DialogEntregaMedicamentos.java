package com.example.medicanet.ui.farmacia.fragments.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.medicanet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DialogEntregaMedicamentos extends DialogFragment {

    public FloatingActionButton btnCancelar, btnGuardar;
    public Spinner SpMedicamento;
    public TextView txtCantidad;
    public MultiAutoCompleteTextView txtIndicaciones;


    public DialogEntregaMedicamentos() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_far_pendientes_entrega_modal, container, false);

        btnCancelar = v.findViewById(R.id.btnCancelar_far_modal_pendiente_entrega);
        btnGuardar = v.findViewById(R.id.btnGuardar_far_modal_pendiente_entrega);
        SpMedicamento = v.findViewById(R.id.spMedicamentos_far_modal_pendiente_entrega);
        txtCantidad = v.findViewById(R.id.edtCantidad_far_modal_pendiente_entrega);
        txtIndicaciones = v.findViewById(R.id.txtIndicaciones_far_modal_pendiente_entrega);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }
}
