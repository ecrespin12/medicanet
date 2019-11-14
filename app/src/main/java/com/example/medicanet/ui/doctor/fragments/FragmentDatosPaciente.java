package com.example.medicanet.ui.doctor.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicanet.R;

public class FragmentDatosPaciente extends Fragment {

    View view;
    TextView tv;
    String codigo;
    String nombre;

    public FragmentDatosPaciente(String codigo, String nombre) {
        // Required empty public constructor
        this.codigo=codigo;
        this.nombre=nombre;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doc_datos_paciente, container, false);
        //CODIGO AGREGADO
        tv=view.findViewById(R.id.tv);
        tv.setText(codigo);
        //FIN DE CODIGO
        return view;
    }

}
