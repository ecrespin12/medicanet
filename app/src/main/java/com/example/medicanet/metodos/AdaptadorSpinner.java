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

import java.util.ArrayList;

public class AdaptadorSpinner  extends ArrayAdapter<ItemSpinner>{

    public Context contexto;
    View view;

    public AdaptadorSpinner(Context context, ArrayList<ItemSpinner> listaDeItem){
        super(context,0,listaDeItem);
        this.contexto=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return iniciarVista(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return iniciarVista(position,convertView,parent);
    }

    private View iniciarVista(int position, View convertView, ViewGroup parent){
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner,parent,false);
        }

        //enlazando vistas
        ImageView imagen=convertView.findViewById(R.id.img_item_spinner);
        TextView tvCampo1=convertView.findViewById(R.id.tvCampo1_item_spinner);
        TextView tvCampo2=convertView.findViewById(R.id.tvCampo2_item_spinner);
        TextView tvCampo3=convertView.findViewById(R.id.tvCampo3_item_spinner);
        TextView tvCampo4=convertView.findViewById(R.id.tvCampo4_item_spinner);

        ItemSpinner itemSpinner = getItem(position);

        if (itemSpinner!=null){
            imagen.setImageResource(itemSpinner.getImg());
            tvCampo1.setText(itemSpinner.getCampo1());
            tvCampo2.setText(itemSpinner.getCampo2());
            tvCampo3.setText(itemSpinner.getCampo3());
            tvCampo4.setText(itemSpinner.getCampo4());
        }

        return convertView;
    }
}
