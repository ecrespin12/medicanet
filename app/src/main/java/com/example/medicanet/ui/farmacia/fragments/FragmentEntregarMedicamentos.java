package com.example.medicanet.ui.farmacia.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.Metodos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.List;

import clasesResponse.EntregaMedicamentosModel;
import clasesResponse.MedicamentosPendientesModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentEntregarMedicamentos extends Fragment {

    RetrofitClientInstance ret = new RetrofitClientInstance();
    ImageButton imgDesde, imgHasta;
    List<EntregaMedicamentosModel> resp;
    EntregaMedicamentosModel item;

    FloatingActionButton fabBuscar;
    EditText edtDesde, edtHasta;
    public int [] eme;
    private IServices servicio;
    public ListView LvConsultas;
    public EditText edtId;
    public String [] Pac_nombre;
    public String [] Med_medico;
    public String [] Pac_codigo;
    public String [] fecha_entrega;

    public FragmentEntregarMedicamentos() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_far_entregar_medicamentos, container, false);

        edtDesde = v.findViewById(R.id.edtDesde_fragment_far_entregar_medicamentos);
        edtHasta = v.findViewById(R.id.edtHasta_fragment_far_entregar_medicamentos);

        fabBuscar = v.findViewById(R.id.fbaBuscar_fragment_far_entregar_medicamentos);
        imgHasta = v.findViewById(R.id.imgbHasta_fragment_far_entregar_medicamentos);
        imgDesde = v.findViewById(R.id.imgbDesde_fragment_far_entregar_medicamentos);
        edtId = v.findViewById(R.id.edtId_fragment_far_entregar_medicamentos);
        LvConsultas = v.findViewById(R.id.lvConsultasProgramadas_fragment_far_entregar_medicamentos);



        servicio = (IServices) ret.createService(IServices.class, v.getContext().getResources().getString(R.string.token));

        getEntregaMedicamentos(0,"P",0,0,"","",0);

        fabBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEntregaMedicamentos(0,"P",0,0,edtId.getText().toString(),"",0);
            }
        });
        imgDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos.fecha(getContext(),edtDesde);

            }
        });

        imgHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos.fecha(getContext(),edtHasta);
            }
        });

        LvConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bundle paqueteDeDatos = new Bundle();
                        paqueteDeDatos.putInt("codigo", eme[position]);

                        FragmentPendientesEntrega fragmentPedientesEntrega = new FragmentPendientesEntrega();

                        fragmentPedientesEntrega.setArguments(paqueteDeDatos);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, fragmentPedientesEntrega);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                },100);
            }
        });

        return v;
    }

    public void getEntregaMedicamentos(int far, String est, int per, int med, String ntag, String dtag, int cod){
        Log.d("JTDebug", "Entra Metodo getEntregaMedicamentosModel");
        Call<List<EntregaMedicamentosModel>> call = servicio.getEntregaMedicamentos(far,est,per,med,ntag,dtag,cod);
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
                        eme = new int[resp.size()];

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            eme[i] = item.eme_codigo;
                            Pac_nombre[i] = "Paciente: "+item.pac_nombre;
                            Pac_codigo[i] = "Codigo Paciente: " + item.pac_codigo;
                            Med_medico[i] = "Medico: " +item.med_nombre;
                            fecha_entrega[i] = "Fecha de entrega: "+item.eme_fecha_entrega;
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, Pac_nombre, Pac_codigo, Med_medico, fecha_entrega);
                        LvConsultas.setAdapter(adaptadorList);


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
