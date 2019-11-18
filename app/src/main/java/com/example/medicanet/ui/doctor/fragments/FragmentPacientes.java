package com.example.medicanet.ui.doctor.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;

public class FragmentPacientes extends Fragment {

    View view;
    EditText edtCodigoPaciente;
    Button btnBuscar;
    ListView lvPacientes;

    AdaptadorListView adaptadorListView;

    TypedArray imagenes;
    String[] codigosPaciente;
    String[] nombresPaciente;

    public static String keyImg = "img";
    public static String keyCodigo = "nombre";
    public static String keyNombre = "descripcion";

    public FragmentPacientes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doc_pacientes, container, false);
        //CODIGO AGREGADO
        edtCodigoPaciente = view.findViewById(R.id.edtCodigoPaciente_fragment_doc_pacientes);
        btnBuscar = view.findViewById(R.id.btnBuscar_fragment_doc_pacientes);
        lvPacientes = view.findViewById(R.id.lvPacientes_fragment_doc_pacientes);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Buscando paciente",Toast.LENGTH_SHORT).show();
            }
        });

        imagenes = getResources().obtainTypedArray(R.array.img_lista_paciente_ejemplo);
        codigosPaciente = getResources().getStringArray(R.array.campo1_lista_paciente_ejemplo);
        nombresPaciente = getResources().getStringArray(R.array.campo2_lista_paciente_ejemplo);

        adaptadorListView = new AdaptadorListView(getContext(),null,codigosPaciente,nombresPaciente,null,null);
        lvPacientes.setAdapter(adaptadorListView);


        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Logica
                        //Crea el nuevo fragmento
                        FragmentDatosPaciente fragmentDatosPaciente = new FragmentDatosPaciente(codigosPaciente[position],nombresPaciente[position]);
                        // Crea la transacción.
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        //remplaza el fragmento en el contenedor
                        transaction.replace(R.id.nav_host_fragment, fragmentDatosPaciente);
                        transaction.addToBackStack(null);
                        // Commit a la transacción
                        transaction.commit();
                    }
                },200);
            }
        });

        //FIN CODIGO AGREGADO
        return view;
    }

}
