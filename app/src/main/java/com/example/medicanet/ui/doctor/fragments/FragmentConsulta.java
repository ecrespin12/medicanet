package com.example.medicanet.ui.doctor.fragments;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarCita;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarDetalleConsulta;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarMedicamentoConsulta;
import java.util.List;
import clasesResponse.ConsultaModel;
import clasesResponse.DatosMedicosModel;
import clasesResponse.RecetaModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentConsulta extends Fragment {

    Button btnDetalles;
    Button btnAgregarCita;
    Button btnMedicamentos;
    Button btnTerminar;

    ListView lvDetalles;
    ListView lvMedicamentos;

    ImageView imgRecargarDetalles,imgRecargarMedicamentos;
    ProgressBar pgbRecargarDetalles,pgbRecargarMedicamentos;

    AdaptadorListView adaptadorListView;

    String[] arr1;
    String[] arr2;
    String[] arr3;

    //VARIABLES PARA EL WS
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    ////////////////////////////////////////////////////////////////////

    ConsultaModel consulta;
    List<DatosMedicosModel> listDatosMedicos;
    DatosMedicosModel datoMedico;
    List<RecetaModel> listRecetas;
    RecetaModel receta;
    FragmentConsulta fragmentConsulta;

    public FragmentConsulta(ConsultaModel consulta) {
        this.consulta=consulta;
        fragmentConsulta=this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_consulta, container, false);

        //codigo agregado
        btnDetalles=view.findViewById(R.id.btnDetalles_fragment_doc_consulta);
        btnAgregarCita=view.findViewById(R.id.btnAgregarCita_fragment_doc_consulta);
        btnMedicamentos=view.findViewById(R.id.btnMedicamentos_fragment_doc_consulta);
        btnTerminar=view.findViewById(R.id.btnTerminar_fragment_doc_consulta);

        lvDetalles=view.findViewById(R.id.lvDetalles_fragment_doc_consulta);
        lvMedicamentos=view.findViewById(R.id.lvMedicamentos_fragment_doc_consulta);

        imgRecargarDetalles=view.findViewById(R.id.imgRecargarDetalles_fragment_doc_consulta);
        imgRecargarMedicamentos=view.findViewById(R.id.imgRecargarMedicamentos_fragment_doc_consulta);

        pgbRecargarDetalles=view.findViewById(R.id.pgbRecargarDetalles_fragment_doc_consulta);
        pgbRecargarMedicamentos=view.findViewById(R.id.pgbRecargarMedicamentos_fragment_doc_consulta);

        pgbRecargarDetalles.setVisibility(View.INVISIBLE);
        pgbRecargarMedicamentos.setVisibility(View.INVISIBLE);

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        //CARGAR EL LISTVIEW CON EL WS
        getDetalles();
        getMedicamentos();

        btnAgregarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAgregarCita.setBackgroundResource(R.drawable.nueva_consulta_2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnAgregarCita.setBackgroundResource(R.drawable.nueva_consulta_1);

                        //crear y mostrar un Dialog
                        DialogAgregarCita dialog = new DialogAgregarCita(consulta,null, false);
                        dialog.show(getFragmentManager(), "dialog_doc_agregar_cita");
                    }
                },100);
            }
        });

        btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDetalles.setBackgroundResource(R.drawable.detalle_consulta_2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnDetalles.setBackgroundResource(R.drawable.detalle_consulta_1);

                        //Crear y mostrar un Dialog
                        DialogAgregarDetalleConsulta dialog = new DialogAgregarDetalleConsulta(consulta,fragmentConsulta,null);
                        dialog.show(getFragmentManager(),"dialog_doc_agregar_detalle");
                    }
                },100);
            }
        });
        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMedicamentos.setBackgroundResource(R.drawable.medicamento_2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnMedicamentos.setBackgroundResource(R.drawable.medicamento_1);

                        //Crear y mostrar un Dialog
                        DialogAgregarMedicamentoConsulta dialog = new DialogAgregarMedicamentoConsulta(consulta,fragmentConsulta,null);
                        dialog.show(getFragmentManager(),"dialog_doc_agregar_medicamento");
                    }
                },100);
            }
        });
        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTerminar.setBackgroundResource(R.drawable.boton_redondeado);
                btnTerminar.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnTerminar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnTerminar.setTextColor(Color.BLACK);

                        //Terminar
                        Toast.makeText(getContext(),"Terminando consulta",Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }
                },100);
            }
        });

        imgRecargarDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvDetalles.setAdapter(null);
                imgRecargarDetalles.setVisibility(View.GONE);
                pgbRecargarDetalles.setVisibility(View.VISIBLE);
                getDetalles();
            }
        });

        imgRecargarMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvMedicamentos.setAdapter(null);
                imgRecargarMedicamentos.setVisibility(View.GONE);
                pgbRecargarMedicamentos.setVisibility(View.VISIBLE);
                getMedicamentos();
            }
        });

        //AGREGAR EVENTO CLICKLISTENER AL LISTVIEW
        lvDetalles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //Pausa para que haga la transicion, esto para que se note el efecto de Click sobre el listView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //logica
                        datoMedico=listDatosMedicos.get(position);
                        //Crear y mostrar un Dialog
                        DialogAgregarDetalleConsulta dialog = new DialogAgregarDetalleConsulta(consulta,fragmentConsulta,datoMedico);
                        dialog.show(getFragmentManager(),"dialog_doc_agregar_detalle");

                    }
                },200);
            }
        });

        //AGREGAR EVENTO CLICKLISTENER AL LISTVIEW
        lvMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //Pausa para que haga la transicion, esto para que se note el efecto de Click sobre el listView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //logica
                        receta=listRecetas.get(position);
                        //Crear y mostrar un Dialog
                        DialogAgregarMedicamentoConsulta dialog = new DialogAgregarMedicamentoConsulta(consulta,fragmentConsulta,receta);
                        dialog.show(getFragmentManager(),"dialog_doc_agregar_medicamento");

                    }
                },200);
            }
        });

        //fin codigo agregado

        return  view;
    }

    //METODO PARA CONSUMIR EL WS
    public void getDetalles() {
        Log.d("JTDebug", "Entra Metodo getDetalles");
        Call<List<DatosMedicosModel>> call = servicio.getDatosMedicos(consulta.cme_codper,consulta.cme_codigo);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<DatosMedicosModel>>() {
            @Override
            public void onResponse(Call<List<DatosMedicosModel>> call, Response<List<DatosMedicosModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listDatosMedicos = response.body();
                        Log.d("JTDebug", "Count: " + listDatosMedicos.size());
                        arr1 = new String[listDatosMedicos.size()];
                        arr2 = new String[listDatosMedicos.size()];

                        for (int i=0;i<listDatosMedicos.size();i++) {
                            datoMedico = listDatosMedicos.get(i);
                            arr1[i] = "Nombre: "+datoMedico.dcm_nombre;
                            arr2[i] = "Descripción: "+datoMedico.dcm_descripcion;
                        }
                        adaptadorListView = new AdaptadorListView(getContext(), null, arr1, arr2, null, null);
                        lvDetalles.setAdapter(adaptadorListView);
                        imgRecargarDetalles.setVisibility(View.VISIBLE);
                        pgbRecargarDetalles.setVisibility(View.INVISIBLE);
                    } else {
                        imgRecargarDetalles.setVisibility(View.VISIBLE);
                        pgbRecargarDetalles.setVisibility(View.INVISIBLE);
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    imgRecargarDetalles.setVisibility(View.VISIBLE);
                    pgbRecargarDetalles.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<DatosMedicosModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                imgRecargarDetalles.setVisibility(View.VISIBLE);
                pgbRecargarDetalles.setVisibility(View.INVISIBLE);
                t.printStackTrace();
            }
        });
    }

    //METODO PARA CONSUMIR EL WS
    public void getMedicamentos() {
        Log.d("JTDebug", "Entra Metodo getMedicamentos");
        Call<List<RecetaModel>> call = servicio.getReceta(consulta.cme_codigo);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<RecetaModel>>() {
            @Override
            public void onResponse(Call<List<RecetaModel>> call, Response<List<RecetaModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listRecetas = response.body();
                        Log.d("JTDebug", "Count: " + listRecetas.size());
                        arr1 = new String[listRecetas.size()];
                        arr2 = new String[listRecetas.size()];
                        arr3 = new String[listRecetas.size()];

                        for (int i=0;i<listRecetas.size();i++) {
                            receta = listRecetas.get(i);
                            arr1[i] = "Medicamento: "+receta.mdc_nombre;
                            arr2[i] = "Cantidad: "+receta.rme_cantidad+" "+receta.mdc_medida;
                            arr3[i] = "Indicaciones: "+receta.rme_indicaciones;
                        }
                        adaptadorListView = new AdaptadorListView(getContext(), null, arr1, arr2, arr3, null);
                        lvMedicamentos.setAdapter(adaptadorListView);
                        imgRecargarMedicamentos.setVisibility(View.VISIBLE);
                        pgbRecargarMedicamentos.setVisibility(View.INVISIBLE);
                    } else {
                        imgRecargarMedicamentos.setVisibility(View.VISIBLE);
                        pgbRecargarMedicamentos.setVisibility(View.INVISIBLE);
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    imgRecargarMedicamentos.setVisibility(View.VISIBLE);
                    pgbRecargarMedicamentos.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<RecetaModel>> call, Throwable t) {
                imgRecargarMedicamentos.setVisibility(View.VISIBLE);
                pgbRecargarMedicamentos.setVisibility(View.INVISIBLE);
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
