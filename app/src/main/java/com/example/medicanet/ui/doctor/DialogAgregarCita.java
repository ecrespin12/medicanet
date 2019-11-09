package com.example.medicanet.ui.doctor;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.AdaptadorSpinner;
import com.example.medicanet.metodos.ItemSpinner;
import com.example.medicanet.metodos.Metodos;

import java.util.ArrayList;

public class DialogAgregarCita extends DialogFragment {

    Spinner spTipoCita;

    EditText edtDescripcion;
    EditText edtFecha;
    EditText edtHora;

    ImageView imgCerrar;

    Button btnGuardar;

    ImageButton btnFecha;
    ImageButton btnHora;

    private ArrayList<ItemSpinner> listaDeItem;
    private AdaptadorSpinner adaptadorSpinner;

    AdaptadorListView adaptadorListView;

    TypedArray imagenes;
    String [] nombres;
    String [] descripciones;

    public DialogAgregarCita() {
        // Required empty public constructor
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
        imgCerrar=view.findViewById(R.id.imgCerrar_dialog_doc_agregar_cita);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_cita);
        btnFecha=view.findViewById(R.id.btnFecha_dialog_doc_agregar_cita);
        btnHora=view.findViewById(R.id.btnHora_dialog_doc_agregar_cita);

        imagenes=getResources().obtainTypedArray(R.array.img_item_list_ejemplo);
        nombres=getResources().getStringArray(R.array.campo1_item_list_ejemplo);
        descripciones=getResources().getStringArray(R.array.campo2_item_list_ejemplo);

        adaptadorListView = new AdaptadorListView(getContext(),imagenes,nombres,descripciones,null,null);
        spTipoCita.setAdapter(adaptadorListView);

        //Inicializando la lista del spinner
        //inicializarSpinner();
        //adaptadorSpinner = new AdaptadorSpinner(getContext(), listaDeItem);
        //spTipoCita.setAdapter(adaptadorSpinner);

        edtHora.setEnabled(false);
        edtFecha.setEnabled(false);

        imgCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Metodos.fecha(getContext(),edtFecha);
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Metodos.hora(getContext(),edtHora);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Guardando...",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void inicializarSpinner(){
        listaDeItem = new ArrayList<>();
        listaDeItem.add(new ItemSpinner(R.drawable.medicanet1,"cam1","cam2","cam3",null));
    }

}
