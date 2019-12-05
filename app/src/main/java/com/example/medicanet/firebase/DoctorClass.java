package com.example.medicanet.firebase;

import java.sql.Date;

public class DoctorClass {

    public String codigo,nombres, apellidos, correo, rol;


    public DoctorClass(){}

    public DoctorClass(String codigo, String nombres, String apellidos, String correo,  String rol){

        this.codigo=codigo;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.correo =correo;
        this.rol=rol;
    }
}
