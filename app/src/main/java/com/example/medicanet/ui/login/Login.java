package com.example.medicanet.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.medicanet.MainActivity;
import com.example.medicanet.R;

public class Login extends AppCompatActivity {
    Button btn_iniciar,btn_salir;
    EditText edtUser;

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
}
