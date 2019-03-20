package com.example.ronaldozelada.xrendir;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.Adaptador;
import com.example.ronaldozelada.xrendir.modelos.Registro;
import com.example.ronaldozelada.xrendir.modelos.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Desembolso extends AppCompatActivity {


    FirebaseFirestore db;
    ListView lista;

    Registro RegistroSelec;
    EditText fechaAproba, DniApro, fechaSol, dni,fechaDesde,fechaHasta,motivo,descripcion,centro,importe,desde,hasta,uid,estado;

    TextView txtNombre;

    usuarios usuarioSelec;

    private List<Registro> listRegistro = new ArrayList<Registro>();
    ArrayAdapter<Registro> arrayAdapterRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desembolso);
        lista = (ListView)findViewById(R.id.Lista);

        db = FirebaseFirestore.getInstance();

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
        dni = (EditText)findViewById(R.id.dni1);
        fechaSol = (EditText)findViewById(R.id.fechaSol);
        DniApro = (EditText)findViewById(R.id.DniApro);
        fechaAproba = (EditText)findViewById(R.id.fechaAproba);

        txtNombre = (TextView) findViewById(R.id.txtNombre);



       usuarioSelec = new usuarios();
        Bundle extras = getIntent().getExtras();
        String Nombres = extras.getString("Nombres");
        String ApeP = extras.getString("ApeP");
        String ApeM = extras.getString("ApeM");
        usuarioSelec.setNombres(Nombres);

        txtNombre.setText(usuarioSelec.getNombres()+" "+ApeP);


        // ListarDatos();
        CargarDatos(this);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                RegistroSelec = (Registro)parent.getItemAtPosition(position);

                motivo.setText(RegistroSelec.getMotivo());
                descripcion.setText(RegistroSelec.getDescripcion());
                centro.setText(RegistroSelec.getCentroCosto());
                importe.setText(RegistroSelec.getImporte());
                desde.setText(RegistroSelec.getFechaDesde());
                hasta.setText(RegistroSelec.getFechaHasta());
                dni.setText(RegistroSelec.getDNI());
                fechaSol.setText(RegistroSelec.getFechaSolicitud());
                DniApro.setText(RegistroSelec.getDniApro());
                fechaAproba.setText(RegistroSelec.getFechaHora());


                uid.setText(RegistroSelec.getUid());
                estado.setText(RegistroSelec.getEstadoSolicitud());


                String Desde = desde.getText().toString();
                String Hasta = hasta.getText().toString();
                String Motivo = motivo.getText().toString();
                String Descripcion = descripcion.getText().toString();
                String CentroCosto = centro.getText().toString();
                String Importe = importe.getText().toString();
                String Dni= dni.getText().toString();
                String FechaSol = fechaSol.getText().toString();
                String Dniapro = DniApro.getText().toString();
                String FechaApro = fechaAproba.getText().toString();



                String Uid = uid.getText().toString();
                String Estado = estado.getText().toString();

                if(Estado.equals("Solicitud_Aprobada")){

                    usuarioSelec = new usuarios();
                    Bundle extras = getIntent().getExtras();
                    String DNI = extras.getString("DNI");
                    String Ruc = extras.getString("Ruc");

                    usuarioSelec.setDNI(DNI);

                    Intent add = new Intent(Desembolso.this, Desembolso_Registro.class);

                    add.putExtra("DNI",DNI);
                    add.putExtra("Ruc",Ruc);

                    add.putExtra("Desde",Desde);
                    add.putExtra("Hasta",Hasta);
                    add.putExtra("Motivo",Motivo);
                    add.putExtra("CentroCosto",CentroCosto);
                    add.putExtra("Descripcion",Descripcion);
                    add.putExtra("Importe",Importe);
                    add.putExtra("Estado",Estado);
                    add.putExtra("Dni",Dni);
                    add.putExtra("FechaSol",FechaSol);
                    add.putExtra("Dniapro",Dniapro);
                    add.putExtra("FechaApro",FechaApro);



                    //  add.putExtra("FechaDesde",FechaDesde);
                    //    add.putExtra("FechaHasta",FechaHasta);

                    add.putExtra("Uid",Uid);


                    startActivity(add);

                }
            }
        });


    }

    @Override
    protected void onRestart() {
        CargarDatos(this);
        super.onRestart();
    }


    public void CargarDatos(final Activity activity)
    {
        usuarioSelec = new usuarios();
        Bundle extras = getIntent().getExtras();
        String DNI = extras.getString("DNI");
        String Ruc = extras.getString("Ruc");

        db.collection("xRendir").whereEqualTo("ruc", Ruc).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Registro> itemList = new ArrayList<>();
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        Registro item = document.toObject(Registro.class);

                        itemList.add(item);
                    }

                    ListView itemListView = (ListView) findViewById(R.id.Lista);



                    Adaptador adaptador = new Adaptador(activity, itemList);

                    adaptador.getFilter().filter("Solicitud_Aprobada");

                    itemListView.setAdapter(adaptador);
                    adaptador.notifyDataSetChanged();


                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void ListarDatos() {

        db.collection("xRendir").orderBy("fechaHora", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listRegistro.clear();

                for(QueryDocumentSnapshot document : task.getResult()) {

                    Registro R= document.toObject(Registro.class);


                    listRegistro.add(R);

                    arrayAdapterRegistro = new ArrayAdapter<Registro>(Desembolso.this, android.R.layout.simple_list_item_1, listRegistro);
                    arrayAdapterRegistro.notifyDataSetChanged();


                    arrayAdapterRegistro.getFilter().filter("Po");

                    lista.setAdapter(arrayAdapterRegistro);

                    // lista.setSelection(listRegistro.size() -1);

                    //Collections.reverse(listRegistro);


                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu03,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.icon_back:
            {
                // Intent add = new Intent(AprobarSolicitud.this, MenuInicio.class);
                //startActivity(add);

                finish();

                Toast.makeText(Desembolso.this, "Atras",Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
}

