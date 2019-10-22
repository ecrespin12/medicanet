package com.example.medicanet.ui.doctor.consultas;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.datosPaciente.DatosPaciente;

public class ConsultasPendientes extends Fragment {

    public static String keyImg="img";
    public static String keyNombre="nombre";
    public static String keyDescripcion="descripcion";

    String [] nombres;
    String [] descripciones;
    TypedArray imagenes;
    Adaptador adaptador;
    ListView lvLista;

    public ConsultasPendientes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_consultas_pendientes, container, false);

        //Codigo agregado

        nombres=getResources().getStringArray(R.array.aplicaciones);
        descripciones=getResources().getStringArray(R.array.descripciones);
        imagenes=getResources().obtainTypedArray(R.array.iconos);

        adaptador=new Adaptador(getContext(),nombres,descripciones,imagenes);

        lvLista=view.findViewById(R.id.lvConsultas_fragment_doc_consultas_pendientes);
        lvLista.setAdapter(adaptador);

        final DatosPaciente detalle=new DatosPaciente();

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //creando bundle para pasar datos al fragmento
                Bundle paqueteDeDatos = new Bundle();
                paqueteDeDatos.putInt(keyImg,imagenes.getResourceId(position,-1));
                paqueteDeDatos.putString(keyNombre,nombres[position]);
                paqueteDeDatos.putString(keyDescripcion,descripciones[position]);

                //Agregamos los argumentos al fragmento
                detalle.setArguments(paqueteDeDatos);

                // Crea el nuevo fragmento y la transacción.
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, detalle);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });
        //fin codigo agregado

        return view;
    }
}
