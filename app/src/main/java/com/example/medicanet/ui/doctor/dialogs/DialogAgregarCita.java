package com.example.medicanet.ui.doctor.dialogs;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.os.Handler;
import android.util.Half;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorSpinner;
import com.example.medicanet.metodos.Metodos;

import java.util.ArrayList;

import clasesResponse.ConsultaModel;

public class DialogAgregarCita extends DialogFragment {

    Spinner             spTipoCita;
    AdaptadorSpinner    adaptadorSpinner;

    EditText            edtDescripcion;
    EditText            edtFecha;
    EditText            edtHora;

    ImageView           btnCerrar;

    Button              btnGuardar;

    ImageButton         btnFecha;
    ImageButton         btnHora;

    TypedArray          imagenes;
    String []           nombres;
    String []           descripciones;

    ConsultaModel item;

    public DialogAgregarCita(ConsultaModel item) {
        this.item=item;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doc_agregar_cita, container, false);

        //CODIGO AGREGADO
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        //Enlazando vistas
        spTipoCita = view.findViewById(R.id.spTipo_dialog_doc_agregar_cita);
        edtDescripcion=view.findViewById(R.id.edtDescripcion_dialog_doc_agregar_cita);
        edtFecha=view.findViewById(R.id.edtFecha_dialog_doc_agregar_cita);
        edtHora=view.findViewById(R.id.edtHora_dialog_doc_agregar_cita);
        btnCerrar=view.findViewById(R.id.imgCerrar_dialog_doc_agregar_cita);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_cita);
        btnFecha=view.findViewById(R.id.btnFecha_dialog_doc_agregar_cita);
        btnHora=view.findViewById(R.id.btnHora_dialog_doc_agregar_cita);

        imagenes=getResources().obtainTypedArray(R.array.img_item_list_ejemplo);
        nombres=getResources().getStringArray(R.array.campo1_item_list_ejemplo);
        descripciones=getResources().getStringArray(R.array.campo2_item_list_ejemplo);

        adaptadorSpinner = new AdaptadorSpinner(getContext(),imagenes,nombres,descripciones,null,null);
        spTipoCita.setAdapter(adaptadorSpinner);

        edtHora.setEnabled(false);
        edtFecha.setEnabled(false);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFecha.setBackgroundResource(R.drawable.calendario_64_2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnFecha.setBackgroundResource(R.drawable.calendario_64_1);

                        //logica
                        Metodos.fecha(getContext(),edtFecha);
                    }
                },100);
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHora.setBackgroundResource(R.drawable.alarm_negro);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnHora.setBackgroundResource(R.drawable.alarm_celeste);

                        //logica
                        Metodos.hora(getContext(),edtHora);
                    }
                },100);
            }
        });

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

        return view;
    }
}
