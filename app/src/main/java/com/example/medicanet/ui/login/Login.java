package com.example.medicanet.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicanet.MainActivity;
import com.example.medicanet.R;
import com.example.medicanet.utils.PreferenceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.ibrahimsn.particle.ParticleView;

public class Login extends AppCompatActivity {

    private ParticleView particleView;

    Button btn_iniciar,btn_salir;
    EditText edtUser, edtPassword;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ValueEventListener eventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase =  FirebaseDatabase.getInstance().getReference("Users");


        btn_iniciar=findViewById(R.id.btn_login_iniciar);
        btn_salir=findViewById(R.id.btn_login_salir);
        edtUser = findViewById(R.id.edt_login_usuario);
        edtPassword = findViewById(R.id.edt_login_password);


        Log.i("login_", "pre");

        if(PreferenceUtils.getEmail(getApplicationContext()) !=null || PreferenceUtils.getRol(getApplicationContext()) !=null ){

            Log.i("login_", "condicion1");
            String rolUser= PreferenceUtils.getRol(getApplicationContext()) ;
            Log.i("login_", rolUser);
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("user", rolUser);
            startActivity(i);
            finish();
        }else{
            Log.i("login_", "condicion2");
            particleView=findViewById(R.id.particleView_activity_login);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        particleView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        particleView.pause();
    }

    public  void iniciar(final View view){


        btn_iniciar.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_iniciar.setBackgroundResource(R.drawable.boton_redondeado_borde);

                if (camposVacios()){
                    return;
                }
                Ingresar(view);
            }
        },100);
    }

    public boolean camposVacios(){
        if (edtUser.getText().toString().equals("")){
            edtUser.setError("Digite el correo");
            edtUser.requestFocus();
            return true;
        }
        if (edtPassword.getText().toString().equals("")){
            edtPassword.setError("Digite la contraseña");
            edtPassword.requestFocus();
            return true;
        }
        return false;
    }

    public void salir(View view){
        btn_salir.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_salir.setBackgroundResource(R.drawable.boton_redondeado_borde);
                finishAffinity();
            }
        },100);
    }
    @Override
    protected void onStart() {
        super.onStart();

        // Check si el user esta asignado en (non-null) .
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //logica aca
    }
    public void Ingresar(View v){

        final String email = edtUser.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        //hay que identificarse en firebase auth y si el auth es exitoso buscamos los datos de usuario por keyID
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio exitoso

                            //guardar preferencias login
                            PreferenceUtils.GuardarEmail(email, getApplicationContext());
                            PreferenceUtils.GuardarPassword(password, getApplicationContext());


                            //objeto usuario que recupera el ID de usuario actual
                            FirebaseUser user = mAuth.getCurrentUser();
                            String idUser = user.getUid();
                            obtenerRolUsuario(idUser);


                            //startActivity(i);

                            //obtenerRol(email);
                           // Toast.makeText(getApplicationContext(), "Bienvenido" , Toast.LENGTH_LONG).show();

                        } else {
                            // si falla mostrar.
                            Log.w("Fallo", "Error al iniciar", task.getException());
                            Toast.makeText(getApplicationContext(), "Credenciales incorrectas o fallo de conexión",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


    }


    public void obtenerRolUsuario(String idUser){



        //buscamos en el nodo el userID
        DatabaseReference mData2 = mDatabase.child(idUser);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String rol = (dataSnapshot.child("rol").getValue().toString().trim());
                //Log.d("rol", rol);
                boolean authUser = false;
                if(rol.equals("doctor")){
                     authUser=true;
                    //Toast.makeText(getApplicationContext(), "doctor" , Toast.LENGTH_LONG).show();
                }else if(rol.equals("paciente")){
                   authUser=true;
                    //Toast.makeText(getApplicationContext(), "paciente" , Toast.LENGTH_LONG).show();
                }else if(rol.equals("farmacia")){
                    authUser=true;
                    //Toast.makeText(getApplicationContext(), "farmacia" , Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Error al ingresar. Comuniquese con soporte tecnico" , Toast.LENGTH_LONG).show();

                //Si el login es correcto enviarlo a la actividad con el rol de user
                if(authUser==true){
                    //guardar preferencias de rol
                    PreferenceUtils.GuardarRol(rol, getApplicationContext());


                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("user", rol);
                    startActivity(i);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mData2.addValueEventListener(eventListener);
    }
}
