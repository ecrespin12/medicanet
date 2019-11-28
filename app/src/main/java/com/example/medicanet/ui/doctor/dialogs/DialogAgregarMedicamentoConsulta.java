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
import com.example.medicanet.metodos.AdaptadorSpinner;

import java.text.SimpleDateFormat;
import java.util.List;

import clasesResponse.MedicamentosModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAgregarMedicamentoConsulta extends DialogFragment {

    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<MedicamentosModel> resp;
    MedicamentosModel item;
    //###########################################################

    ImageView btnCerrar;
    Spinner spTipoMedicamento;
    EditText edtCantidad,edtIndicaciones;
    Button btnGuardar;

    String [] codigosMedicamentos;
    String [] nombresMedicamentos;

    public DialogAgregarMedicamentoConsulta() {
        this.setCancelable(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doc_agregar_medicamento_consulta, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnCerrar = view.findViewById(R.id.imgCerrar_doc_modal_agregar_medicamento);
        btnGuardar = view.findViewById(R.id.btnGuardar_doc_modal_agregar_medicamento);
        spTipoMedicamento = view.findViewById(R.id.spTipo_doc_modal_agregar_medicamento);
        edtCantidad = view.findViewById(R.id.edtCantidad_doc_modal_agregar_medicamento);
        edtIndicaciones = view.findViewById(R.id.edtIndicaciones_doc_modal_agregar_medicamento);

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));
        getMedicamentos();


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

    //METODO PARA CONSUMIR EL WS
    private void getMedicamentos() {
        Log.d("JTDebug", "Entra Metodo getmedicamentos");
        Call<List<MedicamentosModel>> call = servicio.getMedicamentos();
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<MedicamentosModel>>() {
            @Override
            public void onResponse(Call<List<MedicamentosModel>> call, Response<List<MedicamentosModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        codigosMedicamentos=new String[resp.size()];
                        nombresMedicamentos=new String[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            codigosMedicamentos[i] = "Código: "+item.mdc_codigo;
                            nombresMedicamentos[i] = item.mdc_nombre;
                        }
                        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(getContext(), null, codigosMedicamentos, nombresMedicamentos, null, null);
                        spTipoMedicamento.setAdapter(adaptadorSpinner);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<MedicamentosModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
