package com.example.medicanet.ui.farmacia.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.medicanet.R;
import com.example.medicanet.metodos.Metodos;

public class FragmentHistorialEntregas extends Fragment {

    public FragmentHistorialEntregas() {
        // Required empty public constructor
    }

    private ImageButton imgDesde, imgHasta;
    private EditText edtDesde, edtHasta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_far_historial_entregas, container, false);

        imgDesde = view.findViewById(R.id.imgbDesde_fragment_far_historial_entregas);
        imgHasta = view.findViewById(R.id.imgbHasta_fragment_far_historial_entregas);
        edtDesde = view.findViewById(R.id.edtDesde_fragment_far_historial_entregas);
        edtHasta = view.findViewById(R.id.edtHasta_fragment_far_historial_entregas);

        imgDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos.fecha(getContext(), edtDesde);
            }
        });
        imgHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos.fecha(getContext(), edtHasta);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
