package com.example.medicanet.ui.doctor.dialogs;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.os.Handler;
import android.util.Half;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorSpinner;
import com.example.medicanet.metodos.Metodos;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import clasesResponse.CentroMedicoModel;
import clasesResponse.ConsultaModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAgregarCita extends DialogFragment {

    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<CentroMedicoModel> centroMedicoModels;
    CentroMedicoModel centroMedicoModel;

    Spinner             spCentro;
    AdaptadorSpinner    adaptadorSpinner;

    EditText            edtDescripcion;
    EditText            edtFecha;
    EditText            edtHora;

    ImageView           btnCerrar;

    Button              btnGuardar;

    ImageButton         btnFecha;
    ImageButton         btnHora;

    int codigoMedicamento=0;
    int codigoConsulta=0;

    String [] arr1;
    String [] arr2;
    String [] arr3;
    String [] arr4;

    ConsultaModel item;

    public DialogAgregarCita(ConsultaModel item) {
        this.item=item;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doc_agregar_cita, container, false);

        //CODIGO AGREGADO

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, getContext().getResources().getString(R.string.token));

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        //Enlazando vistas
        spCentro = view.findViewById(R.id.spTipo_dialog_doc_agregar_cita);
        edtFecha=view.findViewById(R.id.edtFecha_dialog_doc_agregar_cita);
        edtHora=view.findViewById(R.id.edtHora_dialog_doc_agregar_cita);
        btnCerrar=view.findViewById(R.id.imgCerrar_dialog_doc_agregar_cita);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_cita);
        btnFecha=view.findViewById(R.id.btnFecha_dialog_doc_agregar_cita);
        btnHora=view.findViewById(R.id.btnHora_dialog_doc_agregar_cita);


        edtHora.setEnabled(false);
        edtFecha.setEnabled(false);

        getCentros();

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFecha.setBackgroundResource(R.drawable.calendario_64_2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnFecha.setBackgroundResource(R.drawable.calendario_64_1);

                        //logica
                        Metodos.fecha(getContext(),edtFecha);
                    }
                },100);
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHora.setBackgroundResource(R.drawable.alarm_negro);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnHora.setBackgroundResource(R.drawable.alarm_celeste);

                        //logica
                        Metodos.hora(getContext(),edtHora);
                    }
                },100);
            }
        });

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

        return view;
    }

    //METODO PARA CONSUMIR EL WS
    private void getCentros() {

        Log.d("JTDebug", "Entra Metodo getCentros");
        Call<List<CentroMedicoModel>> call = servicio.getCentroMedico();
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<CentroMedicoModel>>() {
            @Override
            public void onResponse(Call<List<CentroMedicoModel>> call, Response<List<CentroMedicoModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        centroMedicoModels = response.body();
                        Log.d("JTDebug", "Count: " + centroMedicoModels.size());

                        arr1=new String[centroMedicoModels.size()];

                        for (int i=0;i<centroMedicoModels.size();i++) {
                            centroMedicoModel = centroMedicoModels.get(i);
                            arr1[i] = "Código: "+centroMedicoModel.cmd_nombre;
                        }
                        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(getContext(), null, arr1, null, null, null);
                        spCentro.setAdapter(adaptadorSpinner);
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<CentroMedicoModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
