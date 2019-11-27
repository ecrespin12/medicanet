package com.example.medicanet.ui.farmacia.fragments.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.medicanet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;

public class DialogEntregaMedicamentos extends DialogFragment {

    public FloatingActionButton btnCancelar, btnGuardar;
    EditText txtMedicamento,txtCantidad;
    public MultiAutoCompleteTextView txtIndicaciones;
    private IServices servicio;
    RetrofitClientInstance ret = new RetrofitClientInstance();


    public DialogEntregaMedicamentos() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_far_pendientes_entrega_modal, container, false);

        servicio = (IServices) ret.createService(IServices.class, v.getContext().getResources().getString(R.string.token));

        btnCancelar = v.findViewById(R.id.btnCancelar_far_modal_pendiente_entrega);
        btnGuardar = v.findViewById(R.id.btnGuardar_far_modal_pendiente_entrega);
        txtMedicamento = v.findViewById(R.id.edtMedicamento_far_modal_pendiente_entrega);
        txtCantidad = v.findViewById(R.id.edtCantidad_far_modal_pendiente_entrega);
        txtIndicaciones = v.findViewById(R.id.txtIndicaciones_far_modal_pendiente_entrega);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);

        Bundle bd = getArguments();
        txtMedicamento.setEnabled(false);
        txtIndicaciones.setEnabled(false);
        txtCantidad.setText(""+bd.getInt("cantidad"));
        txtMedicamento.setText(""+bd.getString("nombre"));
        txtIndicaciones.setText(""+bd.getString("indicaciones"));


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }
}
