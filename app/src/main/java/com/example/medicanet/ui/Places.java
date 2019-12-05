package com.example.medicanet.ui;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicanet.R;
import com.example.medicanet.metodos.AdaptadorListView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import clasesResponse.CentroMedicoModel;
import clasesResponse.PacientesModel;
import retrofit.Interfaces.IServices;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Places extends FragmentActivity implements OnMapReadyCallback {

    RetrofitClientInstance ret = new RetrofitClientInstance();
    private IServices servicio;
    List<CentroMedicoModel> resp;
    CentroMedicoModel item;

    private GoogleMap mMap;

    Button btnHibrido,btnTerreno,btnSatelite,btnNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        btnHibrido=findViewById(R.id.btnHibrido);
        btnTerreno=findViewById(R.id.btnTerreno);
        btnSatelite=findViewById(R.id.btnSatelite);
        btnNormal=findViewById(R.id.btnNormal);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.places);
        mapFragment.getMapAsync(this);

        btnHibrido.setBackgroundResource(R.drawable.boton_redondeado_borde);
        btnNormal.setBackgroundResource(R.drawable.boton_redondeado_borde);
        btnSatelite.setBackgroundResource(R.drawable.boton_redondeado_borde);
        btnTerreno.setBackgroundResource(R.drawable.boton_redondeado_borde);

        //INICIALIZAR OBJETO DE LA INTERFAZ
        servicio = (IServices) ret.createService(IServices.class, getApplicationContext().getResources().getString(R.string.token));

        //CARGAR EL WS
        //getCentros();

        //INICIO CODIGO DE PLACES
        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!com.google.android.libraries.places.api.Places.isInitialized()) {
            com.google.android.libraries.places.api.Places.initialize(getApplicationContext(), "AIzaSyCmCLFHxbSbjO3vnGI0eRdOl1TnJuiVTuc");
        }
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                //INICIO CODIGO PARA COLOCAR MARCADOR
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                        .anchor(0.5f,1f)
                        .position(place.getLatLng())
                        .title(place.getName())
                        .snippet("Bienvenido a "+place.getName()));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(),12));
                //FIN DE CODIGO PARA COLOCAR MARCADOR
            }

            @Override
            public void onError(Status status) {

            }
        });
        //FIN CODIGO DE PLACES

    }

    public void setBtnHibrido(View view){
        btnHibrido.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnHibrido.setBackgroundResource(R.drawable.boton_redondeado_borde);
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        },100);

    }

    public void setBtnNormal(View view){
        btnNormal.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnNormal.setBackgroundResource(R.drawable.boton_redondeado_borde);
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        },100);

    }
    public void setBtnSatelite(View view){

        btnSatelite.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnSatelite.setBackgroundResource(R.drawable.boton_redondeado_borde);
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        },100);

    }
    public void setBtnTerreno(View view){
        btnTerreno.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnTerreno.setBackgroundResource(R.drawable.boton_redondeado_borde);
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        },100);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //CARGAR EL WS
        getCentros(mMap);

        //LatLng ubicacion = new LatLng(13.701198, -89.200830);
        //mMap.addMarker(new MarkerOptions()
        //        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
        //        .anchor(0.5f,1f)
        //        .position(ubicacion)
        //        .title("UTEC")
        //        .snippet("Universidad Tecnológica de El Salvador"));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,12));

        mMap.getUiSettings().setZoomControlsEnabled(true);



        SharedPreferences obj = PreferenceManager.getDefaultSharedPreferences(this);
        if (obj.getBoolean("check_box_preference_1", true))
        {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getApplicationContext(),"NO TIENES PERMISOS DE UBICACION PARA MOSTRAR TU UBICACION ACTUAL",Toast.LENGTH_LONG).show();
            }else {
                mMap.setMyLocationEnabled(true);
            }
            String Result = ""+obj.getString("list_preference_2", "");
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            if(Result.equals("S")){mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);}
            if(Result.equals("T")){mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);}
            if(Result.equals("H")){mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);}


        }else{
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //mMap.clear();
                //mMap.addMarker(new MarkerOptions()
                //        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                //        .anchor(0.5f,1f)
                //        .position(latLng)
                //        .title("Te has cambiado de lugar")
                //        .snippet("Puedes hacer zoom para descubrir cosas nuevas en este lugar!"));
                //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
            }
        });
    }

    //METODO PARA CONSUMIR EL WS
    private void getCentros(final GoogleMap mMap) {

        Log.d("JTDebug", "Entra Metodo getCentros");
        Call<List<CentroMedicoModel>> call = servicio.getCentroMedico(0);
        Log.d("JTDebug", "Url: " + ret.BASE_URL);
        call.enqueue(new Callback<List<CentroMedicoModel>>() {
            @Override
            public void onResponse(Call<List<CentroMedicoModel>> call, Response<List<CentroMedicoModel>> response) {
                Log.d("JTDebug", "Entra OnResponse");
                try {
                    if (response.isSuccessful()) {
                        Log.d("JTDebug", "Entra IsSuccessful");
                        resp = response.body();
                        Log.d("JTDebug", "Count: " + resp.size());

                        mMap.clear();
                        for (int i=0;i<resp.size();i++) {
                            item = resp.get(i);
                            mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                                    .anchor(0.5f,1f)
                                    .position(new LatLng(item.cmd_latitud,item.cmd_longitud))
                                    .title(item.cmd_nombre)
                                    .snippet("Visitanos en "+item.cmd_nombre+" !!!"));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(item.cmd_latitud,item.cmd_longitud),12));
                        }
                    } else {
                        Log.d("JTDebug", "Entra not Successful. Code: " + response.code() + "\nMessage: " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<CentroMedicoModel>> call, Throwable t) {
                Log.d("JTDebug", "Entra OnFailure");
                Log.d("JTDebug", "Message: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
