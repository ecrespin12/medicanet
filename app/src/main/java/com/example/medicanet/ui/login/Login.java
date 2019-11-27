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

public class Login extends AppCompatActivity {
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

    }

    public  void iniciar(View view){


        btn_iniciar.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_iniciar.setBackgroundResource(R.drawable.boton_redondeado_borde);

                String username = edtUser.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("user", username);
                startActivity(i);
                finish();
            }
        },100);
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
        String password = edtPassword.getText().toString().trim();

        //hay que identificarse en firebase auth y si el auth es exitoso buscamos los datos de usuario por keyID
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio exitoso

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
                            Toast.makeText(getApplicationContext(), "Auth fallo.",
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
