package com.example.medicanet.ui.paciente.historialMedico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.medicanet.R;
import java.util.List;
import clasesResponse.HistorialModel;

public class HistorialAdapter extends BaseAdapter {
    private Context context;
    private List<HistorialModel> list;
    TextView txtNom;
    TextView txtDes;
    TextView comoSea;

    public HistorialAdapter(Context contexto,List<HistorialModel> lista){
        this.list = lista;
        this.context = contexto;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item;
        if (convertView == null){
            LayoutInflater inflater =  (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            item = new View(context);
            item = inflater.inflate(R.layout.item_historial_medico, null);
        }else
            item = convertView;

        txtNom = item.findViewById(R.id.textView12);
        txtDes = item.findViewById(R.id.textView13);
        comoSea = item.findViewById(R.id.textView14);



        txtNom.setText("Codigo: " +list.get(position).hme_codigo);
        txtDes.setText("Descripcion: " +list.get(position).hme_descripcion);
        comoSea.setText("Nombre: " +list.get(position).thm_nombre);

        return item;
    }
}
