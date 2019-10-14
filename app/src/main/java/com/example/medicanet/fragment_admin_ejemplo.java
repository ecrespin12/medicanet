package com.example.medicanet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class fragment_admin_ejemplo extends Fragment {

    private static final String TAG ="fragment_admin_ejemplo";


    //objetos
    private Button btnOpenDialog;

    public fragment_admin_ejemplo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_fragment_admin_ejemplo, container, false);
        btnOpenDialog = view.findViewById(R.id.btnOpenDialog_admin);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG,"ingresando"); //log de fragment

                //dialog/modal a invocar
                dialog_admin_ejemplo dialog = new dialog_admin_ejemplo();
                dialog.show(getFragmentManager(), "dialog_admin_ejemplo");
            }
        });

        return view;
    }

    //methods

    //Metodo que al dar clic en el boton invoca un AlertDialog





}
