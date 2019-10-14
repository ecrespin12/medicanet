package com.example.medicanet.ui.entregaMedicamentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medicanet.R;

import java.util.ArrayList;

public class ConsultasAdapter extends BaseAdapter {
    int layout;
    Context context;
    ArrayList<String> nombres;

    public ConsultasAdapter(int layout, Context context, ArrayList<String> nombres) {
        this.layout = layout;
        this.context = context;
        this.nombres = nombres;
    }

    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int i) {
        return nombres.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if(v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            v = layoutInflater.inflate(layout, null);
        }
        TextView nombre = v.findViewById(R.id.tv_pendientes_entrega);
        nombre.setText(nombres.get(i));

        return v;
    }
}
