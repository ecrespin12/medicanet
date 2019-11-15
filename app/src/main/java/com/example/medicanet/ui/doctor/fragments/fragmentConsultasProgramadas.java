package com.example.medicanet.ui.doctor.fragments;

import android.content.res.TypedArray;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    public static String keyCodigo = "codigo";
    public static String keyNombre = "nombre";
    public static String keyFecha = "fecha";
    public static String keyHoras = "hora";

    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;

    EditText edtCodigoConsulta;
    Button btnBuscar;
    ListView lvLista;

    AdaptadorListView adaptadorListView;

    int[] imagenes;
    String[] codigos;
    String[] nombres;
    String[] fechas;
    String[] horas;

    public fragmentConsultasProgramadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_consultas_programadas, container, false);
        //CODIGO AGREGADO////////////////////////////////////////////////
        edtCodigoConsulta = view.findViewById(R.id.edtCodigoConsulta_fragment_doc_consultas_programadas);
        btnBuscar = view.findViewById(R.id.btnBuscar_fragment_doc_consultas_programadas);
        lvLista = view.findViewById(R.id.lvConsultas_fragment_doc_consultas_programadas);

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        //CARGAR EL LISTVIEW CON EL METODO GETCONSULTAS
        getConsultas(view,0,1,0);

        /*imagenes = getResources().obtainTypedArray(R.array.img_item_list_ejemplo);
        nombres = getResources().getStringArray(R.array.campo1_item_list_ejemplo);
        descripciones = getResources().getStringArray(R.array.campo2_item_list_ejemplo);

        adaptadorListView = new AdaptadorListView(getContext(), imagenes, nombres, descripciones, null, null);
        lvLista.setAdapter(adaptadorListView);*/

        //AGREGAR EVENTO CLICKLISTENER AL LISTVIEW
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //Pausa para que haga la transicion, esto para que se note el efecto de Click sobre el listView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //creando bundle para pasar datos al fragmento
                        Bundle paqueteDeDatos = new Bundle();
                        paqueteDeDatos.putInt(keyImg, imagenes[position]);
                        paqueteDeDatos.putString(keyCodigo, codigos[position]);
                        paqueteDeDatos.putString(keyNombre, nombres[position]);
                        paqueteDeDatos.putString(keyFecha,fechas[position]);
                        paqueteDeDatos.putString(keyHoras,horas[position]);

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
                },200);
            }
        });

        //AGREGAR EVENTO CLICKLISTENER AL BOTON
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                btnBuscar.setBackgroundResource(R.drawable.boton_redondeado);
                btnBuscar.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnBuscar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnBuscar.setTextColor(Color.BLACK);

                        //Codigo para logica del boton
                        Toast.makeText(getContext(), "Buscando", Toast.LENGTH_SHORT).show();
                        int codConsulta=-1000;//numero inventado xd
                        try{
                            codConsulta=Integer.valueOf(edtCodigoConsulta.getText().toString());
                        }catch (Exception e ){
                            System.out.println("Error al convertir codConsulta: "+e);
                        }
                        if (codConsulta==-1000){
                            edtCodigoConsulta.setError("Formato incorrecto, verifique!");
                            edtCodigoConsulta.requestFocus();
                            return;
                        }

                        getConsultas(view,0,0,codConsulta);
                    }
                }, 100);
            }
        });


        //FIN CODIGO AGREGADO////////////////////////////////////////////////////
        return view;
    }

    //METODO PARA CONSUMIR EL WS
    private void getConsultas(final View view, int per, int doc, int cod) {
        Log.d("JTDebug", "Entra Metodo consulta");
        Call<List<ConsultaModel>> call = servicio.getConsultas(per, doc, cod);
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
                        imagenes = new int[resp.size()];
                        codigos = new String[resp.size()];
                        nombres = new String[resp.size()];
                        fechas = new String[resp.size()];
                        horas = new String[resp.size()];

                        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
                        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");

                        for (int i=0;i<resp.size();i++) {
                            ConsultaModel item = resp.get(i);
                            imagenes[i]=R.drawable.medicanet1;
                            codigos[i] = "Código consulta: "+item.cme_codigo;
                            nombres[i] = "Paciente: "+item.per_nombre;
                            fechas[i] = "Fecha: "+dateFormat.format(item.cme_fecha_hora);
                            horas[i] = "Hora: "+hourFormat.format(item.cme_fecha_hora);
                        }
                        AdaptadorListView ha = new AdaptadorListView(getContext(), imagenes, codigos, nombres, fechas, horas);
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
