package com.example.medicanet.ui.doctor.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarCita;

import java.text.SimpleDateFormat;

import clasesResponse.PacientesModel;

public class FragmentDatosPaciente extends Fragment {

    View view;

    TextView tvCodigo;
    TextView tvNombre;
    TextView tvFechaN;
    TextView tvCorreo;
    TextView tvEstado;
    TextView tvDui;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");

    Button btnVerHistorial;
    Button btnProgramarConsulta;

    PacientesModel item;

    public FragmentDatosPaciente(PacientesModel item) {
        // Required empty public constructor
        this.item=item;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doc_datos_paciente, container, false);
        //CODIGO AGREGADO
        tvCodigo=view.findViewById(R.id.tvCodigo_fragment_doc_datos_paciente);
        tvNombre=view.findViewById(R.id.tvNombre_fragment_doc_datos_paciente);
        tvFechaN=view.findViewById(R.id.tvFechaN_fragment_doc_datos_paciente);
        tvCorreo=view.findViewById(R.id.tvCorreo_fragment_doc_datos_paciente);
        tvEstado=view.findViewById(R.id.tvEstado_fragment_doc_datos_paciente);
        tvDui=view.findViewById(R.id.tvDui_fragment_doc_datos_paciente);

        btnVerHistorial=view.findViewById(R.id.btnVerHistorial_fragment_doc_datos_paciente);
        btnProgramarConsulta=view.findViewById(R.id.btnProgramarConsulta_fragment_doc_datos_paciente);

        //llenar los TextView
        tvCodigo.setText(item.per_codigo+"");
        tvNombre.setText(item.pac_nombre);
        tvFechaN.setText(formatoFecha.format(item.per_fecha_nace));
        tvCorreo.setText(item.per_correo);
        tvEstado.setText(item.per_estado);
        tvDui.setText(item.per_dui);

        btnVerHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVerHistorial.setBackgroundResource(R.drawable.boton_redondeado);
                btnVerHistorial.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnVerHistorial.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnVerHistorial.setTextColor(Color.BLACK);

                        //Codigo para logica del boton
                        Toast.makeText(getContext(), "Ver historial", Toast.LENGTH_SHORT).show();
                    }
                }, 100);
            }
        });

        btnProgramarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProgramarConsulta.setBackgroundResource(R.drawable.boton_redondeado);
                btnProgramarConsulta.setTextColor(Color.WHITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnProgramarConsulta.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        btnProgramarConsulta.setTextColor(Color.BLACK);

                        //Codigo para logica del boton
                        Toast.makeText(getContext(), "Programar consulta", Toast.LENGTH_SHORT).show();
                        //crear y mostrar un Dialog
                        DialogAgregarCita dialog = new DialogAgregarCita();
                        dialog.show(getFragmentManager(), "dialog_admin_ejemplo");
                    }
                }, 100);
            }
        });
        //FIN DE CODIGO
        return view;
    }

}
