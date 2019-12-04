package com.example.medicanet.ui.doctor.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarCita;
import com.example.medicanet.ui.paciente.historialMedico.HistorialMedico;
import java.text.SimpleDateFormat;
import java.util.List;
import clasesResponse.ConsultaModel;

public class FragmentDatosConsulta extends Fragment {

    TextView tvDuiPaciente;
    TextView tvNombresPaciente;
    TextView tvFechaNPaciente;
    TextView tvCorreoPaciente;

    TextView tvNombresDoctor;
    TextView tvCorreoDoctor;

    TextView tvCodigoConsulta;
    TextView tvNombreCentroM;
    TextView tvFechaConsulta;
    TextView tvHoraConsulta;

    Button btnIniciarConsulta;
    Button btnGestionarConsulta;

    ConsultaModel consulta;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");


    public FragmentDatosConsulta(List<ConsultaModel> listConsultas, int indiceListView) {
        this.consulta=listConsultas.get(indiceListView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_datos_consulta, container, false);

        //codigo agregado
        tvDuiPaciente=view.findViewById(R.id.tvDuiPaciente_fragment_doc_datos_consulta);
        tvNombresPaciente=view.findViewById(R.id.tvNombresPaciente_fragment_doc_datos_consulta);
        tvFechaNPaciente=view.findViewById(R.id.tvFechaNPaciente_fragment_doc_datos_consulta);
        tvCorreoPaciente=view.findViewById(R.id.tvCorreoPaciente_fragment_doc_datos_consulta);

        tvNombresDoctor=view.findViewById(R.id.tvNombresDoctor_fragment_doc_datos_consulta);
        tvCorreoDoctor=view.findViewById(R.id.tvCorreoDoctor_fragment_doc_datos_consulta);

        tvCodigoConsulta=view.findViewById(R.id.tvCodigoConsulta_fragment_doc_datos_consulta);
        tvNombreCentroM=view.findViewById(R.id.tvNombreCentroM_fragment_doc_datos_consulta);
        tvFechaConsulta=view.findViewById(R.id.tvFechaConsulta_fragment_doc_datos_consulta);
        tvHoraConsulta=view.findViewById(R.id.tvHoraConsulta_fragment_doc_datos_consulta);

        btnIniciarConsulta=view.findViewById(R.id.btnIniciarConsulta_fragment_doc_datos_consulta);
        btnGestionarConsulta=view.findViewById(R.id.btnGestionarConsulta_fragment_doc_datos_consulta);

        //CARGAR DATOS DE LA CONSULTA SELECCIONADA DESDE EL LISTADO ANTERIOR
        tvDuiPaciente.setText("DUI: "+consulta.per_dui);
        tvNombresPaciente.setText("Nombre: "+consulta.per_nombre);
        tvFechaNPaciente.setText("Fecha N: "+formatoFecha.format(consulta.per_fecha_nace));
        tvCorreoPaciente.setText("Correo: "+consulta.per_correo);

        tvNombresDoctor.setText("Nombre: "+consulta.med_nombre);
        tvCorreoDoctor.setText("Correo: "+consulta.med_correo);

        tvCodigoConsulta.setText("Código de consulta: "+consulta.cme_codigo);
        tvNombreCentroM.setText("Centro medico: "+consulta.cmd_nombre);
        tvFechaConsulta.setText("Fecha: "+formatoFecha.format(consulta.cme_fecha_hora));
        tvHoraConsulta.setText("Hora: "+formatoHora.format(consulta.cme_fecha_hora));

        btnIniciarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIniciarConsulta.setBackgroundResource(R.drawable.boton_redondeado);
                btnIniciarConsulta.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnIniciarConsulta.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnIniciarConsulta.setTextColor(Color.BLACK);
                    }
                }, 100);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            final FragmentConsulta fragmentConsulta = new FragmentConsulta(consulta);

                            // Crea el nuevo fragmento y la transacción.
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.nav_host_fragment, fragmentConsulta);
                            transaction.addToBackStack(null);

                            // Commit a la transacción
                            transaction.commit();
                        }catch (Exception e){
                            System.out.println("error: "+e);
                        }

                    }
                }, 500);
            }
        });

        btnGestionarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGestionarConsulta.setBackgroundResource(R.drawable.boton_redondeado);
                btnGestionarConsulta.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnGestionarConsulta.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnGestionarConsulta.setTextColor(Color.BLACK);
                    }
                }, 100);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnGestionarConsulta.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnGestionarConsulta.setTextColor(Color.BLACK);

                        //CREANDO NUEVO DIALOG Y ENVIANDOLE TRUE PARA GESTIONAR CONSULTA
                        DialogAgregarCita agregarCita = new DialogAgregarCita(consulta, null, true);
                        agregarCita.show(getFragmentManager(),"dialog_doc_agregar_cita");

                    }
                }, 500);
            }
        });

        //fin codigo agregado
        return view;
    }

}
