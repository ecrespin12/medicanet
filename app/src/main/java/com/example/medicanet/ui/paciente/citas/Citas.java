package com.example.medicanet.ui.paciente.citas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.medicanet.R;
import com.example.medicanet.metodos.Metodos;

/**
 * A simple {@link Fragment} subclass.
 */
public class Citas extends Fragment  {

    public Citas() {
        // Required empty public constructor
    }

    ImageButton obtenerFechaI, obtenetFechaF, btnBuscar;
    EditText fechaI, fechaF;
    ListView lvCitasPendientes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pac_citas, container, false);
        obtenerFechaI = view.findViewById(R.id.ib_obtener_fecha);
        fechaI = view.findViewById(R.id.et_mostrar_fecha_picker);
        obtenetFechaF = view.findViewById(R.id.ib_obtener_fechalimite);
        fechaF = view.findViewById(R.id.et_mostrar_fecha_limite);
        btnBuscar = view.findViewById(R.id.btnBuscar_pac);
        lvCitasPendientes = view.findViewById(R.id.lvCitasPendientes_pac);
        obtenerFechaI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Metodos.fecha(getContext(), fechaI);
            }
        });

        obtenetFechaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Metodos.fecha(getContext(), fechaF);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }
}
