package com.example.medicanet.firebase;

import java.sql.Date;

public class DoctorClass {

    public String codigo,nombres, apellidos, correo, rol;
    public String fechaNacimiento;

    public DoctorClass(){}

    public DoctorClass(String codigo, String nombres, String apellidos, String correo, String fechaNacimiento, String rol){

        this.codigo=codigo;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.correo =correo;
        this.fechaNacimiento=fechaNacimiento;
        this.rol=rol;
    }
}
