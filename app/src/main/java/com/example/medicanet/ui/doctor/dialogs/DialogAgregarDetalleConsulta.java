package com.example.medicanet.ui.doctor.dialogs;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.fragments.FragmentConsulta;

import clasesResponse.ConsultaModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAgregarDetalleConsulta extends DialogFragment {

    EditText edtNombre;
    EditText edtDescripcion;
    Button btnGuardar;
    ImageView btnCerrar;

    private IServices servicio;
    RetrofitClientInstance ret = new RetrofitClientInstance();

    ConsultaModel item;
    FragmentConsulta fragmentConsulta;

    public DialogAgregarDetalleConsulta(ConsultaModel item, FragmentConsulta fragmentConsulta) {
        this.item=item;
        this.fragmentConsulta=fragmentConsulta;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.dialog_doc_agregar_detalle_consulta, container, false);

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        edtNombre=view.findViewById(R.id.edtNombre_doc_modal_agregar_detalle_consulta);
        edtDescripcion=view.findViewById(R.id.edtDescripcion_doc_modal_agregar_detalle_consulta);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_detalle_consulta);
        btnCerrar=view.findViewById(R.id.btnCerrar_doc_modal_agregar_detalle);

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
                        String nom = edtNombre.getText().toString().trim();
                        String dsc = edtDescripcion.getText().toString().trim();
                        int cme = item.cme_codigo;
                        postAgregarDetalleConsulta(nom,dsc,cme);
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

        return  view;
    }


    public void postAgregarDetalleConsulta(String nom, String dsc,  int cme){


        Log.d("JTDebug", "Entra Metodo postAgregarDetalleConsulta");
        Call<Integer> call = servicio.postAgregarDetalleConsulta(nom,dsc,cme);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        Integer resp ;
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp);
                        if(resp==1){
                            Toast.makeText(getContext(),"Operacion Realizada Exitosamente",Toast.LENGTH_LONG).show();
                            fragmentConsulta.getDetalles();
                        }
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
