package com.example.medicanet.ui.doctor.consultas;

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
import android.widget.ListView;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.ui.doctor.datosPaciente.DatosPaciente;

public class ConsultasProgramadas extends Fragment {

    public static String keyImg="img";
    public static String keyNombre="nombre";
    public static String keyDescripcion="descripcion";



    ListView lvLista;
    AdaptadorGeneral adaptadorGeneral;

    TypedArray imagenes;
    String [] nombres;
    String [] descripciones;

    Button btnBuscar;

    public ConsultasProgramadas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doc_consultas_programadas, container, false);

        //Codigo

        lvLista=view.findViewById(R.id.lvConsultas_fragment_doc_consultas_pendientes);

        imagenes=getResources().obtainTypedArray(R.array.img_item_list_ejemplo);
        nombres=getResources().getStringArray(R.array.campo1_item_list_ejemplo);
        descripciones=getResources().getStringArray(R.array.campo2_item_list_ejemplo);

        adaptadorGeneral = new AdaptadorGeneral(getContext(),imagenes,nombres,descripciones,null,null);
        lvLista.setAdapter(adaptadorGeneral);

        final DatosPaciente datosPaciente=new DatosPaciente();

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //creando bundle para pasar datos al fragmento
                Bundle paqueteDeDatos = new Bundle();
                paqueteDeDatos.putInt(keyImg,imagenes.getResourceId(position,-1));
                paqueteDeDatos.putString(keyNombre,nombres[position]);
                paqueteDeDatos.putString(keyDescripcion,descripciones[position]);

                //Agregamos los argumentos al fragmento
                datosPaciente.setArguments(paqueteDeDatos);

                // Crea el nuevo fragmento y la transacción.
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, datosPaciente);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        btnBuscar=view.findViewById(R.id.btnBuscar_fragment_doc_consultas_pendientes);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBuscar.setBackgroundResource(R.drawable.boton_redondeado);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnBuscar.setBackgroundResource(R.drawable.boton_redondeado_borde);
                        Toast.makeText(getContext(),"buscando",Toast.LENGTH_SHORT).show();
                    }
                },100);
            }
        });
        //fin codigo agregado

        return view;
    }
}
