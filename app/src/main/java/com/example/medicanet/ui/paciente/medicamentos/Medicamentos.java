package com.example.medicanet.ui.paciente.medicamentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.AdaptadorSpinner;

import java.util.List;

import clasesResponse.MedicamentosModel;
import clasesResponse.MedicamentosPendientesModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Medicamentos extends Fragment {

    /** Pasos para consumir
            1- Primero revisar si el model esta hecho si no ghacerlo,
2- despues ir IServices y hacer el metodo del excel, los nombres de los model debel ser identicos al del excel
            3- despues modifical el fragment de lka clase que corresponde
//!>
    **/
    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<MedicamentosPendientesModel> resp;
    MedicamentosPendientesModel item;
    //###########################################################
    ListView lvMedicamentosPendientes;

    String[] per_codigo;
    String[] med_nombre;
    String[] mdc_nombre;
    String[] mdc_descripcion;
    String[] rme_cantidad;
    String[] rme_indicaciones;

    public Medicamentos() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pac_medicamentos, container, false);
        lvMedicamentosPendientes = view.findViewById(R.id.lv_pendientes_entregas_pac);
        // Inflate the layout for this fragment

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));
        getMedicamentosPendientes();



        return view;
    }

    //METODO PARA CONSUMIR EL WS
    private void getMedicamentosPendientes() {
        Log.d("JTDebug", "Entra Metodo getmedicamentosPendientes");
        Call<List<MedicamentosPendientesModel>> call = servicio.getMedicamentosPendientes(1);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<MedicamentosPendientesModel>>() {
            @Override
            public void onResponse(Call<List<MedicamentosPendientesModel>> call, Response<List<MedicamentosPendientesModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
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
                        lvMedicamentosPendientes.setAdapter(adaptadorList);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<MedicamentosPendientesModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
