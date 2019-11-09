package com.example.medicanet.metodos;

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

public class AdaptadorListView extends ArrayAdapter {
    public Context contexto;
    public TypedArray imagenes;
    public String [] vectorCampo1;
    public String [] vectorCampo2;
    public String [] vectorCampo3;
    public String [] vectorCampo4;

    ImageView imagen;
    TextView tvCampo1;
    TextView tvCampo2;
    TextView tvCampo3;
    TextView tvCampo4;

    View view;

    public AdaptadorListView(@NonNull Context context,
                             TypedArray imagenes,

                             String [] vectorCampo1,
                             String [] vectorCampo2,
                             String [] vectorCampo3,
                             String [] vectorCampo4) {

        super(context, R.layout.item_list_view,vectorCampo1);

        this.contexto=context;
        this.imagenes=imagenes;
        this.vectorCampo1=vectorCampo1;
        this.vectorCampo2=vectorCampo2;
        this.vectorCampo3=vectorCampo3;
        this.vectorCampo4=vectorCampo4;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflar = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflar.inflate(R.layout.item_list_view,parent,false);
        //enlazando vistas
        imagen=view.findViewById(R.id.img_item_list_view);
        tvCampo1=view.findViewById(R.id.tvCampo1_item_list_view);
        tvCampo2=view.findViewById(R.id.tvCampo2_item_list_view);
        tvCampo3=view.findViewById(R.id.tvCampo3_item_list_view);
        tvCampo4=view.findViewById(R.id.tvCampo4_item_list_view);

        //seteando vistas
        if (imagenes==null){
            imagen.setImageResource(R.drawable.medicanet1);
            imagen.setVisibility(View.GONE);
        }else{
            imagen.setImageResource(imagenes.getResourceId(position,-1));
        }

        if (vectorCampo1==null){
            tvCampo1.setText("");
            tvCampo1.setVisibility(View.GONE);
        }else{
            tvCampo1.setText(vectorCampo1[position]);
        }

        if (vectorCampo2==null){
            tvCampo2.setText("");
            tvCampo2.setVisibility(View.GONE);
        }else{
            tvCampo2.setText(vectorCampo2[position]);
        }

        if (vectorCampo3==null){
            tvCampo3.setText("");
            tvCampo3.setVisibility(View.GONE);
        }else{
            tvCampo3.setText(vectorCampo3[position]);
        }

        if (vectorCampo4==null){
            tvCampo4.setText("");
            tvCampo4.setVisibility(View.GONE);
        }else{
            tvCampo4.setText(vectorCampo4[position]);
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflar = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflar.inflate(R.layout.item_list_view,parent,false);
        //enlazando vistas
        imagen=view.findViewById(R.id.img_item_list_view);
        tvCampo1=view.findViewById(R.id.tvCampo1_item_list_view);
        tvCampo2=view.findViewById(R.id.tvCampo2_item_list_view);
        tvCampo3=view.findViewById(R.id.tvCampo3_item_list_view);
        tvCampo4=view.findViewById(R.id.tvCampo4_item_list_view);

        //seteando vistas
        if (imagenes==null){
            imagen.setImageResource(R.drawable.medicanet1);
            imagen.setVisibility(View.GONE);
        }else{
            imagen.setImageResource(imagenes.getResourceId(position,-1));
        }

        if (vectorCampo1==null){
            tvCampo1.setText("");
            tvCampo1.setVisibility(View.GONE);
        }else{
            tvCampo1.setText(vectorCampo1[position]);
        }

        if (vectorCampo2==null){
            tvCampo2.setText("");
            tvCampo2.setVisibility(View.GONE);
        }else{
            tvCampo2.setText(vectorCampo2[position]);
        }

        if (vectorCampo3==null){
            tvCampo3.setText("");
            tvCampo3.setVisibility(View.GONE);
        }else{
            tvCampo3.setText(vectorCampo3[position]);
        }

        if (vectorCampo4==null){
            tvCampo4.setText("");
            tvCampo4.setVisibility(View.GONE);
        }else{
            tvCampo4.setText(vectorCampo4[position]);
        }

        return view;
    }
}

