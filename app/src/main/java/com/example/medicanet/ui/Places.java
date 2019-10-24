package com.example.medicanet.ui;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicanet.R;
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

import java.util.Arrays;

public class Places extends FragmentActivity implements OnMapReadyCallback {

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

        btnHibrido.setBackgroundResource(R.drawable.boton_redondeado1);
        btnNormal.setBackgroundResource(R.drawable.boton_redondeado2);
        btnSatelite.setBackgroundResource(R.drawable.boton_redondeado3);
        btnTerreno.setBackgroundResource(R.drawable.boton_redondeado4);

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
        btnHibrido.setBackgroundResource(R.drawable.boton_presionado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnHibrido.setBackgroundResource(R.drawable.boton_redondeado1);
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        },100);

    }

    public void setBtnNormal(View view){
        btnNormal.setBackgroundResource(R.drawable.boton_presionado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnNormal.setBackgroundResource(R.drawable.boton_redondeado2);
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        },100);

    }
    public void setBtnSatelite(View view){

        btnSatelite.setBackgroundResource(R.drawable.boton_presionado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnSatelite.setBackgroundResource(R.drawable.boton_redondeado3);
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        },100);

    }
    public void setBtnTerreno(View view){
        btnTerreno.setBackgroundResource(R.drawable.boton_presionado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnTerreno.setBackgroundResource(R.drawable.boton_redondeado4);
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        },100);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ubicacion = new LatLng(13.701198, -89.200830);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                .anchor(0.5f,1f)
                .position(ubicacion)
                .title("UTEC")
                .snippet("Universidad Tecnológica de El Salvador"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,12));

        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getApplicationContext(),"NO TIENES PERMISOS DE UBICACION PARA MOSTRAR TU UBICACION ACTUAL",Toast.LENGTH_LONG).show();
        }else {
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador))
                        .anchor(0.5f,1f)
                        .position(latLng)
                        .title("Te has cambiado de lugar")
                        .snippet("Puedes hacer zoom para descubrir cosas nuevas en este lugar!"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
            }
        });
    }
}
