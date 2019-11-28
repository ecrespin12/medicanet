package com.example.medicanet.ui.doctor.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
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

    ConsultaModel item;

    AdaptadorListView adaptadorListView;

    int[] imagenes;
    String[] arr1;
    String[] arr2;
    String[] arr3;
    String[] arr4;

    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<DatosMedicosModel> datosMedicosModels;
    DatosMedicosModel datosMedicosModel;
    List<RecetaModel> recetaModels;
    RecetaModel recetaModel;
    FragmentConsulta fragmentConsulta;

    public FragmentConsulta(ConsultaModel item) {
        this.item=item;
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
                        DialogAgregarCita dialog = new DialogAgregarCita(item);
                        dialog.show(getFragmentManager(), "dialog_admin_ejemplo");
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
                        DialogAgregarDetalleConsulta dialog = new DialogAgregarDetalleConsulta(item,fragmentConsulta);
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
                        DialogAgregarMedicamentoConsulta dialog = new DialogAgregarMedicamentoConsulta(item,fragmentConsulta);
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
                    }
                },100);
            }
        });
        //fin codigo agregado

        return  view;
    }

    //METODO PARA CONSUMIR EL WS
    public void getDetalles() {
        Log.d("JTDebug", "Entra Metodo getDetalles");
        Call<List<DatosMedicosModel>> call = servicio.getDatosMedicos(0,item.cme_codigo);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<DatosMedicosModel>>() {
            @Override
            public void onResponse(Call<List<DatosMedicosModel>> call, Response<List<DatosMedicosModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        datosMedicosModels = response.body();
                        Log.d("JTDebug", "Count: " + datosMedicosModels.size());
                        arr1 = new String[datosMedicosModels.size()];
                        arr2 = new String[datosMedicosModels.size()];

                        for (int i=0;i<datosMedicosModels.size();i++) {
                            datosMedicosModel = datosMedicosModels.get(i);
                            arr1[i] = "Nombre: "+datosMedicosModel.dcm_nombre;
                            arr2[i] = "Descripción: "+datosMedicosModel.dcm_descripcion;
                        }
                        adaptadorListView = new AdaptadorListView(getContext(), null, arr1, arr2, null, null);
                        lvDetalles.setAdapter(adaptadorListView);
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<DatosMedicosModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    //METODO PARA CONSUMIR EL WS
    public void getMedicamentos() {
        Log.d("JTDebug", "Entra Metodo getMedicamentos");
        Call<List<RecetaModel>> call = servicio.getReceta(item.cme_codigo);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<RecetaModel>>() {
            @Override
            public void onResponse(Call<List<RecetaModel>> call, Response<List<RecetaModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        recetaModels = response.body();
                        Log.d("JTDebug", "Count: " + recetaModels.size());
                        arr1 = new String[recetaModels.size()];
                        arr2 = new String[recetaModels.size()];
                        arr3 = new String[recetaModels.size()];

                        for (int i=0;i<recetaModels.size();i++) {
                            recetaModel = recetaModels.get(i);
                            arr1[i] = "Medicamento: "+recetaModel.mdc_nombre;
                            arr2[i] = "Cantidad: "+recetaModel.rme_cantidad+" "+recetaModel.mdc_medida;
                            arr3[i] = "Indicaciones: "+recetaModel.rme_indicaciones;
                        }
                        adaptadorListView = new AdaptadorListView(getContext(), null, arr1, arr2, arr3, null);
                        lvMedicamentos.setAdapter(adaptadorListView);
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<RecetaModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
