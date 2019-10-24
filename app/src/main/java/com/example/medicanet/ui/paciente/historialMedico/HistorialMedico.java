package com.example.medicanet.ui.paciente.historialMedico;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.medicanet.R;

import java.util.List;

import clasesResponse.HistorialModel;
import clasesResponse.clPrueba;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialMedico extends Fragment {
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    ListView lvHistorialMedico;
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
        Log.d("JTDebug", "Entra Metodo consulta");
        Call<List<HistorialModel>> call = servicio.getHistorialPaciente(2);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<HistorialModel>>() {
            @Override
            public void onResponse(Call<List<HistorialModel>> call, Response<List<HistorialModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if(response.isSuccessful()){
                        Log.d("JTDebug", "Entra IsSuccessful");
                        List<HistorialModel> resp = response.body();
                        HistorialAdapter ha = new HistorialAdapter(view.getContext(),resp);
                        lvHistorialMedico.setAdapter(ha);
                    }else{
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
