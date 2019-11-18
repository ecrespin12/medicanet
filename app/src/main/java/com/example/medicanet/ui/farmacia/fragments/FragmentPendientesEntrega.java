package com.example.medicanet.ui.farmacia.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.medicanet.R;

public class FragmentPendientesEntrega extends Fragment {


    public FragmentPendientesEntrega() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_far_pendientes_entrega, container, false);
    }

}
