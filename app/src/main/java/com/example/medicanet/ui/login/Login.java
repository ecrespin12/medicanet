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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button btn_iniciar,btn_salir;
    EditText edtUser;
    EditText edtUsuario, edtClave;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ValueEventListener eventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn_iniciar=findViewById(R.id.btn_login_iniciar);
        btn_salir=findViewById(R.id.btn_login_salir);
        edtUser = findViewById(R.id.edt_login_usuario);

    }

    public  void iniciar(View view){
        btn_iniciar.setBackgroundResource(R.drawable.boton_redondeado);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_iniciar.setBackgroundResource(R.drawable.boton_redondeado_borde);

                String username = edtUser.getText().toString();


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


    public void login(View v){


        final String email = edtUsuario.getText().toString().trim();
        String password = edtClave.getText().toString().trim();

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
                            //obtenerRolUsuario(idUser);

                            //Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
                            //startActivity(i);

                            //obtenerRol(email);
                            //Toast.makeText(getApplicationContext(), "Bienvenido" + y, Toast.LENGTH_LONG).show();

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
}
