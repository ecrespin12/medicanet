package com.example.medicanet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.medicanet.ui.Places;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarScan();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        //recupero el rol de usuario
        String user = getIntent().getExtras().getString("user");

        if(user.equals("doctor")){
            navigationView.getMenu().setGroupVisible(R.id.group_doctor, true);
        }else if(user.equals("farmacia")){
            navigationView.getMenu().setGroupVisible(R.id.group_farmacia, true);
        }else if(user.equals("paciente")){
            navigationView.getMenu().setGroupVisible(R.id.group_paciente, true);
        }else {

            //admin por defecto
            navigationView.getMenu().setGroupVisible(R.id.group_admin, true);
        }



        mAppBarConfiguration = new AppBarConfiguration.Builder(

                //paciente
                R.id.nav_pac_citas,
                R.id.nav_pac_datos_medicos,
                R.id.nav_pac_historial_medico,
                R.id.nav_pac_medicamentos,
                R.id.nav_pac_perfil,

                //doctor
                R.id.nav_doc_consultas_programadas,
                R.id.nav_doc_pacientes,

                //farmacia
                R.id.nav_far_entregar_medicamentos,
                R.id.nav_far_historial_entregas,

                R.id.nav_admin_doctor,
                R.id.nav_admin_ejemplo)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //LINEA PARA QUE SE PUEDAN PONER IMAGENES DE COLORES EN EL NAVIGATION DRAWER
        navigationView.setItemIconTintList(null);
    }

    public void IniciarScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Codigo QR");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_ajustes){
            Toast.makeText(getApplicationContext(),"Ajustes...",Toast.LENGTH_SHORT).show();
        }else if(id==R.id.action_salir){
            Toast.makeText(getApplicationContext(),"Saliendo...",Toast.LENGTH_SHORT).show();
            finishAffinity();
        }else if(id==R.id.action_search){
            Toast.makeText(getApplicationContext(),"Abriendo mapa...",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), Places.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String Resultado = "";// al final aqui se captura el resultado para que hagan la busqueda
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                Resultado = result.getContents();
            }
            Toast.makeText(this, "Lectura: " + Resultado, Toast.LENGTH_LONG).show();

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
