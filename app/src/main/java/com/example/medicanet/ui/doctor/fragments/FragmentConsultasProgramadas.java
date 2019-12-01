package com.example.medicanet.ui.doctor.fragments;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.AdaptadorListViewCargando;
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

    public static String keyImg = "img";
    public static String keyCodigo = "codigo";
    public static String keyNombre = "nombre";
    public static String keyFecha = "fecha";
    public static String keyHoras = "hora";

    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<ConsultaModel> resp;
    ConsultaModel item;

    EditText edtFecha;
    EditText edtNombrePaciente;
    Button btnBuscar;
    Button btnFecha;

    ImageView imgRecargar;

    ProgressBar pgbRecargar;

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
        lvLista.setAdapter(new AdaptadorListViewCargando(getContext()));

        imgRecargar = view.findViewById(R.id.imgRecargar_fragment_doc_consultas_programadas);
        pgbRecargar = view.findViewById(R.id.pgbRecargar_fragment_doc_consultas_programadas);

        pgbRecargar.setVisibility(View.INVISIBLE);

        imgRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvLista.setAdapter(new AdaptadorListViewCargando(getContext()));
                imgRecargar.setVisibility(View.GONE);
                pgbRecargar.setVisibility(View.VISIBLE);
                getConsultas(view,0,1,0);
            }
        });

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        //CARGAR EL LISTVIEW CON EL METODO GETCONSULTAS
        getConsultas(view,0,1,0);

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
                        FragmentDatosConsulta fragmentDatosConsulta = new FragmentDatosConsulta(resp,position);
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
                btnBuscar.setBackgroundResource(R.drawable.lupa_negra);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnBuscar.setBackgroundResource(R.drawable.lupa_celeste);

                        //Codigo para logica del boton
                        Toast.makeText(getContext(), "Buscando", Toast.LENGTH_SHORT).show();

                        //COMENTARIAR AQUI SI SE BUSCA POR NOMBRE Y FECHA
                        String nom="";//numero inventado xd
                        nom=edtNombrePaciente.getText().toString();

                        //getConsultas(view,0,0,codConsulta);
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
                        Metodos.fecha(getContext(),edtFecha);
                    }
                },100);
            }
        });

        //FIN CODIGO AGREGADO////////////////////////////////////////////////////
        return view;
    }

    public void fecha( final EditText edt){
        int dia,mes, anio;
        String fecha="";
        final Calendar calendario= Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anio=calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
                String fechaSelec=dayOfMonth+"-"+(month+1)+"-"+year;
                try {
                    Date date = formatter.parse(fechaSelec);
                    System.out.println(date);
                    System.out.println(formatter.format(date));
                    edt.setText(formatter.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
                ,anio,mes,dia);
        datePickerDialog.show();
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
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());
                        imagenes = new int[resp.size()];
                        codigos = new String[resp.size()];
                        nombres = new String[resp.size()];
                        fechas = new String[resp.size()];
                        horas = new String[resp.size()];

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
                        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");

                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            imagenes[i]=R.drawable.medicanet1;
                            codigos[i] = "Código consulta: "+item.cme_codigo;
                            nombres[i] = "Paciente: "+item.per_nombre;
                            fechas[i] = "Fecha: "+formatoFecha.format(item.cme_fecha_hora);
                            horas[i] = "Hora: "+formatoHora.format(item.cme_fecha_hora);
                        }
                        adaptadorListView = new AdaptadorListView(getContext(), imagenes, codigos, nombres, fechas, horas);
                        lvLista.setAdapter(adaptadorListView);
                        imgRecargar.setVisibility(View.VISIBLE);
                        pgbRecargar.setVisibility(View.INVISIBLE);
                    } else {
                        imgRecargar.setVisibility(View.VISIBLE);
                        pgbRecargar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(),"No se pudieron cargar las consultas",Toast.LENGTH_SHORT).show();
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    imgRecargar.setVisibility(View.VISIBLE);
                    pgbRecargar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(),"No se pudieron cargar las consultas",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaModel>> call, Throwable t) {
                imgRecargar.setVisibility(View.VISIBLE);
                pgbRecargar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(),"No se pudieron cargar las consultas",Toast.LENGTH_SHORT).show();
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
