package com.example.ronaldozelada.xrendir;

import android.app.Activity;
import android.content.Intent;
import android.media.effect.Effect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.Adaptador;
import com.example.ronaldozelada.xrendir.modelos.Registro;
import com.example.ronaldozelada.xrendir.modelos.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class xRendir extends AppCompatActivity {

    private static final String TAG ="Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" ;
    FirebaseFirestore db;
    ListView lista;
    int x;
    Registro RegistroSelec;
    EditText fechaDesde,fechaHasta,motivo,descripcion,centro,importe,desde,hasta,uid,estado;
    TextView txtNombre;
    usuarios usuarioSelec;



    private List<Registro> listRegistro = new ArrayList<Registro>();
    ArrayAdapter<Registro> arrayAdapterRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_rendir);
        db = FirebaseFirestore.getInstance();


        lista = (ListView)findViewById(R.id.Lista);

        motivo = (EditText)findViewById(R.id.motivo);
        descripcion = (EditText)findViewById(R.id.descripcion);
        centro = (EditText)findViewById(R.id.centro);
        importe=(EditText)findViewById(R.id.importe);
        desde = (EditText)findViewById(R.id.desde);
        hasta = (EditText)findViewById(R.id.hasta);
        uid = (EditText)findViewById(R.id.id);
        estado = (EditText)findViewById(R.id.estado);
        fechaHasta =(EditText)findViewById(R.id.txtfechaHasta);
        fechaDesde = (EditText)findViewById(R.id.txtfechaDesde);

        txtNombre = (TextView)findViewById(R.id.txtNombre);




        usuarioSelec = new usuarios();
        Bundle extras = getIntent().getExtras();
        String Nombres = extras.getString("Nombres");
        String ApeP = extras.getString("ApeP");
        String ApeM = extras.getString("ApeM");
        String DNI = extras.getString("DNI");
        usuarioSelec.setDNI(DNI);
        usuarioSelec.setNombres(Nombres);

       txtNombre.setText(usuarioSelec.getNombres()+" " + ApeP);


        //String Nombres = extras.getString("Nombres");
       // RecibirDatoYactualizarLista();

       // ListarDatos();


        CargarDatos(this);

        //CargarDatos(this);




        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





                RegistroSelec = (Registro)parent.getItemAtPosition(position);
              //  String  motivo,descripcion,centro,importe,desde,hasta;

                motivo.setText(RegistroSelec.getMotivo());
                descripcion.setText(RegistroSelec.getDescripcion());
                centro.setText(RegistroSelec.getCentroCosto());
                importe.setText(RegistroSelec.getImporte());
                desde.setText(RegistroSelec.getFechaDesde());
                hasta.setText(RegistroSelec.getFechaHasta());


            //   fechaDesde.setText(RegistroSelec.getFechaDesde());
              // fechaHasta.setText(RegistroSelec.getFechaHasta());


                uid.setText(RegistroSelec.getUid());
                estado.setText(RegistroSelec.getEstadoSolicitud());


                String Desde = desde.getText().toString();
                String Hasta = hasta.getText().toString();
                String Motivo = motivo.getText().toString();
                String Descripcion = descripcion.getText().toString();
                String CentroCosto = centro.getText().toString();
                String Importe = importe.getText().toString();
              //  String FechaDesde = fechaDesde.getText().toString();
             //   String FechaHasta = fechaHasta.getText().toString();

                String Uid = uid.getText().toString();
                String Estado = estado.getText().toString();

                if(Estado.equals("Registrado") || Estado.equals("Solicitud_Rechazada")){


                    usuarioSelec = new usuarios();
                    Bundle extras = getIntent().getExtras();
                    String DNI = extras.getString("DNI");
                    String Nombres = extras.getString("Nombres");
                    String ApeP = extras.getString("ApeP");
                    String ApeM = extras.getString("ApeM");
                    String Ruc = extras.getString("Ruc");
                    usuarioSelec.setDNI(DNI);



                    Intent add = new Intent(xRendir.this, xRendir_Registro.class);

                    add.putExtra("DNI",DNI);

                   add.putExtra("Desde",Desde);
                    add.putExtra("Hasta",Hasta);
                    add.putExtra("Motivo",Motivo);
                    add.putExtra("CentroCosto",CentroCosto);
                    add.putExtra("Descripcion",Descripcion);
                    add.putExtra("Importe",Importe);
                    add.putExtra("Estado",Estado);
                    add.putExtra("Nombres",Nombres);
                    add.putExtra("ApeP",ApeP);
                    add.putExtra("ApeM",ApeM);
                    add.putExtra("Ruc",Ruc);

                    add.putExtra("Uid",Uid);


                    startActivity(add);




                }




            }
        });



    }
