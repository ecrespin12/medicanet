package com.example.medicanet.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.medicanet.R;
import com.example.medicanet.dialog_admin_ejemplo;


public class fragment_admin_doctor extends Fragment {

    private static final String TAG ="fragment_admin_doctor";


    public fragment_admin_doctor() {
        // Required empty public constructor
    }
    //objetos
    private Button btnOpenDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_admin_doctor, container, false);
        btnOpenDialog = view.findViewById(R.id.btnOpenDialogDoc_admin);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG,"ingresando"); //log de fragment

                //dialog/modal a invocar
                dialog_admin_frmDoctor dialog = new dialog_admin_frmDoctor();
                dialog.show(getFragmentManager(), "dialog_admin_frmDoctor");
            }
        });

        return view;
    }


}
