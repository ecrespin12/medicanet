package com.example.medicanet.webServices;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.medicanet.R;
import java.util.ArrayList;
import java.util.List;

import clasesResponse.clItems;
import clasesResponse.clPrueba;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebService extends Fragment {
    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio = (IServices)ret.createService(IServices.class, null, null);
    private ArrayList<Integer> arrNum = new ArrayList<>();

    private TextView tev_webservice_response1;
    private TextView tev_webservice_response2;
    private Button btn_webservice_1;
    private Button btn_webservice_2;
    private Spinner spn_webservice_version;

    public WebService() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        tev_webservice_response1 = v.findViewById(R.id.tev_webservice_response_1);
        tev_webservice_response2 = v.findViewById(R.id.tev_webservice_response_2);
        btn_webservice_1 = v.findViewById(R.id.btn_webservice_1);
        btn_webservice_2 = v.findViewById(R.id.btn_webservice_2);
        spn_webservice_version = v.findViewById(R.id.spn_webservice_version);

        arrNum.add(1);
        arrNum.add(2);
        spn_webservice_version.setAdapter(new ArrayAdapter<Integer>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, arrNum));

        btn_webservice_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarServicio(view);
            }
        });

        btn_webservice_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarServicioVersion(view);
            }
        });

        return v;
    }

    private void consultarServicio(View view){
        Log.d("JTDebug", "Entra Metodo consulta");
        Call<clPrueba> call = servicio.getConsultaApi();
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<clPrueba>() {
            @Override
            public void onResponse(Call<clPrueba> call, Response<clPrueba> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if(response.isSuccessful()){
                        Log.d("JTDebug", "Entra IsSuccessful");
                        clPrueba resp = response.body();
                        tev_webservice_response1.setText(resp.consulta);
                    }else{
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<clPrueba> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void consultarServicioVersion(View view){
        final String c = spn_webservice_version.getSelectedItem().toString();
        Log.d("JTDebug", "Entra Metodo consulta " + c);
        Call<clItems> call = servicio.getConsultaVersion(c);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<clItems>() {
            @Override
            public void onResponse(Call<clItems> call, Response<clItems> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if(response.isSuccessful()){
                        Log.d("JTDebug", "Entra IsSuccessful");
                        clItems Items = response.body();
                        List<clItems.clVersion> listItems = Items.items;
                        Log.d("JTDebug", "Count: " + listItems.size());
                        for (clItems.clVersion item: listItems) {
                            Log.d("JTDebug", "Entra foreach");
                            if(item.version_id.equals(c)){
                                String Messa = "Código de Version: " + item.version_id +
                                        "\nVersión: " + item.version +
                                        "\nAño: " + item.year;
                                Log.d("JTDebug", "Mensaje " + Messa);
                                tev_webservice_response2.setText(Messa);
                            }
                        }
                    }else{
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<clItems> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
