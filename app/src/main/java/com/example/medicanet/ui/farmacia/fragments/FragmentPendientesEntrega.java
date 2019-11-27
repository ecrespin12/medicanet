package com.example.medicanet.ui.farmacia.fragments;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.Metodos;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarCita;
import com.example.medicanet.ui.farmacia.fragments.dialogs.DialogEntregaMedicamentos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import clasesResponse.EntregaMedicamentoDetalleModel;
import clasesResponse.EntregaMedicamentosModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPendientesEntrega extends Fragment {

    private ListView lvPendientesEntrega;
    private Bundle bd;
    private ImageButton imgDesde, imgHasta;
    private EditText edtDesde, edtHasta;

    RetrofitClientInstance ret = new RetrofitClientInstance();
    List<EntregaMedicamentoDetalleModel> resp;
    EntregaMedicamentoDetalleModel item;

    FloatingActionButton fabBuscar;
    public int eme;
    private IServices servicio;
    public String [] Pac_nombre;
    public String [] Med_medico;
    public String [] Pac_codigo;
    public String [] fecha_entrega;


    public FragmentPendientesEntrega() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_far_pendientes_entrega, container, false);

        servicio = (IServices) ret.createService(IServices.class, v.getContext().getResources().getString(R.string.token));

        lvPendientesEntrega = v.findViewById(R.id.lvPendientesEntrega_fragment_far_pendientes_entrega);
        imgDesde = v.findViewById(R.id.imgbDesde_fragment_far_pendientes_entrega);
        imgHasta = v.findViewById(R.id.imgbHasta_fragment_far_pendientes_entrega);

        edtDesde = v.findViewById(R.id.edtDesde_fragment_far_pendientes_entrega);
        edtHasta = v.findViewById(R.id.edtHasta_fragment_far_pendientes_entrega);

        /*AdaptadorListView ha = new AdaptadorListView(getContext(),img,Arr1,Arr2,Arr3,Arr4);
        lvPendientesEntrega.setAdapter(ha);*/
        bd = getArguments();
        eme = bd.getInt("codigo");

        getEntregaMedicamentosDetalle(eme);

       //String Titulo = bd.getString("titulo");

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

       lvPendientesEntrega.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {


                       //crear y mostrar un Dialog
                       DialogEntregaMedicamentos dialog = new DialogEntregaMedicamentos();
                       dialog.show(getFragmentManager(), "dialog_entregar_Medicamentos");
                   }
               },100);
           }
       });
        // Inflate the layout for this fragment
        return v;
    }

    public void getEntregaMedicamentosDetalle(int emes){
        Log.d("JTDebug", "Entra Metodo getEntregaMedicamentoDetalleModel");
        Call<List<EntregaMedicamentoDetalleModel>> call = servicio.getEntregaMedicamentosDetalle(emes);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<EntregaMedicamentoDetalleModel>>() {
            @Override
            public void onResponse(Call<List<EntregaMedicamentoDetalleModel>> call, Response<List<EntregaMedicamentoDetalleModel>> response) {
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
                            Pac_nombre[i] = "Nombre: "+item.mdc_nombre;
                            Pac_codigo[i] = "Indicaciones: " + item.rme_indicaciones;
                            Med_medico[i] = "Cantidad: " +item.ede_cantidad;
                            fecha_entrega[i] = "Estado: "+item.ede_estado;
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, Pac_nombre, Pac_codigo, Med_medico, fecha_entrega);
                        lvPendientesEntrega.setAdapter(adaptadorList);


                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<EntregaMedicamentoDetalleModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
