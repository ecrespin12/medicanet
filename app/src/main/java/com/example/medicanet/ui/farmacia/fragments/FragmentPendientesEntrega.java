package com.example.medicanet.ui.farmacia.fragments;


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
import com.example.medicanet.ui.farmacia.fragments.dialogs.DialogEntregaMedicamentos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import clasesResponse.EntregaMedicamentoDetalleModel;
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
    public int codigo;
    public int eme;
    private IServices servicio;
    public String [] Pac_nombre;
    public String [] Med_medico;
    public String [] indicaciones;
    public String [] fecha_entrega;
    public int [] codigo_med;
    public String [] eme_codigo;
    public int ede_cantidad = 0;


    public FragmentPendientesEntrega() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_far_pendientes_entrega, container, false);



        servicio = (IServices) ret.createService(IServices.class, v.getContext().getResources().getString(R.string.token));
        lvPendientesEntrega = v.findViewById(R.id.lvPendientesEntrega_fragment_far_pendientes_entrega);

        /*AdaptadorListView ha = new AdaptadorListView(getContext(),img,Arr1,Arr2,Arr3,Arr4);
        lvPendientesEntrega.setAdapter(ha);*/
        bd = getArguments();
        eme = bd.getInt("codigo");

        getEntregaMedicamentosDetalle(eme);

       //String Titulo = bd.getString("titulo");


       lvPendientesEntrega.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {


                       Bundle paqueteDeDatos = new Bundle();
                       paqueteDeDatos.putInt("codigo", codigo_med[position]);
                       paqueteDeDatos.putString("estado", eme_codigo[position]);
                       paqueteDeDatos.putInt("cantidad", ede_cantidad);
                       paqueteDeDatos.putString("indicaciones",indicaciones[position]);
                       paqueteDeDatos.putString("nombre",Pac_nombre[position]);
                       //crear y mostrar un Dialog
                       DialogEntregaMedicamentos dialog = new DialogEntregaMedicamentos();
                       dialog.setArguments(paqueteDeDatos);
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
                    ede_cantidad = 0;
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        Pac_nombre=new String[resp.size()];
                        Med_medico=new String[resp.size()];
                        indicaciones =new String[resp.size()];
                        fecha_entrega=new String[resp.size()];
                        codigo_med =new int[resp.size()];
                        eme_codigo =new String[resp.size()];


                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            ede_cantidad += 1;
                            eme_codigo[i] = item.ede_estado;
                            codigo_med[i] = item.eme_codigo;
                            Pac_nombre[i] = "Nombre: "+item.mdc_nombre;
                            indicaciones[i] = "" + item.rme_indicaciones;
                            Med_medico[i] = "Cantidad: " +item.ede_cantidad;
                            fecha_entrega[i] = "Estado: "+item.ede_estado;
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, Pac_nombre, indicaciones, Med_medico, fecha_entrega);
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
