package com.example.medicanet.ui.farmacia.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;

public class FragmentEntregarMedicamentos extends Fragment {

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
        edtId = v.findViewById(R.id.edtId_fragment_far_entregar_medicamentos);
        LvConsultas = v.findViewById(R.id.lvConsultasProgramadas_fragment_far_entregar_medicamentos);

        AdaptadorListView ha = new AdaptadorListView(getContext(),null,Arr1,Arr2,Arr3,Arr4);
        LvConsultas.setAdapter(ha);
        edtId.setText("hola");

        return v;
    }
}
