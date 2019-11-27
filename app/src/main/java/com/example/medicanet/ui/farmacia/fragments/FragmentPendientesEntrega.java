package com.example.medicanet.ui.farmacia.fragments;


import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.Metodos;
import com.example.medicanet.ui.doctor.dialogs.DialogAgregarCita;
import com.example.medicanet.ui.farmacia.fragments.dialogs.DialogEntregaMedicamentos;

public class FragmentPendientesEntrega extends Fragment {

    private ListView lvPendientesEntrega;
    private Bundle bd;
    private ImageButton imgDesde, imgHasta;
    private EditText edtDesde, edtHasta;

    public String [] Arr1 = {"Jose","Maria","Callejon","Romeo"};
    public String [] Arr2 = {"Real Madrid","Barcelona","Atletico de Madrid","Juventus"};
    public String [] Arr3 = {"Lemus","Ramirez","Jetsu","Santos"};
    public String [] Arr4 = {"EL Encuentro","Metro Centro","Simeon Cañas","La Tecno :v"};
    public int [] img = {R.drawable.medicanet1,R.drawable.medicanet1,R.drawable.medicanet1,R.drawable.medicanet1};

    public FragmentPendientesEntrega() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_far_pendientes_entrega, container, false);

        lvPendientesEntrega = v.findViewById(R.id.lvPendientesEntrega_fragment_far_pendientes_entrega);
        imgDesde = v.findViewById(R.id.imgbDesde_fragment_far_pendientes_entrega);
        imgHasta = v.findViewById(R.id.imgbHasta_fragment_far_pendientes_entrega);

        edtDesde = v.findViewById(R.id.edtDesde_fragment_far_pendientes_entrega);
        edtHasta = v.findViewById(R.id.edtHasta_fragment_far_pendientes_entrega);

        AdaptadorListView ha = new AdaptadorListView(getContext(),img,Arr1,Arr2,Arr3,Arr4);
        lvPendientesEntrega.setAdapter(ha);
        bd = getArguments();

       String Titulo = bd.getString("titulo");

       imgDesde.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Metodos.fecha(getContext(),edtDesde);
           }
       });

       imgHasta.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Metodos.fecha(getContext(),edtHasta);
           }
       });

       lvPendientesEntrega.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {


                       //crear y mostrar un Dialog
                       DialogEntregaMedicamentos dialog = new DialogEntregaMedicamentos();
                       dialog.show(getFragmentManager(), "dialog_entregar_Medicamentos");
                   }
               },100);
           }
       });
        // Inflate the layout for this fragment
        return v;
    }

}
