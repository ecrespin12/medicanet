package com.example.medicanet.ui.farmacia.fragments.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.Metodos;
import com.example.medicanet.ui.farmacia.fragments.FragmentPendientesEntrega;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import clasesResponse.EntregaMedicamentosModel;
import clasesResponse.UpdateEntregaDeMedicamentosModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class DialogEntregaMedicamentos extends DialogFragment {

    public FloatingActionButton btnCancelar, btnGuardar;
    EditText txtMedicamento,txtCantidad;
    public MultiAutoCompleteTextView txtIndicaciones;
    private IServices servicio;
    RetrofitClientInstance ret = new RetrofitClientInstance();
    UpdateEntregaDeMedicamentosModel item;
    public Date Date;
    public int count= 0;


    public DialogEntregaMedicamentos() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_far_pendientes_entrega_modal, container, false);

        servicio = (IServices) ret.createService(IServices.class, v.getContext().getResources().getString(R.string.token));

        btnCancelar = v.findViewById(R.id.btnCancelar_far_modal_pendiente_entrega);
        btnGuardar = v.findViewById(R.id.btnGuardar_far_modal_pendiente_entrega);
        txtMedicamento = v.findViewById(R.id.edtMedicamento_far_modal_pendiente_entrega);
        txtCantidad = v.findViewById(R.id.edtCantidad_far_modal_pendiente_entrega);
        txtIndicaciones = v.findViewById(R.id.txtIndicaciones_far_modal_pendiente_entrega);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);

        Bundle bd = getArguments();
        txtMedicamento.setEnabled(false);
        txtIndicaciones.setEnabled(false);

        final int Cantidades = bd.getInt("cantidades"), Codigo = bd.getInt("codigo"),Cantidad = bd.getInt("cantidad");
        final String Nombre=bd.getString("nombre"),cod_det = bd.getString("cod_det"), cod_med = bd.getString("mdc_codigo"),Indicaciones=bd.getString("indicaciones");
        txtCantidad.setText(""+Cantidades);
        txtMedicamento.setText(""+Nombre);
        txtIndicaciones.setText(""+Indicaciones);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = Metodos.FormatoFecha();

                if(Cantidad > count){
                    postEntregaMedicamentoUpdateDetalle(cod_det,Cantidades,"E", fecha,cod_med);
                    count =count + 1;
                }
                if(Cantidad == count){
                    postEntregaMedicamentoUpdate(Codigo,"E", fecha);
                    count = 0;
                    txtCantidad.setEnabled(false);
                    btnGuardar.setEnabled(false);
                }


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }


    public void postEntregaMedicamentoUpdate(int cod, String est,  String fec){


        Log.d("JTDebug", "Entra Metodo postEntregaMedicamentoUpdate");
        Call<Boolean> call = servicio.postEntregaMedicamentoUpdate(cod,est,fec);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        Boolean resp ;
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp);
                        if(resp==true){
                            Toast.makeText(getContext(),"Se ha completado la Entrega de Medicamento",Toast.LENGTH_LONG).show();
                        }


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }



    public void postEntregaMedicamentoUpdateDetalle(String cod,int can, String est,  String fec,String mdc){


        Log.d("JTDebug", "Entra Metodo postEntregaMedicamentosUpdateDetalle");
        Call<Boolean> call = servicio.postEntregaMedicamentoUpdateDetalle(cod,mdc,can,est,fec);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        Log.d("JTDebug", fec + " " + mdc + " " + cod + " " + can);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        Boolean resp ;
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp);
                        if(resp==true){
                            Toast.makeText(getContext(),"Operacion Realizada Exitosamente",Toast.LENGTH_LONG).show();
                        }


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
