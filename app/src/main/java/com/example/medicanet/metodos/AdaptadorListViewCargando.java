package com.example.medicanet.metodos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicanet.R;

public class AdaptadorListViewCargando extends ArrayAdapter {

    public Context contexto;

    View view;

    public AdaptadorListViewCargando(@NonNull Context context) {

        super(context, R.layout.item_list_view_cargando);

        this.contexto=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflar = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflar.inflate(R.layout.item_list_view_cargando,parent,false);

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflar = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflar.inflate(R.layout.item_list_view_cargando,parent,false);
        return view;
    }
}

