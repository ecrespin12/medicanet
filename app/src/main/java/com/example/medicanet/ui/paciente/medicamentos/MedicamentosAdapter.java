package com.example.medicanet.ui.paciente.medicamentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.medicanet.R;
import java.util.List;
import clasesResponse.MedicamentosModel;

public class MedicamentosAdapter extends BaseAdapter {
    private Context context;
    private List<MedicamentosModel> list;
    TextView txtNom;
    TextView txtDes;

    public MedicamentosAdapter(Context contexto,List<MedicamentosModel> lista){
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
            item = inflater.inflate(R.layout.item_list_view, null);
        }else
            item = convertView;

        txtNom = item.findViewById(R.id.tvCampo1_item_list_view);
        txtDes = item.findViewById(R.id.tvCampo2_item_list_view);

        txtNom.setText(list.get(position).mdc_nombre);
        txtDes.setText(list.get(position).mdc_descripcion);

        return item;
    }
}
