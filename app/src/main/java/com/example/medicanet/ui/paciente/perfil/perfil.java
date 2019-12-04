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

    String[] cod;
    String[] nom;
    String[] fec;
    String[] cor;
    String[] est;
    String[] dui;

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
        Log.d("JTDebug", "Entra Metodo getmedicamentosPendientes");
        Call<List<PerfilPacienteModel>> call = servicio.getPerfil(1, "", "", "", "", "");
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<PerfilPacienteModel>>() {
            @Override
            public void onResponse(Call<List<PerfilPacienteModel>> call, Response<List<PerfilPacienteModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                       /* Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        med_nombre=new String[resp.size()];
                        mdc_nombre=new String[resp.size()];
                        mdc_descripcion=new String[resp.size()];
                        rme_cantidad=new String[resp.size()];
                        rme_indicaciones=new String[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            mdc_descripcion[i] = "Medicina: " +item.mdc_descripcion;
                            med_nombre[i] = "Paciente: "+item.med_nombre;
                            mdc_nombre[i] = "Cantidad pendiente: " + item.mdc_nombre;
                            rme_cantidad[i] = "Indicaciones: "+item.rme_cantidad;
                            rme_indicaciones[i] = "Indicaciones:"+item.rme_indicaciones;
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, med_nombre, mdc_nombre, mdc_descripcion, rme_cantidad);
                        lv_datos_pac.setAdapter(adaptadorList);
*/

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
