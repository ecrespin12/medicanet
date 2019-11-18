package com.example.medicanet.ui.farmacia.ConsultasProgramadas;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;

public class far_consultas_programadas extends Fragment {
    public ListView LvConsultas;
    public String []
            Arr1 = {"Jose","Maria","Callejon","Romeo"},
            Arr2 = {"Real Madrid","Barcelona","Atletico de Madrid","Juventus"},
            Arr3 = {"Lemus","Ramirez","Jetsu","Santos"},
            Arr4 = {"EL Encuentro","Metro Centro","Simeon Cañas","La Tecno :v"};

    public int [] img = {R.drawable.medicanet1,R.drawable.medicanet1,R.drawable.medicanet1,R.drawable.medicanet1};
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public far_consultas_programadas() {

    }

    public static far_consultas_programadas newInstance(String param1, String param2) {
        far_consultas_programadas fragment = new far_consultas_programadas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_far_pendientes_entrega, container, false);
        LvConsultas = v.findViewById(R.id.lv_far_consultas_programadas);

        AdaptadorListView ha = new AdaptadorListView(getContext(),img,Arr1,Arr2,Arr3,Arr4);
        LvConsultas.setAdapter(ha);

        return v;
    }





}
