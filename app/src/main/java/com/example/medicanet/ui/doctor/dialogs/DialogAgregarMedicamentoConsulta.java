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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorSpinner;
import com.example.medicanet.ui.doctor.fragments.FragmentConsulta;
import java.util.List;
import clasesResponse.ConsultaModel;
import clasesResponse.MedicamentosModel;
import clasesResponse.RecetaModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAgregarMedicamentoConsulta extends DialogFragment {

    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    //###########################################################

    List<MedicamentosModel> listMedicamentos;
    MedicamentosModel medicamento;

    ImageView btnCerrar;
    Spinner spTipoMedicamento;
    EditText edtCantidad,edtIndicaciones;
    Button btnGuardar;
    Button btnEliminar;
    TextView tvTituloMedida;
    TextView tvTituloDialog;

    int codigoMedicamento=0;
    int codigoConsulta=0;

    String [] arr1;
    String [] arr2;

    ConsultaModel consultaModel;
    FragmentConsulta fragmentConsulta;
    RecetaModel receta;

    boolean gestionando;

    public DialogAgregarMedicamentoConsulta(ConsultaModel consultaModel, FragmentConsulta fragmentConsulta, RecetaModel receta) {
        this.consultaModel=consultaModel;
        this.codigoConsulta=consultaModel.cme_codigo;
        this.setCancelable(false);
        this.fragmentConsulta=fragmentConsulta;
        this.receta=receta;
        if (receta!=null){
            gestionando=true;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_doc_agregar_medicamento_consulta, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnCerrar = view.findViewById(R.id.imgCerrar_doc_modal_agregar_medicamento);
        btnGuardar = view.findViewById(R.id.btnGuardar_doc_modal_agregar_medicamento);
        btnEliminar = view.findViewById(R.id.btnEliminar_doc_modal_agregar_medicamento);
        spTipoMedicamento = view.findViewById(R.id.spTipo_doc_modal_agregar_medicamento);
        edtCantidad = view.findViewById(R.id.edtCantidad_doc_modal_agregar_medicamento);
        edtIndicaciones = view.findViewById(R.id.edtIndicaciones_doc_modal_agregar_medicamento);
        tvTituloMedida = view.findViewById(R.id.tvTituloDeMedida_doc_modal_agregar_medicamento);

        tvTituloDialog = view.findViewById(R.id.tvTitulo_doc_modal_agregar_medicamento_consulta);

        btnEliminar.setVisibility(View.GONE);

        if (gestionando){
            tvTituloDialog.setText("Gestionar medicamento");
            btnGuardar.setText("editar medicamento");
            btnEliminar.setVisibility(View.VISIBLE);
            edtCantidad.setText(receta.mdc_medida);
            edtIndicaciones.setText(receta.rme_indicaciones);
        }

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));
        getMedicamentos();

        spTipoMedicamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                medicamento=listMedicamentos.get(i);
                tvTituloMedida.setText("Cantidad segun unidad de medida ("+medicamento.mdc_medida+")");
                codigoMedicamento=medicamento.mdc_codigo;
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

                        String indicaciones = edtIndicaciones.getText().toString().trim();
                        double cantidad = Double.valueOf(edtCantidad.getText().toString().trim());

                        if (gestionando){
                            Toast.makeText(getContext(), "Editando...", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }else{
                            Toast.makeText(getContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                            postAgregarMedicamentoConsulta(codigoMedicamento,codigoConsulta,indicaciones,cantidad);
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

                        Toast.makeText(getContext(), "Eliminando...", Toast.LENGTH_SHORT).show();
                        dismiss();
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

        return  view;
    }

    //METODO PARA CONSUMIR EL WS
    private void getMedicamentos() {
        Log.d("JTDebug", "Entra Metodo getmedicamentos");
        Call<List<MedicamentosModel>> call = servicio.getMedicamentos(0,"","","");
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<MedicamentosModel>>() {
            @Override
            public void onResponse(Call<List<MedicamentosModel>> call, Response<List<MedicamentosModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listMedicamentos = response.body();
                        Log.d("JTDebug", "Count: " + listMedicamentos.size());
                        arr1=new String[listMedicamentos.size()];
                        arr2=new String[listMedicamentos.size()];

                        for (int i=0;i<listMedicamentos.size();i++) {
                            medicamento = listMedicamentos.get(i);
                            arr1[i] = medicamento.mdc_nombre;
                            arr2[i] = "Medida: "+medicamento.mdc_medida;
                        }
                        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(getContext(), null, arr1, arr2, null, null);
                        spTipoMedicamento.setAdapter(adaptadorSpinner);

                        if (gestionando){
                            for (int i=0 ; i<listMedicamentos.size() ; i++){
                                medicamento=listMedicamentos.get(i);
                                if (receta.mdc_codigo==medicamento.mdc_codigo){
                                    spTipoMedicamento.setSelection(i);
                                    break;
                                }
                            }
                        }
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<MedicamentosModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void postAgregarMedicamentoConsulta(int mdc, int cme,  String ind, double can){

        Log.d("JTDebug", "Entra Metodo postAgregarMedicamentoConsulta");
        Call<Integer> call = servicio.postAgregarReceta(mdc,cme,ind,can);
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
                        if(resp==1){
                            Toast.makeText(getContext(),"Operacion realizada exitosamente",Toast.LENGTH_SHORT).show();
                            fragmentConsulta.getMedicamentos();
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

}
