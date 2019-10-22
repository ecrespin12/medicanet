package com.example.medicanet.ui.entregaMedicamentos;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.medicanet.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link entregaM.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class entregaM extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    ArrayList<String> nombres;
    ConsultasAdapter ad;
    private OnFragmentInteractionListener mListener;

    public entregaM() {
        // Required empty public constructor
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
        v = inflater.inflate(R.layout.fragment_pac_medicamentos, container, false);
        nombres = new ArrayList<>();
        nombres.add("Jose Aparicio");
        nombres.add("Kevin Fernando");
        nombres.add("Josue Molina");
        nombres.add("David Lopez");
        nombres.add("Brian Guadron");
        nombres.add("Chiky Gonzales");
        nombres.add("Humberto Cañas");
        nombres.add("Jose Aparicio");
        nombres.add("Kevin Fernando");
        nombres.add("Josue Molina");
        nombres.add("David Lopez");
        nombres.add("Brian Guadron");
        nombres.add("Chiky Gonzales");
        nombres.add("Humberto Cañas");
        nombres.add("Jose Aparicio");
        nombres.add("Kevin Fernando");
        nombres.add("Josue Molina");
        nombres.add("David Lopez");
        nombres.add("Brian Guadron");
        nombres.add("Chiky Gonzales");
        nombres.add("Humberto Cañas");
        nombres.add("Jose Aparicio");
        nombres.add("Kevin Fernando");
        nombres.add("Josue Molina");
        nombres.add("David Lopez");
        nombres.add("Brian Guadron");
        nombres.add("Chiky Gonzales");
        nombres.add("Humberto Cañas");
        nombres.add("Jose Aparicio");
        nombres.add("Kevin Fernando");
        nombres.add("Josue Molina");
        nombres.add("David Lopez");
        nombres.add("Brian Guadron");
        nombres.add("Chiky Gonzales");
        nombres.add("Humberto Cañas");
        nombres.add("Jose Aparicio");
        nombres.add("Kevin Fernando");
        nombres.add("Josue Molina");
        nombres.add("David Lopez");
        nombres.add("Brian Guadron");
        nombres.add("Chiky Gonzales");
        nombres.add("Humberto Cañas");


        ListView Lv1 = v.findViewById(R.id.lv_pendientes_entregas_pac);


        ad = new ConsultasAdapter(R.layout.consultas_programadas_design,getContext(),nombres);

        Lv1.setAdapter(ad);

        return v;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
