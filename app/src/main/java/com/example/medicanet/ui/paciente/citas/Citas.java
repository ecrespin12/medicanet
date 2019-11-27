package com.example.medicanet.ui.paciente.citas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.Metodos;

import java.util.List;

import clasesResponse.CitasModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Citas extends Fragment  {

    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<CitasModel> resp;
    CitasModel item;
    //###########################################################

    String[] cme_codigo;
    String[] per_nombre;
    String[] per_correo;
    String[] per_dui;
    String[] per_fecha_nace;
    String[] med_nombre;
    String[] med_correo;
    String[] cmd_codigo;
    String[] cmd_nombre;
    String[] cmd_latitud;
    String[] cmd_longitud;
    String[] cme_fecha_hora;

    public Citas() {
        // Required empty public constructor
    }

    ImageButton obtenerFechaI, obtenetFechaF, btnBuscar;
    EditText fechaI, fechaF;
    ListView lvCitasPendientes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pac_citas, container, false);
        obtenerFechaI = view.findViewById(R.id.ib_obtener_fecha);
        fechaI = view.findViewById(R.id.et_mostrar_fecha_picker);
        obtenetFechaF = view.findViewById(R.id.ib_obtener_fechalimite);
        fechaF = view.findViewById(R.id.et_mostrar_fecha_limite);
        btnBuscar = view.findViewById(R.id.btnBuscar_pac);
        lvCitasPendientes = view.findViewById(R.id.lvCitasPendientes_pac);

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        obtenerFechaI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Metodos.fecha(getContext(), fechaI);
            }
        });

        obtenetFechaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Metodos.fecha(getContext(), fechaF);
            }
        });

        getHistorial(view);
        // Inflate the layout for this fragment
        return view;

    }

    private void getHistorial(final View view){
        Log.d("JTDebug", "Entra Metodo getmedicamentosPendientes");
        Call<List<CitasModel>> call = servicio.getCitas( 1,0,0);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<CitasModel>>() {
            @Override
            public void onResponse(Call<List<CitasModel>> call, Response<List<CitasModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        per_nombre=new String[resp.size()];
                        per_correo=new String[resp.size()];
                        per_dui=new String[resp.size()];
                        per_fecha_nace=new String[resp.size()];
                        med_nombre=new String[resp.size()];
                        med_correo=new String[resp.size()];
                        cmd_codigo=new String[resp.size()];
                        cmd_nombre=new String[resp.size()];
                        cmd_latitud=new String[resp.size()];
                        cmd_longitud=new String[resp.size()];
                        cme_fecha_hora=new String[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            per_nombre[i] = "Paciente: "+item.per_nombre;
                            per_correo[i] = "Correo: " + item.per_correo;
                            per_dui[i] = "DUI" +item.per_dui;
                            per_fecha_nace[i] = "Fecha de Nacimiento: "+item.per_fecha_nace;
                            med_nombre[i] = "Nombre del Medico: "+item.med_nombre;
                            med_correo[i] = "Medico Correp: "+item.med_correo;
                            cmd_codigo[i] = "Codigo del medico: "+item.cmd_codigo;
                            cmd_nombre[i] = "Nombre: "+item.cmd_nombre;
                            cmd_latitud[i] = "Latitud: "+item.cmd_latitud;
                            cmd_longitud[i] = "Longitud: "+item.cmd_longitud;
                            cme_fecha_hora[i] = "Fecha: "+item.cme_fecha_hora;
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, per_nombre, per_correo, per_dui, per_fecha_nace);
                        lvCitasPendientes.setAdapter(adaptadorList);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<CitasModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
