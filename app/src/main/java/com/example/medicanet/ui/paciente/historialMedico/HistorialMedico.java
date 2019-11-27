package com.example.medicanet.ui.paciente.historialMedico;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;

import java.text.SimpleDateFormat;
import java.util.List;
import clasesResponse.HistorialModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialMedico extends Fragment {
    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<HistorialModel> resp;
    HistorialModel item;
    //###########################################################

    ListView lvHistorialMedico;
    String[] arr1;
    String[] arr2;
    String[] arr3;
    String[] arr4;

    int codigoPaciente=1;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");


    public HistorialMedico() {
        // Required empty public constructor
    }
    public HistorialMedico(int codigoPaciente) {
        this.codigoPaciente=codigoPaciente;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pac_historial_medico, container, false);
        servicio = (IServices)ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));
        lvHistorialMedico = view.findViewById(R.id.lvHistorialMedico_pac);
        // Inflate the layout for this fragment
        getHistorial(view);
        return view;
    }

    private void getHistorial(final View view){
        Log.d("JTDebug", "Entra Metodo getmedicamentosPendientes");
        Call<List<HistorialModel>> call = servicio.getHistorialPaciente( codigoPaciente);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<HistorialModel>>() {
            @Override
            public void onResponse(Call<List<HistorialModel>> call, Response<List<HistorialModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        arr1=new String[resp.size()];
                        arr2=new String[resp.size()];
                        arr3=new String[resp.size()];
                        arr4=new String[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            arr1[i] = "TIPO HISTORIAL: "+item.thm_nombre;
                            arr2[i] = "DESCRIPCIÓN: " + item.hme_descripcion;
                            arr3[i] = "FECHA: " +formatoFecha.format(item.hme_fecha_crea);
                            arr4[i] = "HORA: "+formatoHora.format(item.hme_fecha_crea);
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, arr1, arr2, arr3, arr4);
                        lvHistorialMedico.setAdapter(adaptadorList);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<HistorialModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
