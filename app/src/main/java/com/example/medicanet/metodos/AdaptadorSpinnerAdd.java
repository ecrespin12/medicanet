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

public class AdaptadorSpinnerAdd extends ArrayAdapter<ItemSpinnerAdd>{

    public Context contexto;
    View view;

    public AdaptadorSpinnerAdd(Context context, ArrayList<ItemSpinnerAdd> listaDeItem){
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

        ItemSpinnerAdd itemSpinnerAdd = getItem(position);

        if (itemSpinnerAdd !=null){
            imagen.setImageResource(itemSpinnerAdd.getImg());
            tvCampo1.setText(itemSpinnerAdd.getCampo1());
            tvCampo2.setText(itemSpinnerAdd.getCampo2());
            tvCampo3.setText(itemSpinnerAdd.getCampo3());
            tvCampo4.setText(itemSpinnerAdd.getCampo4());
        }

        return convertView;
    }
}
