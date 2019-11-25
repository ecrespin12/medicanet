package com.example.medicanet.ui.farmacia.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.example.medicanet.metodos.Metodos;

import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;

public class FragmentEntregarMedicamentos extends Fragment {

    RetrofitClientInstance ret = new RetrofitClientInstance();
    ImageButton imgDesde, imgHasta;
    EditText edtDesde, edtHasta;
    private IServices servicio;
    public ListView LvConsultas;
    public EditText edtId;
    public String [] Arr1 = {"Jose","Maria","Callejon","Romeo"};
    public String [] Arr2 = {"Real Madrid","Barcelona","Atletico de Madrid","Juventus"};
    public String [] Arr3 = {"Lemus","Ramirez","Jetsu","Santos"};
    public String [] Arr4 = {"EL Encuentro","Metro Centro","Simeon Cañas","La Tecno :v"};
    public int [] img = {R.drawable.medicanet1,R.drawable.medicanet1,R.drawable.medicanet1,R.drawable.medicanet1};

    public FragmentEntregarMedicamentos() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_far_entregar_medicamentos, container, false);

        edtDesde = v.findViewById(R.id.edtDesde_fragment_far_entregar_medicamentos);
        edtHasta = v.findViewById(R.id.edtHasta_fragment_far_entregar_medicamentos);

        imgHasta = v.findViewById(R.id.imgbHasta_fragment_far_entregar_medicamentos);
        imgDesde = v.findViewById(R.id.imgbDesde_fragment_far_entregar_medicamentos);
        edtId = v.findViewById(R.id.edtId_fragment_far_entregar_medicamentos);
        LvConsultas = v.findViewById(R.id.lvConsultasProgramadas_fragment_far_entregar_medicamentos);

        AdaptadorListView ha = new AdaptadorListView(getContext(),null,Arr1,Arr2,Arr3,Arr4);
        LvConsultas.setAdapter(ha);

        servicio = (IServices) ret.createService(IServices.class, v.getContext().getResources().getString(R.string.token));

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

        LvConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bundle paqueteDeDatos = new Bundle();
                        paqueteDeDatos.putString("titulo", Arr1[position]);
                        paqueteDeDatos.putString("comentario1",Arr2[position]);
                        paqueteDeDatos.putString("comentario2", Arr3[position]);
                        paqueteDeDatos.putString("comentario3", Arr4[position]);
                        FragmentPendientesEntrega fragmentPedientesEntrega = new FragmentPendientesEntrega();

                        fragmentPedientesEntrega.setArguments(paqueteDeDatos);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, fragmentPedientesEntrega);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                },100);
            }
        });

        return v;
    }
}
