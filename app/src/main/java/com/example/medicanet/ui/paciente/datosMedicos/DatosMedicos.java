package com.example.medicanet.ui.paciente.datosMedicos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;

import java.util.List;

import clasesResponse.CitasModel;
import clasesResponse.ConsultaModel;
import clasesResponse.DatosMedicosModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatosMedicos extends Fragment {

    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<DatosMedicosModel> resp;
    DatosMedicosModel item;
    //###########################################################
    String[] dcm_codigo;
    String[] dcm_nombre;
    String[] dcm_descripcion;


    ListView lvDatosMedicos;
    public DatosMedicos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_pac_datos_medicos, container, false);
        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));
        getHistorial(view);

        lvDatosMedicos = view.findViewById(R.id.lvDatosMedicos_pac);
        return view;

    }

    private void getHistorial(final View view){
        Log.d("JTDebug", "Entra Metodo getmedicamentosPendientes");
        Call<List<DatosMedicosModel>> call = servicio.getDatosMedicos( 1,0);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<DatosMedicosModel>>() {
            @Override
            public void onResponse(Call<List<DatosMedicosModel>> call, Response<List<DatosMedicosModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        dcm_codigo=new String[resp.size()];
                        dcm_nombre=new String[resp.size()];
                        dcm_descripcion=new String[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            dcm_codigo[i] = "Codigo: "+item.dcm_codigo;
                            dcm_nombre[i] = "Diagnostico: " + item.dcm_nombre;
                            dcm_descripcion[i] = "Descripcion: " +item.dcm_descripcion;
                            /*per_fecha_nace[i] = "Fecha de Nacimiento: "+item.per_fecha_nace+"\n"
                            +"Latitud: "+item.cmd_latitud+"\n"
                            +"Longitud: "+item.cmd_longitud;
                            med_nombre[i] = "Nombre del Medico: "+item.med_nombre;
                            med_correo[i] = "Medico Correp: "+item.med_correo;
                            cmd_codigo[i] = "Codigo del medico: "+item.cmd_codigo;
                            cmd_nombre[i] = "Nombre: "+item.cmd_nombre;
                            cmd_latitud[i] = "Latitud: "+item.cmd_latitud;
                            cmd_longitud[i] = "Longitud: "+item.cmd_longitud;
                            cme_fecha_hora[i] = "Fecha: "+item.cme_fecha_hora;*/
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, dcm_codigo, dcm_nombre, dcm_descripcion, null);
                        lvDatosMedicos.setAdapter(adaptadorList);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<DatosMedicosModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
