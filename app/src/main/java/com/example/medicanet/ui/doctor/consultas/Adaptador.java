package com.example.medicanet.ui.doctor.consultas;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicanet.R;

public class Adaptador extends ArrayAdapter {
    public Context contexto;
    public String [] grupos;
    public String [] descripcionesGrupo;
    public TypedArray imagenes;
    View fila;

    public Adaptador(@NonNull Context context, String [] grupos, String [] descripcionesGrupo, TypedArray imagenes) {
        super(context, R.layout.item_de_listview,descripcionesGrupo);

        this.contexto=context;
        this.grupos=grupos;
        this.imagenes=imagenes;
        this.descripcionesGrupo=descripcionesGrupo;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflar = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fila= inflar.inflate(R.layout.item_de_listview,parent,false);
        //enlazando vistas
        TextView txtGrupo=fila.findViewById(R.id.tvNombreItem);
        TextView txtDescripcion = fila.findViewById(R.id.tvDescripcionItem);
        ImageView imgGrupo=fila.findViewById(R.id.imgItem);
        //seteando vistas
        txtGrupo.setText(grupos[position]);
        txtDescripcion.setText(descripcionesGrupo[position]);
        imgGrupo.setImageResource(imagenes.getResourceId(position,-1));

        return fila;
    }
}

