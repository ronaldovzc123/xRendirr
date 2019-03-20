package com.example.ronaldozelada.xrendir.modelos;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ronaldozelada.xrendir.R;

import java.util.List;

public class Adaptador2 extends ArrayAdapter<liquidacion> {

    private final Activity context;

    public Adaptador2(Activity context, List<liquidacion> object) {


        super(context, 0, object);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView =  (context).getLayoutInflater().inflate(R.layout.elemento_lista2,parent,false);
        }
        liquidacion item = getItem(position);

        TextView Porveedor = (TextView) convertView.findViewById(R.id.txtProveedor);
        Porveedor.setText(item.getProveedor());

        TextView Importe = (TextView) convertView.findViewById(R.id.txtImporte);
        Importe.setText("S/"+ item.getImporte());

        TextView fecha = (TextView) convertView.findViewById(R.id.txtfecha);
        fecha.setText(item.getFechaDoc());

        TextView Motivo = (TextView) convertView.findViewById(R.id.txtCategoria);
        Motivo.setText(item.getCategoria());

        return convertView;
    }
}