/*
    @Override
    protected void onStart() {
        CargarDatos2(this);
        super.onStart();
    }*/

   @Override
    protected void onRestart() {
        CargarDatos(this);
        super.onRestart();
    }

    public void RecibirDatoYactualizarLista()
    {

       Bundle extras = getIntent().getExtras();
       String  x = extras.getString("x");
        if(x.equals("x"))
        {
            ListarDatos();
        }else
            {
                ListarDatos();
            }
    }

    public void ListarDatos() {

        db.collection("xRendir").orderBy("fechaHora", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listRegistro.clear();

                for(QueryDocumentSnapshot document : task.getResult()) {

                    Registro R= document.toObject(Registro.class);
                    listRegistro.add(R);

                    arrayAdapterRegistro = new ArrayAdapter<Registro>(xRendir.this, android.R.layout.simple_list_item_1, listRegistro);
                    arrayAdapterRegistro.notifyDataSetChanged();

                    lista.setAdapter(arrayAdapterRegistro);

                  // lista.setSelection(listRegistro.size() -1);

                    //Collections.reverse(listRegistro);


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu01,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.icon_add:
                {
                   // Intent a = new Intent(xRendir.this, xRendir_Registro.class);
                    //startActivity(a);


                    Intent add = new Intent(xRendir.this, xRendir_Registro.class);

                    usuarioSelec = new usuarios();
                    Bundle extras = getIntent().getExtras();
                    String DNI = extras.getString("DNI");
                    String Nombres = extras.getString("Nombres");
                    String ApeP = extras.getString("ApeP");
                    String ApeM = extras.getString("ApeM");
                    String Ruc = extras.getString("Ruc");

                    usuarioSelec.setDNI(DNI);
                    add.putExtra("DNI",DNI);
                    add.putExtra("Nombres",Nombres);
                    add.putExtra("ApeP",ApeP);
                    add.putExtra("ApeM",ApeM);
                    add.putExtra("Ruc",Ruc);



                    String Desde ="";
                    String Hasta ="";
                    String Motivo ="";
                    String CentroCosto ="";
                    String Descripcion ="";
                    String Importe ="";
                    String Estado ="";
                    String FechaDesde = "";
                    String FechaHasta = "";

                    String Uid ="";


                    add.putExtra("Desde",Desde);
                    add.putExtra("Hasta",Hasta);
                    add.putExtra("Motivo",Motivo);
                    add.putExtra("CentroCosto",CentroCosto);
                    add.putExtra("Descripcion",Descripcion);
                    add.putExtra("Importe",Importe);
                    add.putExtra("Estado",Estado);
                    add.putExtra("FechaDesde",FechaDesde);
                    add.putExtra("FechaHasta",FechaHasta);


                    add.putExtra("Uid",Uid);

                    startActivity(add);

                    Toast.makeText( xRendir.this, "Nuevo Registro" ,Toast.LENGTH_SHORT).show();


                    break;


                }
            case R.id.icon_back:
                {
                    //Intent add = new Intent(xRendir.this, MenuInicio.class);
                    /*String Nombres = "";
                    add.putExtra("Nombres",Nombres);*/
                   // startActivity(add);



                    finish();


                    Toast.makeText(xRendir.this, "Atras",Toast.LENGTH_SHORT).show();
                    break;
                }


        }
        return true;
    }


    public void CargarDatos(final Activity activity)
    {
        usuarioSelec = new usuarios();
        Bundle extras = getIntent().getExtras();
        String DNI = extras.getString("DNI");

        db.collection("xRendir").whereEqualTo("dni", DNI).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            private static final String TAG = "Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

             //   db.collection("xRendir").orderBy("fechaHora", Query.Direction.DESCENDING);

                ArrayList<Registro> itemList = new ArrayList<>();

                Adaptador adaptador = new Adaptador(activity, itemList);

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Registro item = document.toObject(Registro.class);
                        itemList.add(item);

                    }

                    ListView itemListView = (ListView) findViewById(R.id.Lista);

                    itemListView.invalidateViews();

                    itemListView.setAdapter(adaptador);

                    adaptador.notifyDataSetChanged();
                } else{Log.d(TAG, "No Funciono");}
        }
    });
    }

}
