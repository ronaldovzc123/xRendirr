package com.example.ronaldozelada.xrendir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.Registro;
import com.example.ronaldozelada.xrendir.modelos.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class Desembolso_Registro extends AppCompatActivity {


    FirebaseFirestore db;

    public EditText txt_Empresa, nombres, fechaHasta, fecha, motivo,descripcion,centro,importe;
    usuarios usuarioSelec;
    LinearLayout layout0,layout1,layout2,layout3,layout4,layout5,layout6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desembolso__registro);
        db = FirebaseFirestore.getInstance();

        motivo = (EditText)findViewById(R.id.txt_Motivo);
        descripcion = (EditText)findViewById(R.id.txt_Descripcion);
        centro = (EditText)findViewById(R.id.txt_CentroCosto);
        importe=(EditText)findViewById(R.id.txt_Importe);

        fecha = (EditText)findViewById(R.id.txtfechaDesde);
        fechaHasta= (EditText)findViewById(R.id.txtfechaHasta);

        nombres = (EditText)findViewById(R.id.txt_Nombres);

        txt_Empresa = (EditText)findViewById(R.id.txt_Empresa);


        Bundle extras = getIntent().getExtras();
        String Ruc = extras.getString("Ruc");

        final DocumentReference docRef = db.collection("Empresas").document(Ruc);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            private static final String TAG = "Mensaje";
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        String Nombre = document.getString("Nombre");

                        txt_Empresa.setText(Nombre);
                    }
                }
            }
        });










        layout0 = (LinearLayout) findViewById(R.id.L0);
        layout1 = (LinearLayout) findViewById(R.id.L1);
        layout2 = (LinearLayout) findViewById(R.id.L2);
        layout3 = (LinearLayout) findViewById(R.id.L3);
        layout4 = (LinearLayout) findViewById(R.id.L4);
        layout5 = (LinearLayout) findViewById(R.id.L5);
        layout6 = (LinearLayout) findViewById(R.id.L6);

        layout0.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        layout1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        layout2.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        layout3.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        layout4.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        layout5.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        layout6.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });


        RecibirDatos();

    }

    public void hideSoftKeyboard(View view) {

        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu05,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Desembolsar: {

                usuarioSelec = new usuarios();
                Bundle extras = getIntent().getExtras();
                String DNI = extras.getString("DNI");
                String Ruc = extras.getString("Ruc");
                usuarioSelec.setDNI(DNI);

                Bundle extras2 = getIntent().getExtras();
                String FechaSol = extras2.getString("FechaSol");

                Bundle extras3 = getIntent().getExtras();
                String Dni = extras3.getString("Dni");

                Bundle extras4 = getIntent().getExtras();
                String Dniapro = extras4.getString("Dniapro");

                Bundle extras5 = getIntent().getExtras();
                String FechaApro = extras5.getString("FechaApro");



                String Uid = extras.getString("Uid");

                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
                String Fecha = sdf.format(date);

                long hora = System.currentTimeMillis();
                SimpleDateFormat sss = new SimpleDateFormat("hh-mm-ss a");
                String Hora = sss.format(hora);

                long fechahora = System.currentTimeMillis();
                SimpleDateFormat fec = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
                String fechashora = fec.format(fechahora);


                Registro r = new Registro();

                r.setUid(Uid);
                r.setDniApro(Dniapro);
                r.setMotivo(motivo.getText().toString().trim());
                r.setCentroCosto(centro.getText().toString().trim());
                r.setDescripcion(descripcion.getText().toString().trim());
                r.setImporte(importe.getText().toString().trim());
                r.setFechaHasta(fechaHasta.getText().toString().trim());
                r.setFechaDesde(fecha.getText().toString().trim());
                r.setDNI(Dni);
                r.setFecha(Fecha);
                r.setRuc(Ruc);
                r.setFechaSolicitud(FechaSol);
                r.setFechaAprobador(FechaApro);
                r.setFechaHora(fechashora);
                r.setNombres_Apellidos(nombres.getText().toString());
                r.setHora(Hora);
                r.setDniAproDesembol(DNI);

                r.setEstadoSolicitud("Por_Liquidar");

                db.collection("xRendir").document(Uid).set(r);
                Toast.makeText(this, "Desembolsado", Toast.LENGTH_SHORT).show();

                finish();
                //Intent add = new Intent(AprobarSolicitud_Registro.this, AprobarSolicitud.class);
                // startActivity(add);


                break;
            }


            case R.id.icon_cancel:{
                finish();

                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();

                //  Intent add = new Intent(AprobarSolicitud_Registro.this, AprobarSolicitud.class);
                // startActivity(add);
                break;
            }


        }

        return true;
    }




    private void RecibirDatos() {

        Bundle extras = getIntent().getExtras();
        String Uid = extras.getString("Uid");

        String Desde = extras.getString("Desde");
        String Hasta = extras.getString("Hasta");
        String Motivo = extras.getString("Motivo");
        String CentroCosto = extras.getString("CentroCosto");
        String Descripcion = extras.getString("Descripcion");
        String Importe = extras.getString("Importe");
        String DNI = extras.getString("Dni");
        String FechaSol = extras.getString("FechaSol");
        String Dniapro = extras.getString("Dniapro");



        motivo.setText(Motivo);
        centro.setText(CentroCosto);
        descripcion.setText(Descripcion);
        importe.setText(Importe);
        fecha.setText(Desde);
        fechaHasta.setText(Hasta);
        //Dni.setText(DNI);


        final DocumentReference docRef = db.collection("usuarios").document(DNI);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            private static final String TAG = "Mensaje";

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){

                        String Nombres = document.getString("nombres");
                        String ApellidoP = document.getString("apellido_paterno");
                        String ApellidoM = document.getString("apellido_materno");

                        nombres.setText(Nombres +" "+ApellidoP+" "+ApellidoM);
                    }  else {
                        Log.d(TAG, "No such document");

                        Toast.makeText(Desembolso_Registro.this, "DNI No Registrado", Toast.LENGTH_SHORT).show();
                    }

                }else { Log.d(TAG, "get failed with ", task.getException());}
            }
        });






















    }
}
