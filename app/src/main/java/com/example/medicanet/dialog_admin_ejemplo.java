package com.example.medicanet;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class dialog_admin_ejemplo extends DialogFragment {

    //nombre de fragment
    private static final String TAG ="dialog_admin_ejemplo";

    //objetos
    public EditText edtNombres, edtApellidos, edtEdad;
    public ImageView tvClose;
    public Button btnGuardar;

    //variables
     String nombres="", apellidos ="", edad="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_admin_formulario, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //ins objetos
        btnGuardar = view.findViewById(R.id.btnGuardar_modal_adm);
        tvClose = view.findViewById(R.id.btnClose_modal_adm);


        edtNombres = view.findViewById(R.id.txtNombres_modal_adm);
        edtApellidos = view.findViewById(R.id.txtApellidos_modal_adm);
        edtEdad = view.findViewById(R.id.txtEdad_modal_adm);



        //Para cancelar
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Saliendo...");
                getDialog().dismiss();
            }
        });


        //para guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Guardando...");
                Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_LONG).show();
                getDialog().dismiss();

            }
        });


        return view;
    }

}
