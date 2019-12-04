package com.example.medicanet.ui.doctor.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
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
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.AdaptadorSpinner;
import com.example.medicanet.metodos.Metodos;
import java.text.SimpleDateFormat;
import java.util.List;
import clasesResponse.CentroMedicoModel;
import clasesResponse.ConsultaModel;
import clasesResponse.DatosMedicosModel;
import clasesResponse.PacientesModel;
import clasesResponse.RecetaModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAgregarCita extends DialogFragment {

    //VARIABLES DE WS
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    /////////////////////////////////////////////////////////
    List<CentroMedicoModel> listCentros;
    CentroMedicoModel centroMedico;

    Spinner             spCentro;

    EditText            edtFecha;
    EditText            edtHora;

    ImageView           btnCerrar;

    Button              btnGuardar;
    Button              btnEliminar;

    ImageButton         btnFecha;
    ImageButton         btnHora;

    TextView            tvTituloDialog;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");

    int codigoCentroMedico;

    String [] arr1;
    int cantidadDeDetalles;
    boolean detalles=true;
    int cantidadDeMedicamentos;
    boolean medicamentos=true;
    List<DatosMedicosModel> datosMedicos;
    List<RecetaModel> listRecetas;

    ConsultaModel consulta;
    PacientesModel paciente;
    boolean gestionando;
    int codPer;

    public DialogAgregarCita(ConsultaModel consulta, PacientesModel paciente, boolean gestionando) {
        this.consulta=consulta;
        this.paciente=paciente;
        this.gestionando=gestionando;
        if (consulta!=null){
            codPer=consulta.cme_codper;
        }else{
            codPer=paciente.per_codigo;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doc_agregar_cita, container, false);

        //CODIGO AGREGADO

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, getContext().getResources().getString(R.string.token));

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        //Enlazando vistas
        spCentro = view.findViewById(R.id.spTipo_dialog_doc_agregar_cita);
        edtFecha=view.findViewById(R.id.edtFecha_dialog_doc_agregar_cita);
        edtHora=view.findViewById(R.id.edtHora_dialog_doc_agregar_cita);
        btnCerrar=view.findViewById(R.id.imgCerrar_dialog_doc_agregar_cita);
        btnGuardar=view.findViewById(R.id.btnGuardar_doc_modal_agregar_cita);
        btnEliminar=view.findViewById(R.id.btnEliminar_doc_modal_agregar_cita);
        btnFecha=view.findViewById(R.id.btnFecha_dialog_doc_agregar_cita);
        btnHora=view.findViewById(R.id.btnHora_dialog_doc_agregar_cita);
        tvTituloDialog=view.findViewById(R.id.tvTitulo_dialog_doc_agregar_cita);

        edtHora.setEnabled(false);
        edtFecha.setEnabled(false);

        btnEliminar.setVisibility(View.GONE);

        if (gestionando){
            btnEliminar.setVisibility(View.VISIBLE);
            btnGuardar.setText("editar consulta");
            tvTituloDialog.setText("Gestionar consulta");
            edtFecha.setText(formatoFecha.format(consulta.cme_fecha_hora));
            edtHora.setText(formatoHora.format(consulta.cme_fecha_hora));
        }

        getCentros();

        spCentro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                centroMedico=listCentros.get(i);
                codigoCentroMedico=centroMedico.cmd_codigo;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHora.setBackgroundResource(R.drawable.alarm_negro);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnHora.setBackgroundResource(R.drawable.alarm_celeste);

                        //logica
                        Metodos.hora(getContext(),edtHora);
                    }
                },100);
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


                        int per = codPer;
                        int med = 1; // codigo del medico logeado
                        int cmd = codigoCentroMedico;
                        String fecha=edtFecha.getText().toString().trim();
                        String hora=edtHora.getText().toString().trim();
                        String fechaHora = fecha+" "+hora;

                        //LLAMAR AL WS
                        if (gestionando){
                            Toast.makeText(getContext(), "Editando...", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }else{
                            Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                            postAgregarConsulta(per, med, cmd, fechaHora);
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

                getDetalles(consulta.cme_codigo);
                getMedicamentos(consulta.cme_codigo);



                System.out.println(cantidadDeDetalles);
                System.out.println(cantidadDeMedicamentos);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnEliminar.setBackgroundResource(R.drawable.boton_style_modal);
                        btnEliminar.setTextColor(Color.WHITE);


                        //logica
                        if (cantidadDeDetalles>0 || detalles==true){
                            Toast.makeText(getContext(),"No se elimino por que tiene "+cantidadDeDetalles+" detalles agregados",Toast.LENGTH_LONG).show();
                            System.out.println("CANTIDAD DE DETALLES: "+cantidadDeDetalles);
                            return;
                        }

                        if (cantidadDeMedicamentos>0 || medicamentos==true){
                            Toast.makeText(getContext(),"No se elimino por que tiene "+cantidadDeMedicamentos+" medicamentos agregados",Toast.LENGTH_LONG).show();
                            System.out.println("CANTIDAD DE MEDICAMENTOS: "+cantidadDeMedicamentos);
                            return;
                        }

                        if (cantidadDeDetalles==0 && detalles==false && cantidadDeMedicamentos==0 && medicamentos==false){
                            Toast.makeText(getContext(), "Eliminando...", Toast.LENGTH_SHORT).show();
                            postEliminarConsulta(consulta.cme_codigo);
                        }

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
    private void getCentros() {

        Log.d("JTDebug", "Entra Metodo getCentros");
        Call<List<CentroMedicoModel>> call = servicio.getCentroMedico(0);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<CentroMedicoModel>>() {
            @Override
            public void onResponse(Call<List<CentroMedicoModel>> call, Response<List<CentroMedicoModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listCentros = response.body();
                        Log.d("JTDebug", "Count: " + listCentros.size());

                        arr1=new String[listCentros.size()];

                        for (int i=0;i<listCentros.size();i++) {
                            centroMedico = listCentros.get(i);
                            arr1[i] = centroMedico.cmd_nombre;
                        }
                        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(getContext(), null, arr1, null, null, null);
                        spCentro.setAdapter(adaptadorSpinner);

                        if (gestionando){
                            for (int i=0 ; i<listCentros.size() ; i++){
                                centroMedico=listCentros.get(i);
                                if (centroMedico.cmd_codigo==consulta.cmd_codigo){
                                    spCentro.setSelection(i);
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
            public void onFailure(Call<List<CentroMedicoModel>> call, Throwable t) {
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

    public void getDetalles(int cme) {

        detalles=true;
        Log.d("JTDebug", "Entra Metodo getDetalles");
        Call<List<DatosMedicosModel>> call = servicio.getDatosMedicos(0,cme);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<DatosMedicosModel>>() {
            @Override
            public void onResponse(Call<List<DatosMedicosModel>> call, Response<List<DatosMedicosModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        datosMedicos = response.body();
                        cantidadDeDetalles=datosMedicos.size();
                        if (cantidadDeDetalles==0){
                            detalles=false;
                        }
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

    public void getMedicamentos(int cme) {
        medicamentos=true;
        Log.d("JTDebug", "Entra Metodo getMedicamentos");
        Call<List<RecetaModel>> call = servicio.getReceta(cme);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<RecetaModel>>() {
            @Override
            public void onResponse(Call<List<RecetaModel>> call, Response<List<RecetaModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listRecetas = response.body();
                        cantidadDeMedicamentos=listRecetas.size();
                        if (cantidadDeMedicamentos==0){
                            medicamentos=false;
                        }
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
