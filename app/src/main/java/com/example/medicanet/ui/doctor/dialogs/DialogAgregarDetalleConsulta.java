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
import android.widget.TextView;
import android.widget.Toast;
import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.fragments.FragmentConsulta;
import clasesResponse.ConsultaModel;
import clasesResponse.DatosMedicosModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAgregarDetalleConsulta extends DialogFragment {

    EditText edtNombre;
    EditText edtDescripcion;
    Button btnGuardar;
    Button btnEliminar;
    ImageView btnCerrar;

    TextView tvTitulo;

    //VARIABLES DEL WS
    private IServices servicio;
    RetrofitClientInstance ret = new RetrofitClientInstance();
    /////////////////////////////////////////////////////////////

    ConsultaModel consulta;
    FragmentConsulta fragmentConsulta;
    DatosMedicosModel datoMedico;

    boolean gestionando=false;

    public DialogAgregarDetalleConsulta(ConsultaModel consulta, FragmentConsulta fragmentConsulta, DatosMedicosModel datoMedico) {
        this.consulta=consulta;
        this.fragmentConsulta=fragmentConsulta;
        this.datoMedico=datoMedico;
        if (datoMedico!=null){
            gestionando=true;
        }
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
        btnEliminar=view.findViewById(R.id.btnEliminar_doc_modal_agregar_detalle_consulta);
        btnCerrar=view.findViewById(R.id.btnCerrar_doc_modal_agregar_detalle);

        tvTitulo=view.findViewById(R.id.tvTitulo_doc_modal_agregar_detalle_consulta);

        btnEliminar.setVisibility(View.GONE);

        if (gestionando){
            tvTitulo.setText("Gestionar detalle de consulta");
            btnGuardar.setText("Editar detalle");
            btnEliminar.setVisibility(View.VISIBLE);
            edtNombre.setText(datoMedico.dcm_nombre);
            edtDescripcion.setText(datoMedico.dcm_descripcion);
        }

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
                        int cme = consulta.cme_codigo;


                        if (gestionando){
                            Toast.makeText(getContext(), "Editando...", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }else{
                            Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                            postAgregarDetalleConsulta(nom,dsc,cme);
                        }

                    }
                },100);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEliminar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                btnEliminar.setTextColor(Color.BLACK);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnEliminar.setBackgroundResource(R.drawable.boton_style_modal);
                        btnEliminar.setTextColor(Color.WHITE);

                        //logica

                        Toast.makeText(getContext(), "Eliminando...", Toast.LENGTH_SHORT).show();
                        dismiss();
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
                            Toast.makeText(getContext(),"Operacion realizada exitosamente",Toast.LENGTH_SHORT).show();
                            fragmentConsulta.getDetalles();
                            dismiss();
                        }
                        if(resp==0){
                            Toast.makeText(getContext(),"Operacion no realizada",Toast.LENGTH_SHORT).show();
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
