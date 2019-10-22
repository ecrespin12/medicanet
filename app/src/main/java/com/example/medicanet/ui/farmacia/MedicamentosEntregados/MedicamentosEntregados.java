package com.example.medicanet.ui.farmacia.MedicamentosEntregados;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.medicanet.R;
import com.example.medicanet.metodos.MetodosDavid;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentosEntregados extends Fragment {


    public MedicamentosEntregados() {
        // Required empty public constructor
    }

private ImageButton imgB1, imgB2;
    private EditText edt1, edt2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_far_medicamentos_entregados, container, false);

        imgB1 = view.findViewById(R.id.imgb_far_medicamentos_entregados_fecha1);
        imgB2 = view.findViewById(R.id.imgb_far_medicamentos_entregados_fecha2);
        edt1 = view.findViewById(R.id.edt_far_medicamentos_entregados_fecha1);
        edt2 = view.findViewById(R.id.edt_far_medicamentos_entregados_fecha2);

        imgB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MetodosDavid.fecha(getContext(), edt1);
            }
        });
        imgB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MetodosDavid.fecha(getContext(), edt2);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
