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
    String[] hme_codthm;
    String[] hme_descripcion;
    String[] hme_fecha_crea;
    String[] thm_nombre;
    public HistorialMedico() {
        // Required empty public constructor
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
        Call<List<HistorialModel>> call = servicio.getHistorialPaciente( 1);
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
                        hme_codthm=new String[resp.size()];
                        hme_descripcion=new String[resp.size()];
                        hme_fecha_crea=new String[resp.size()];
                        thm_nombre=new String[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            hme_codthm[i] = "Paciente: "+item.hme_codthm;
                            hme_descripcion[i] = "Cantidad pendiente: " + item.hme_descripcion;
                            hme_fecha_crea[i] = "Medicina: " +item.hme_fecha_crea;
                            thm_nombre[i] = "Indicaciones: "+item.thm_nombre;
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, hme_codthm, hme_descripcion, hme_fecha_crea, thm_nombre);
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
