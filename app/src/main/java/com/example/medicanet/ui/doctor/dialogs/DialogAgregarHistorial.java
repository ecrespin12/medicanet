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
import com.example.medicanet.ui.paciente.historialMedico.HistorialMedico;
import com.example.medicanet.utils.PreferenceUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import clasesResponse.CentroMedicoModel;
import clasesResponse.ConsultaModel;
import clasesResponse.DatosMedicosModel;
import clasesResponse.EntregaMedicamentosModel;
import clasesResponse.HistorialModel;
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
    EditText            edtFecha;
    EditText            edtHora;

    TextView            tvFecha;
    TextView            tvHora;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");

    int codigoTipoHistorial;

    String [] arr1;

    PacientesModel paciente;
    HistorialModel historial;
    boolean gestionando;
    HistorialMedico historialMedico;

    public DialogAgregarHistorial() {

    }

    public DialogAgregarHistorial(PacientesModel paciente, boolean gestionando, HistorialModel historial, HistorialMedico historialMedico) {
        this.paciente=paciente;
        this.gestionando=gestionando;
        this.historial=historial;
        this.historialMedico=historialMedico;
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
        edtFecha=view.findViewById(R.id.edtFecha_dialog_doc_agregar_historial);
        tvFecha=view.findViewById(R.id.tvFecha_dialog_doc_agregar_historial);
        edtHora=view.findViewById(R.id.edthora_dialog_doc_agregar_historial);
        tvHora=view.findViewById(R.id.tvHora_dialog_doc_agregar_historial);

        if (gestionando && PreferenceUtils.getRol(getContext()).equals("doctor")){
            btnEliminar.setVisibility(View.VISIBLE);
            btnGuardar.setText("editar historial");
            tvTituloDialog.setText("Gestionar historial");
            edtHora.setVisibility(View.GONE);
            edtFecha.setVisibility(View.GONE);
            tvFecha.setVisibility(View.GONE);
            tvHora.setVisibility(View.GONE);
        }
        if (!gestionando && PreferenceUtils.getRol(getContext()).equals("doctor")){
            btnEliminar.setVisibility(View.GONE);
            edtHora.setVisibility(View.GONE);
            edtFecha.setVisibility(View.GONE);
            tvFecha.setVisibility(View.GONE);
            tvHora.setVisibility(View.GONE);
        }else if(gestionando && !PreferenceUtils.getRol(getContext()).equals("doctor")){
            btnGuardar.setVisibility(View.INVISIBLE);
            btnEliminar.setVisibility(View.GONE);
            spTipo.setEnabled(false);
            edtDescripcion.setEnabled(false);
            tvTituloDialog.setText("Información del historial");

            edtHora.setVisibility(View.VISIBLE);
            edtHora.setEnabled(false);
            tvHora.setVisibility(View.VISIBLE);
            edtFecha.setVisibility(View.VISIBLE);
            edtFecha.setEnabled(false);
            tvFecha.setVisibility(View.VISIBLE);
        }

        if (historial!=null){
            edtDescripcion.setText(historial.hme_descripcion);
            edtFecha.setText(formatoFecha.format(historial.hme_fecha_crea));
            edtHora.setText(formatoHora.format(historial.hme_fecha_crea));
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
                        int thm = codigoTipoHistorial;
                        String des = edtDescripcion.getText().toString().trim();

                        //LLAMAR AL WS
                        if (gestionando){
                            Toast.makeText(getContext(), "Editando...", Toast.LENGTH_SHORT).show();
                            postEditarHistorial(historial.hme_codigo,per,thm,des);
                        }else{
                            Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                            postAgregarHistorial(0,per,thm,des);
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
                        Toast.makeText(getContext(), "Elimiando...", Toast.LENGTH_SHORT).show();
                        postEliminarHistorial(historial.hme_codigo);

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

    public void postAgregarHistorial(int cod, int per, int thm, String des){

        Log.d("JTDebug", "Entra Metodo postAgregarHistorial");
        Call<Integer> call = servicio.postAgregarHistorial(cod,per,thm,des);
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
                            historialMedico.getHistorial();
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

    public void postEditarHistorial(int cod, int per, int thm, String des){

        Log.d("JTDebug", "Entra Metodo postAgregarHistorial");
        Call<Boolean> call = servicio.postEditarHistorial(cod,per,thm,des);
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
                            historialMedico.getHistorial();
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

                        if (historial!=null){
                            for (int i=0 ; i<listTiposHistorial.size() ; i++){
                                tipoHistorial=listTiposHistorial.get(i);
                                if (tipoHistorial.thm_codigo==historial.hme_codthm){
                                    spTipo.setSelection(i);
                                    break;
                                }
                            }
                        }

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

    public void postEliminarHistorial(int cod){

        Log.d("JTDebug", "Entra Metodo postEliminarHistorial");
        Call<Boolean> call = servicio.postEliminarHistorial(cod);
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
                            historialMedico.getHistorial();
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
