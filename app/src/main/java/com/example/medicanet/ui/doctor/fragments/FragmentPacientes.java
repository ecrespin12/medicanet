package com.example.medicanet.ui.doctor.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Handler;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.List;
import clasesResponse.PacientesModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPacientes extends Fragment {

    //VARIABLES PARA EL WS
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    ////////////////////////////////////////////////////////////////////////////

    List<PacientesModel> listPacientes;
    PacientesModel paciente;

    View view;
    EditText edtCodigoPaciente;
    Button btnBuscar;
    ListView lvPacientes;

    AdaptadorListView adaptadorListView;

    int[] imagenes;
    String[] arr1;
    String[] arr2;
    String[] arr3;
    String[] arr4;

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

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));

        //CARGAR EL LISTVIEW CON EL METODO GETCONSULTAS
        getPacientes(view,2);


        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Logica
                        //Crea el nuevo fragmento
                        paciente=listPacientes.get(position);
                        FragmentDatosPaciente fragmentDatosPaciente = new FragmentDatosPaciente(paciente);
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

    //METODO PARA CONSUMIR EL WS
    private void getPacientes(final View view, int med) {
        Log.d("JTDebug", "Entra Metodo pacientes");
        Call<List<PacientesModel>> call = servicio.getPacientesActivos(med);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<PacientesModel>>() {
            @Override
            public void onResponse(Call<List<PacientesModel>> call, Response<List<PacientesModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        listPacientes = response.body();
                        Log.d("JTDebug", "Count: " + listPacientes.size());
                        imagenes = new int[listPacientes.size()];
                        arr1 = new String[listPacientes.size()];
                        arr2 = new String[listPacientes.size()];
                        arr3 = new String[listPacientes.size()];
                        arr4 = new String[listPacientes.size()];

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE d MMMM yyyy");
                        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");

                        for (int i=0;i<listPacientes.size();i++) {
                            paciente = listPacientes.get(i);
                            imagenes[i]=R.drawable.paciente;
                            arr1[i] = "DUI: "+paciente.per_dui;
                            arr2[i] = "Nombre: "+paciente.pac_nombre;
                            arr3[i] = "Correo: "+paciente.per_correo;
                            arr4[i] = "Estado: "+paciente.per_estado;
                        }
                        adaptadorListView = new AdaptadorListView(getContext(), imagenes, arr1, arr2, arr3, arr4);
                        lvPacientes.setAdapter(adaptadorListView);
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<PacientesModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
