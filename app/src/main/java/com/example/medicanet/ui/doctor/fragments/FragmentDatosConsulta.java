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
    Button btnVerHistorial;

    ConsultaModel item;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");


    public FragmentDatosConsulta(List<ConsultaModel> resp, int indiceListView) {
        this.item=resp.get(indiceListView);
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
        btnVerHistorial=view.findViewById(R.id.btnVerHistorial_fragment_doc_datos_consulta);

        //CARGAR DATOS DE LA CONSULTA SELECCIONADA DESDE EL LISTADO ANTERIOR
        tvDuiPaciente.setText("DUI: "+item.per_dui);
        tvNombresPaciente.setText("Nombre: "+item.per_nombre);
        tvFechaNPaciente.setText("Fecha N: "+formatoFecha.format(item.per_fecha_nace));
        tvCorreoPaciente.setText("Correo: "+item.per_correo);

        tvNombresDoctor.setText("Nombre: "+item.med_nombre);
        tvCorreoDoctor.setText("Correo: "+item.med_correo);

        tvCodigoConsulta.setText("Código de consulta: "+item.cme_codigo);
        tvNombreCentroM.setText("Centro medico: "+item.cmd_nombre);
        tvFechaConsulta.setText("Fecha: "+formatoFecha.format(item.cme_fecha_hora));
        tvHoraConsulta.setText("Hora: "+formatoHora.format(item.cme_fecha_hora));

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
                        final FragmentConsulta fragmentConsulta = new FragmentConsulta();

                        // Crea el nuevo fragmento y la transacción.
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, fragmentConsulta);
                        transaction.addToBackStack(null);

                        // Commit a la transacción
                        transaction.commit();
                    }
                }, 500);
            }
        });

        btnVerHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnVerHistorial.setBackgroundResource(R.drawable.boton_redondeado);
                btnVerHistorial.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnVerHistorial.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnVerHistorial.setTextColor(Color.BLACK);
                    }
                }, 100);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnVerHistorial.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnVerHistorial.setTextColor(Color.BLACK);

                        //AQUI COMIENZA LA LOGICA DE DEL BOTON
                        Toast.makeText(getContext(), "Ver historial medico", Toast.LENGTH_SHORT).show();
                    }
                }, 500);
            }
        });

        //fin codigo agregado
        return view;
    }

}
