package com.example.medicanet.ui.doctor.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorSpinner;
import com.example.medicanet.metodos.Metodos;

import java.text.SimpleDateFormat;
import java.util.List;

import clasesResponse.CentroMedicoModel;
import clasesResponse.ConsultaModel;
import clasesResponse.DatosMedicosModel;
import clasesResponse.EntregaMedicamentosModel;
import clasesResponse.PacientesModel;
import clasesResponse.RecetaModel;
import clasesResponse.TipoHistorialModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAgregarHistorial extends DialogFragment {

    //VARIABLES DE WS
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    /////////////////////////////////////////////////////////
    List<TipoHistorialModel> listTiposHistorial;
    TipoHistorialModel tipoHistorial;

    Spinner             spTipo;

    ImageView           btnCerrar;

    Button              btnGuardar;
    Button              btnEliminar;

    TextView            tvTituloDialog;

    EditText            edtDescripcion;

    int codigoTipoHistorial;

    String [] arr1;

    PacientesModel paciente;
    boolean gestionando;

    public DialogAgregarHistorial() {

    }

    public DialogAgregarHistorial( PacientesModel paciente, boolean gestionando) {
        this.paciente=paciente;
        this.gestionando=gestionando;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doc_agregar_historial, container, false);

        //CODIGO AGREGADO

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, getContext().getResources().getString(R.string.token));

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        //Enlazando vistas
        spTipo = view.findViewById(R.id.spTipo_dialog_doc_agregar_historial);
        btnCerrar=view.findViewById(R.id.imgCerrar_dialog_doc_agregar_historial);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_historial);
        btnEliminar=view.findViewById(R.id.btnEliminar_doc_modal_agregar_historial);
        tvTituloDialog=view.findViewById(R.id.tvTitulo_dialog_doc_agregar_historial);
        edtDescripcion=view.findViewById(R.id.edtDescripcion_doc_modal_agregar_historial);

        btnEliminar.setVisibility(View.GONE);

        if (gestionando){
            btnEliminar.setVisibility(View.VISIBLE);
            btnGuardar.setText("editar historial");
            tvTituloDialog.setText("Gestionar historial");
        }else {

        }

        getTiposHistorial();

        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoHistorial=listTiposHistorial.get(i);
                codigoTipoHistorial=tipoHistorial.thm_codigo;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGuardar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                btnGuardar.setTextColor(Color.BLACK);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnGuardar.setBackgroundResource(R.drawable.boton_style_modal);
                        btnGuardar.setTextColor(Color.WHITE);

                        //logica


                        int per = paciente.per_codigo;
                        int cmd = codigoTipoHistorial;

                        //LLAMAR AL WS
                        if (gestionando){
                            Toast.makeText(getContext(), "Editando...", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }else{
                            Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                            //postAgregarConsulta(per, med, cmd, fechaHora);
                        }


                    }
                },100);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEliminar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                btnEliminar.setTextColor(Color.BLACK);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnEliminar.setBackgroundResource(R.drawable.boton_style_modal);
                        btnEliminar.setTextColor(Color.WHITE);


                        //logica
                        //En el metodo getDetalles llamare al metodo getMedicamentos
                        // y en el metodo getMedicamentos llamare al de Eliminar

                        //getDetalles(consulta.cme_codigo);

                    }
                },100);
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCerrar.setImageResource(R.drawable.eliminar2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnCerrar.setImageResource(R.drawable.eliminar1);

                        //logica
                        dismiss();
                    }
                },100);

            }
        });

        return view;
    }

    //METODO PARA CONSUMIR EL WS
    private void getTiposHistorial() {

        Log.d("JTDebug", "Entra Metodo getTiposHistorial");
        Call<List<TipoHistorialModel>> call = servicio.getTiposHistorial(0,"");
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<TipoHistorialModel>>() {
            @Override
            public void onResponse(Call<List<TipoHistorialModel>> call, Response<List<TipoHistorialModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listTiposHistorial = response.body();
                        Log.d("JTDebug", "Count: " + listTiposHistorial.size());

                        arr1=new String[listTiposHistorial.size()];

                        for (int i=0;i<listTiposHistorial.size();i++) {
                            tipoHistorial = listTiposHistorial.get(i);
                            arr1[i] = tipoHistorial.thm_nombre;
                        }
                        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(getContext(), null, arr1, null, null, null);
                        spTipo.setAdapter(adaptadorSpinner);

                        //if (gestionando){
                          //  for (int i=0 ; i<listTiposHistorial.size() ; i++){
                            //    centroMedico=listCentros.get(i);
                              //  if (centroMedico.cmd_codigo==consulta.cmd_codigo){
                                //    spCentro.setSelection(i);
                                  //  break;
                                //}
                            //}
                        //}

                    } else {Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<TipoHistorialModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void postAgregarConsulta(int per, int med, int cmd, String fec){

        Log.d("JTDebug", "Entra Metodo postAgregarConsulta");
        Call<Integer> call = servicio.postAgregarConsulta(per,med,cmd,fec);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        Integer resp ;
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp);
                        if(resp>1){
                            Toast.makeText(getContext(),"Operacion realizada exitosamente",Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                        if(resp==0){
                            Toast.makeText(getContext(),"Operacion no realizada",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void postEliminarConsulta(int cod){

        Log.d("JTDebug", "Entra Metodo postEliminarConsulta");
        Call<Boolean> call = servicio.postEliminarConsulta(cod);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        Boolean resp ;
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp);
                        if(resp==true){
                            Toast.makeText(getContext(),"Operacion realizada exitosamente",Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                        if(resp==false){
                            Toast.makeText(getContext(),"Operacion no realizada",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
