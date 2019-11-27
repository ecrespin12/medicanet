package com.example.medicanet.ui.farmacia.fragments;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import clasesResponse.EntregaMedicamentosModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHistorialEntregas extends Fragment {


    RetrofitClientInstance ret = new RetrofitClientInstance();
    ImageButton imgDesde, imgHasta;
    List<EntregaMedicamentosModel> resp;
    EntregaMedicamentosModel item;
    private IServices servicio;
    public String [] Pac_nombre;
    public String [] Med_medico;
    public String [] Pac_codigo;
    public String [] fecha_entrega;
    public ListView lvHistorial;
    public FloatingActionButton fabBuscarHistorial;
    public EditText edtBuscar;

    public FragmentHistorialEntregas() {
        // Required empty public constructor
    }

    private EditText edtDesde, edtHasta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_far_historial_entregas, container, false);
        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        edtBuscar = view.findViewById(R.id.edtId_fragment_fragment_far_historial_entregas);
        fabBuscarHistorial = view.findViewById(R.id.fbaBuscar_fragment_far_historial_entregas);
        imgDesde = view.findViewById(R.id.imgbDesde_fragment_far_historial_entregas);
        imgHasta = view.findViewById(R.id.imgbHasta_fragment_far_historial_entregas);
        edtDesde = view.findViewById(R.id.edtDesde_fragment_far_historial_entregas);
        edtHasta = view.findViewById(R.id.edtHasta_fragment_far_historial_entregas);
        lvHistorial = view.findViewById(R.id.lvMedicamentosEntregados_fragment_far_historial_entregas);
        getEntregaMedicamentos(0,"E",0,0,"","",0,null, null);

        fabBuscarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEntregaMedicamentos(0,"E",0,0,edtBuscar.getText().toString(),"",0,edtDesde.getText().toString(),edtHasta.getText().toString());
            }
        });
        imgDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos.fecha(getContext(), edtDesde);
            }
        });
        imgHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos.fecha(getContext(), edtHasta);

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void getEntregaMedicamentos(int far, String est, int per, int med, String ntag, String dtag, int cod, String fini, String ffin){
        Log.d("JTDebug", "Entra Metodo getEntregaMedicamentosModel");
        Call<List<EntregaMedicamentosModel>> call = servicio.getEntregaMedicamentos(far,est,per,med,ntag,dtag,cod,fini,ffin);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<EntregaMedicamentosModel>>() {
            @Override
            public void onResponse(Call<List<EntregaMedicamentosModel>> call, Response<List<EntregaMedicamentosModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        Pac_nombre=new String[resp.size()];
                        Med_medico=new String[resp.size()];
                        Pac_codigo=new String[resp.size()];
                        fecha_entrega=new String[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            Pac_nombre[i] = "Paciente: "+item.pac_nombre;
                            Pac_codigo[i] = "Codigo Paciente: " + item.pac_codigo;
                            Med_medico[i] = "Medico: " +item.med_nombre;
                            fecha_entrega[i] = "Fecha de entrega: "+item.eme_fecha_entrega;
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, Pac_nombre, Pac_codigo, Med_medico, fecha_entrega);
                        lvHistorial.setAdapter(adaptadorList);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<EntregaMedicamentosModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });

        }
}
