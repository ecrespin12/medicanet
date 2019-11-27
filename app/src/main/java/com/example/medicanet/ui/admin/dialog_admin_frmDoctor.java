package com.example.medicanet.ui.admin;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.medicanet.R;
import com.example.medicanet.firebase.DoctorClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dialog_admin_frmDoctor extends DialogFragment {


    //nombre de fragment
    private static final String TAG ="dialog_admin_frmDoctor";

    DatabaseReference DBReference;
    FirebaseAuth mAuth;

    //objetos
    public EditText edtNombres, edtApellidos, edtCorreo;
    public ImageView tvClose;
    public Button btnGuardar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_admin_frm_doctor, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //ins objetos
        btnGuardar = view.findViewById(R.id.btnGuardarDoc_modal_adm);
        tvClose = view.findViewById(R.id.btnCloseDoc_modal_adm);


        edtNombres = view.findViewById(R.id.txtNombresDoc_modal_adm);
        edtApellidos = view.findViewById(R.id.txtApellidosDoc_modal_adm);
        edtCorreo = view.findViewById(R.id.txtCorreoDoc_modal_adm);

        DBReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();



        //Para cancelar
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Saliendo...");
                getDialog().dismiss();
            }
        });


        //para guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final String codigo = "29543687";
            final String nombres = edtNombres.getText().toString();
            final String apellidos = edtApellidos.getText().toString();
            final String correo = edtCorreo.getText().toString();
            final String  rol = "doctor";

            final String fecha="03/03/2000";
            final Date date = new Date();
            final Date newDate = new Date(date.getTime());
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            String stringdate = dt.format(newDate);

            Log.d("date", stringdate);

            final String password = "123456";

                mAuth.createUserWithEmailAndPassword(correo, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    DoctorClass enc  = new DoctorClass(
                                            codigo,
                                            nombres,
                                            apellidos,
                                            correo,
                                            fecha,
                                            rol
                                    );

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(enc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(getContext(), "Registro realizado exitosamente", Toast.LENGTH_LONG).show();

                                        }
                                    });

                                }else {
                                    Toast.makeText(getContext(), "Error al registrar", Toast.LENGTH_LONG).show();
                                }

                            }
                        });


            }
            });






        return view;
    }

}
