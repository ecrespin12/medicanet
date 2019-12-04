package com.example.medicanet.ui.doctor.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.Metodos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import clasesResponse.ConsultaModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentConsultasProgramadas extends Fragment {

    //VARIABLES PARA EL WS
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    ////////////////////////////////////////////////////////////////////////////
    List<ConsultaModel> listConsulta;
    ConsultaModel consulta;

    TextView tvTitulo;

    EditText edtFecha;
    EditText edtNombrePaciente;
    Button btnBuscar;
    Button btnFecha;

    ImageView imgRecargar;

    ProgressBar pgbRecargar, pgbLista;

    ListView lvLista;

    AdaptadorListView adaptadorListView;

    int[] imagenes;
    String[] codigos;
    String[] nombres;
    String[] fechas;
    String[] horas;

    public FragmentConsultasProgramadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_consultas_programadas, container, false);
        //CODIGO AGREGADO////////////////////////////////////////////////
        edtFecha = view.findViewById(R.id.edtFecha_fragment_doc_consultas_programadas);
        edtNombrePaciente = view.findViewById(R.id.edtNombrePaciente_fragment_doc_consultas_programadas);
        btnBuscar = view.findViewById(R.id.btnBuscar_fragment_doc_consultas_programadas);
        btnFecha = view.findViewById(R.id.btnFecha_fragment_doc_consultas_programadas);
        lvLista = view.findViewById(R.id.lvConsultas_fragment_doc_consultas_programadas);

        imgRecargar = view.findViewById(R.id.imgRecargar_fragment_doc_consultas_programadas);
        pgbRecargar = view.findViewById(R.id.pgbRecargar_fragment_doc_consultas_programadas);
        pgbLista = view.findViewById(R.id.pgbLista_fragment_doc_consultas_programadas);

        tvTitulo = view.findViewById(R.id.tvTitulo_fragment_doc_consultas_programadas);

        pgbRecargar.setVisibility(View.INVISIBLE);

        imgRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvLista.setAdapter(null);
                imgRecargar.setVisibility(View.GONE);
                pgbRecargar.setVisibility(View.VISIBLE);
                pgbLista.setVisibility(View.VISIBLE);
                getConsultas(view,0,1,0, 0, edtFecha.getText().toString().trim(),edtNombrePaciente.getText().toString().trim());
            }
        });

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
                        FragmentConsultasProgramadas fragmentConsultasProgramadas = new FragmentConsultasProgramadas();
                        //Crea la transaccion
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        //remplazar el nuevo fragmento en el contenedor principal(nav_host_fragment)
                        transaction.replace(R.id.nav_host_fragment, fragmentConsultasProgramadas);
                        //agregar la transaccion a la pila
                        //transaction.addToBackStack(null);
                        // Commit a la transacción
                        transaction.commit();
                    }
                },100);
            }
        });

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        //CARGAR EL LISTVIEW CON EL METODO GETCONSULTAS
        getConsultas(view,0,1,0, 0, edtFecha.getText().toString().trim(), edtNombrePaciente.getText().toString().trim());

        //AGREGAR EVENTO CLICKLISTENER AL LISTVIEW
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //Pausa para que haga la transicion, esto para que se note el efecto de Click sobre el listView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // Crea el nuevo fragmento
                        FragmentDatosConsulta fragmentDatosConsulta = new FragmentDatosConsulta(listConsulta,position);
                        //Crea la transaccion
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        //remplazar el nuevo fragmento en el contenedor principal(nav_host_fragment)
                        transaction.replace(R.id.nav_host_fragment, fragmentDatosConsulta);
                        //agregar la transaccion a la pila
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
                btnBuscar.setBackgroundResource(R.drawable.lupa_negra);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnBuscar.setBackgroundResource(R.drawable.lupa_celeste);

                        //Codigo para logica del boton
                        Toast.makeText(getContext(), "Buscando", Toast.LENGTH_SHORT).show();

                        //CARGAR EL LISTVIEW CON EL METODO GETCONSULTAS
                        getConsultas(view,0,1,0, 0, edtFecha.getText().toString().trim(), edtNombrePaciente.getText().toString().trim());

                    }
                }, 100);
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFecha.setBackgroundResource(R.drawable.calendario_64_2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnFecha.setBackgroundResource(R.drawable.calendario_64_1);

                        //logica
                        fecha(getContext(),edtFecha);
                    }
                },100);
            }
        });

        //FIN CODIGO AGREGADO////////////////////////////////////////////////////
        return view;
    }

    public void fecha(final Context context, final EditText edt){
        int dia,mes, anio;
        final Calendar calendario= Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anio=calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                String fechaSelec=year+"-"+(month+1)+"-"+dayOfMonth;
                try {
                    Date date = formatter.parse(fechaSelec);
                    System.out.println(date);
                    System.out.println(formatter.format(date));
                    edt.setText(formatter.format(date));

                    //CARGAR EL LISTVIEW CON EL METODO GETCONSULTAS
                    getConsultas(view,0,1,0, 0, edtFecha.getText().toString().trim(), edtNombrePaciente.getText().toString().trim());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
                ,anio,mes,dia);
        datePickerDialog.show();
    }

    //METODO PARA CONSUMIR EL WS
    private void getConsultas(final View view, int per, int med, int cod, int cmd, String fec, String ntag) {
        Log.d("JTDebug", "Entra Metodo consulta");
        Call<List<ConsultaModel>> call = servicio.getConsultas(per, med, cod, cmd, fec, ntag);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<ConsultaModel>>() {
            @Override
            public void onResponse(Call<List<ConsultaModel>> call, Response<List<ConsultaModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listConsulta = response.body();
                        Log.d("JTDebug", "Count: " + listConsulta.size());
                        imagenes = new int[listConsulta.size()];
                        codigos = new String[listConsulta.size()];
                        nombres = new String[listConsulta.size()];
                        fechas = new String[listConsulta.size()];
                        horas = new String[listConsulta.size()];

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
                        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");

                        for (int i=0;i<listConsulta.size();i++) {
                            consulta = listConsulta.get(i);
                            imagenes[i]=R.drawable.consulta;
                            codigos[i] = "Código consulta: "+consulta.cme_codigo;
                            nombres[i] = "Paciente: "+consulta.per_nombre;
                            fechas[i] = "Fecha: "+formatoFecha.format(consulta.cme_fecha_hora);
                            horas[i] = "Hora: "+formatoHora.format(consulta.cme_fecha_hora);
                        }
                        adaptadorListView = new AdaptadorListView(getContext(), imagenes, codigos, nombres, fechas, horas);
                        lvLista.setAdapter(adaptadorListView);
                        imgRecargar.setVisibility(View.VISIBLE);
                        pgbRecargar.setVisibility(View.INVISIBLE);
                        pgbLista.setVisibility(View.INVISIBLE);
                    } else {
                        imgRecargar.setVisibility(View.VISIBLE);
                        pgbRecargar.setVisibility(View.INVISIBLE);
                        pgbLista.setVisibility(View.INVISIBLE);
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    imgRecargar.setVisibility(View.VISIBLE);
                    pgbRecargar.setVisibility(View.INVISIBLE);
                    pgbLista.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaModel>> call, Throwable t) {
                imgRecargar.setVisibility(View.VISIBLE);
                pgbRecargar.setVisibility(View.INVISIBLE);
                pgbLista.setVisibility(View.INVISIBLE);
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
