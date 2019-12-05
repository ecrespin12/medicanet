package com.example.medicanet.ui.paciente.perfil;

import android.content.Context;
import android.net.Uri;
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

import clasesResponse.PerfilPacienteModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class perfil extends Fragment {
    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<PerfilPacienteModel> resp;
    PerfilPacienteModel item;
    //###########################################################
    ListView lv_datos_pac;

    String[] per_nombre;
    String[] per_apellido;
    String[] per_fecha_nace;
    String[] per_correo;
    String[] per_estado;
    String[] per_dui;

    public perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        lv_datos_pac = view.findViewById(R.id.lv_datos_pac);
        // Inflate the layout for this fragment

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));
        getPerfil();



        return view;
    }

    //METODO PARA CONSUMIR EL WS
    private void getPerfil() {
        Log.d("JTDebug", "Entra Metodo getPerfil");
        Call<List<PerfilPacienteModel>> call = servicio.getPerfil(1, "", "", "", "", "");
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<PerfilPacienteModel>>() {
            @Override
            public void onResponse(Call<List<PerfilPacienteModel>> call, Response<List<PerfilPacienteModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                       Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        per_nombre=new String[resp.size()];
                        per_apellido=new String[resp.size()];
                        per_fecha_nace=new String[resp.size()];
                        per_correo=new String[resp.size()];
                        per_estado=new String[resp.size()];
                        per_dui=new String[resp.size()];


                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            per_nombre[i] = "Medicina: " +item.per_nombre;
                            per_apellido[i] = "Medicina: " +item.per_apellido;
                            per_fecha_nace[i] = "Medicina: " +item.per_fecha_nace;
                            per_correo[i] = "Medicina: " +item.per_correo;
                            per_estado[i] = "Medicina: " +item.per_estado;
                            per_dui[i] = "Medicina: " +item.per_dui;

                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, per_nombre, per_apellido, per_fecha_nace, per_correo);
                        lv_datos_pac.setAdapter(adaptadorList);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<PerfilPacienteModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
