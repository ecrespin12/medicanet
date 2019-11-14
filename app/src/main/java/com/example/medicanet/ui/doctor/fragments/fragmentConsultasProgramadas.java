package com.example.medicanet.ui.doctor.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;

import java.text.SimpleDateFormat;
import java.util.List;

import clasesResponse.ConsultaModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragmentConsultasProgramadas extends Fragment {

    public static String keyImg = "img";
    public static String keyNombre = "nombre";
    public static String keyDescripcion = "descripcion";

    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;

    ListView lvLista;
    AdaptadorListView adaptadorListView;

    TypedArray imagenes;
    String[] nombres;
    String[] descripciones;

    Button btnBuscar;

    public fragmentConsultasProgramadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_consultas_programadas, container, false);

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        //Codigo

        lvLista = view.findViewById(R.id.lvConsultas_fragment_doc_consultas_pendientes);

        imagenes = getResources().obtainTypedArray(R.array.img_item_list_ejemplo);
        nombres = getResources().getStringArray(R.array.campo1_item_list_ejemplo);
        descripciones = getResources().getStringArray(R.array.campo2_item_list_ejemplo);

        adaptadorListView = new AdaptadorListView(getContext(), imagenes, nombres, descripciones, null, null);
        lvLista.setAdapter(adaptadorListView);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //creando bundle para pasar datos al fragmento
                Bundle paqueteDeDatos = new Bundle();
                paqueteDeDatos.putInt(keyImg, imagenes.getResourceId(position, -1));
                paqueteDeDatos.putString(keyNombre, nombres[position]);
                paqueteDeDatos.putString(keyDescripcion, descripciones[position]);

                // Crea el nuevo fragmento
                fragmentDatosConsulta fragmentDatosConsulta = new fragmentDatosConsulta();
                //Agregamos los argumentos al fragmento
                fragmentDatosConsulta.setArguments(paqueteDeDatos);
                //Crea la transaccion
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //remplazar el nuevo fragmento en el contenedor principal(nav_host_fragment)
                transaction.replace(R.id.nav_host_fragment, fragmentDatosConsulta);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        btnBuscar = view.findViewById(R.id.btnBuscar_fragment_doc_consultas_pendientes);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBuscar.setBackgroundResource(R.drawable.boton_redondeado);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnBuscar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        Toast.makeText(getContext(), "buscando", Toast.LENGTH_SHORT).show();
                    }
                }, 100);
            }
        });
        //fin codigo agregado
        getConsultas(view);
        return view;
    }

    private void getConsultas(final View view) {
        Log.d("JTDebug", "Entra Metodo consulta");
        Call<List<ConsultaModel>> call = servicio.getConsultas(0, 2, 0);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<ConsultaModel>>() {
            @Override
            public void onResponse(Call<List<ConsultaModel>> call, Response<List<ConsultaModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        List<ConsultaModel> resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        String[] arrTit = new String[resp.size()];
                        String[] arrFec = new String[resp.size()];
                        String[] arrHora = new String[resp.size()];
                        int c = 0;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
                        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");

                        for (ConsultaModel item : resp) {
                            Log.d("JTDebug", "Conteo de arr " + c);
                            //DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
                            arrTit[c] = item.per_nombre;
                            arrFec[c] = dateFormat.format(item.cme_fecha_hora); //dateFormat.format(item.cme_fecha_hora);
                            arrHora[c] = hourFormat.format(item.cme_fecha_hora); //dateFormat.format(item.cme_fecha_hora);
                            c++;
                        }
                        AdaptadorListView ha = new AdaptadorListView(getContext(), null, arrTit, arrFec, arrHora, null);
                        lvLista.setAdapter(ha);
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
