package com.example.medicanet.ui.paciente.perfil;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.List;

import clasesResponse.PerfilPacienteModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class perfil extends Fragment {
    //VARIABLES PARA CONSUMIR EL WS##############################
    RetrofitClientInstance ret = new RetrofitClientInstance();
    ImageView qrCode;
    private IServices servicio;
    List<PerfilPacienteModel> resp;
    PerfilPacienteModel item;
    //###########################################################
    ListView lv_datos_pac;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");

    TextView tvNombre,tvCorreo,tvFecha,tvDui;

    public perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvCorreo = view.findViewById(R.id.tvCorreo_Fragment_perfil);
        tvNombre = view.findViewById(R.id.tvNombre_Fragment_perfil);
        tvFecha = view.findViewById(R.id.tvFecha_Fragment_perfil);
        tvDui = view.findViewById(R.id.tvDui_Fragment_perfil);
        // Inflate the layout for this fragment

        servicio = (IServices) ret.createService(IServices.class, view.getContext().getResources().getString(R.string.token));
        getPerfil();

        String cod = "55"; //aqui solo es de sustituir con lo que se quiere el QR
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(cod, BarcodeFormat.QR_CODE, 400, 400);
            qrCode = view.findViewById(R.id.qrCode);
            qrCode.setImageBitmap(bitmap);
        } catch(Exception e) {

        }

        return view;
    }

    //METODO PARA CONSUMIR EL WS
    private void getPerfil() {
        Log.d("JTDebug", "Entra Metodo getPerfil");
        Call<List<PerfilPacienteModel>> call = servicio.getPerfil(1, null, null , null, null,null);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<PerfilPacienteModel>>() {
            @Override
            public void onResponse(Call<List<PerfilPacienteModel>> call, Response<List<PerfilPacienteModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                       Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        PerfilPacienteModel perfil = resp.get(0);
                        tvNombre.setText("Nombre: "+perfil.per_nombre+" "+perfil.per_apellidos);
                        tvCorreo.setText("Correo: "+perfil.per_correo);
                        tvFecha.setText("Fecha N: "+formatoFecha.format(perfil.per_fecha_nace));
                        tvDui.setText("DUI: "+perfil.per_dui);

                        Log.d("JTDebug", "Count: " + resp.size());
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<PerfilPacienteModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
