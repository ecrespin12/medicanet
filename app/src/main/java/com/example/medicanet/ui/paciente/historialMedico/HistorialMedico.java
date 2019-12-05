package com.example.medicanet.ui.paciente.historialMedico;

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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarHistorial;
import java.text.SimpleDateFormat;
import java.util.List;
import clasesResponse.HistorialModel;
import clasesResponse.PacientesModel;
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

    TextView tvTitulo;
    Button btnHistorial;
    ListView lvHistorial;
    ProgressBar pgbHistorial;

    String[] arr1;
    String[] arr2;
    String[] arr3;
    String[] arr4;

    PacientesModel paciente;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");

    boolean agregar;

    public HistorialMedico() {
        // Required empty public constructor
    }
    public HistorialMedico(PacientesModel paciente, boolean agregar) {
        this.paciente=paciente;
        this.agregar=agregar;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pac_historial_medico, container, false);
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        servicio = (IServices)ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        tvTitulo = view.findViewById(R.id.tvTitulo_fragment_pac_historial_medico);
        btnHistorial = view.findViewById(R.id.btnHistorial_fragment_pac_historial_medico);
        lvHistorial = view.findViewById(R.id.lvHistorialMedico_fragment_pac_historial_medico);
        pgbHistorial = view.findViewById(R.id.pgbHistorial_fragment_pac_historial_medico);

        pgbHistorial.setVisibility(View.VISIBLE);

        if (agregar){
            btnHistorial.setVisibility(View.VISIBLE);
        }else{
            btnHistorial.setVisibility(View.GONE);
        }

        tvTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvTitulo.setTextColor(Color.LTGRAY);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvTitulo.setTextColor(Color.BLACK);

                        //logica
                        // Crea el nuevo fragmento
                        HistorialMedico historialMedico = new HistorialMedico();
                        //Crea la transaccion
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        //remplazar el nuevo fragmento en el contenedor principal(nav_host_fragment)
                        transaction.replace(R.id.nav_host_fragment, historialMedico);
                        //agregar la transaccion a la pila
                        //transaction.addToBackStack(null);
                        // Commit a la transacción
                        transaction.commit();
                    }
                },100);
            }
        });

        //AGREGAR EVENTO CLICKLISTENER AL LISTVIEW
        lvHistorial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //Pausa para que haga la transicion, esto para que se note el efecto de Click sobre el listView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // Crea el nuevo fragmento
                        //FragmentDatosConsulta fragmentDatosConsulta = new FragmentDatosConsulta(listConsulta,position);
                        //Crea la transaccion
                        //FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        //remplazar el nuevo fragmento en el contenedor principal(nav_host_fragment)
                        //transaction.replace(R.id.nav_host_fragment, fragmentDatosConsulta);
                        //agregar la transaccion a la pila
                        //transaction.addToBackStack(null);
                        // Commit a la transacción
                        //transaction.commit();

                    }
                },200);
            }
        });

        //AGREGAR EVENTO CLICKLISTENER AL BOTON
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                btnHistorial.setBackgroundResource(R.drawable.historial_medico2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnHistorial.setBackgroundResource(R.drawable.historial_medico);

                        //Codigo para logica del boton
                        DialogAgregarHistorial dialogAgregarHistorial = new DialogAgregarHistorial(paciente,false);
                        dialogAgregarHistorial.show(getFragmentManager(),"dialogAgregarHistorial");

                        //CARGAR EL LISTVIEW CON EL METODO GETCONSULTAS
                        //getConsultas(view,0,1,0, 0, edtFecha.getText().toString().trim(), edtNombrePaciente.getText().toString().trim());

                    }
                }, 100);
            }
        });


        //LLAMAR WS
        getHistorial();

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        return view;
    }

    private void getHistorial(){
        Log.d("JTDebug", "Entra Metodo getmedicamentosPendientes");
        int codigoPaciente=1;
        if (paciente!=null){
            codigoPaciente=paciente.per_codigo;
        }
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
                            arr1[i] = "TITULO: "+item.thm_nombre;
                            arr2[i] = "DESCRIPCIÓN: " + item.hme_descripcion;
                            arr3[i] = "FECHA: " +formatoFecha.format(item.hme_fecha_crea);
                            arr4[i] = "HORA: "+formatoHora.format(item.hme_fecha_crea);
                        }
                        AdaptadorListView adaptadorList = new AdaptadorListView(getContext(), null, arr1, arr2, arr3, arr4);
                        lvHistorial.setAdapter(adaptadorList);
                        pgbHistorial.setVisibility(View.INVISIBLE);


                    } else {
                        pgbHistorial.setVisibility(View.INVISIBLE);
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    pgbHistorial.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<HistorialModel>> call, Throwable t) {
                pgbHistorial.setVisibility(View.INVISIBLE);
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
