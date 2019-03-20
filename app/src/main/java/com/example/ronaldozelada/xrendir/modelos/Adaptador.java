package com.example.ronaldozelada.xrendir.modelos;

import android.app.Activity;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ronaldozelada.xrendir.R;

import java.util.List;

public class Adaptador extends ArrayAdapter<Registro> {

    private final Activity context;

    public Adaptador(Activity context, List<Registro> object) {
        super(context, 0, object);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView =  (context).getLayoutInflater().inflate(R.layout.elementos_listas,parent,false);
        }
        Registro item = getItem(position);

        TextView Porveedor = (TextView) convertView.findViewById(R.id.txtProveedor);
        Porveedor.setText(item.getEstadoSolicitud());

        TextView Importe = (TextView) convertView.findViewById(R.id.txtImporte);
        Importe.setText("S/"+ item.getImporte());

        TextView fecha = (TextView) convertView.findViewById(R.id.txtfecha);
        fecha.setText(item.getFecha());

        TextView Motivo = (TextView) convertView.findViewById(R.id.txtMotivo);
        Motivo.setText(item.getMotivo());

        TextView Nombre = (TextView) convertView.findViewById(R.id.txtNombre);
        Nombre.setText(item.getNombres_Apellidos());

        return convertView;
    }

    }