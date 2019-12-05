package com.example.medicanet.ui.custom_preferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.medicanet.R;

public class CustomPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_preferences);
        /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new preferencesFragment());
        ft.commit();*/
        // Crea el nuevo fragmento
        CustomPreferencesFragment fragmentDatosConsulta = new CustomPreferencesFragment();
        //Crea la transaccion
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //remplazar el nuevo fragmento en el contenedor principal(nav_host_fragment)
        transaction.replace(R.id.conteneder_preferencias, fragmentDatosConsulta);
        //transaction.addToBackStack(null);

        // Commit a la transacción
        transaction.commit();
    }
}
