package com.example.ronaldozelada.xrendir;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.Registro;
import com.example.ronaldozelada.xrendir.modelos.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class xRendir_Registro extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db;

    public EditText txt_Empresa,txt_Nombre, fechaHasta, fecha, motivo, descripcion, centro, importe;
    DatePicker date;
    usuarios usuarioSelec;
    private boolean condicion = true;

    LinearLayout layout0,layout1,layout2,layout3,layout4,layout5,layout6;


    private int dia, mes, ano;

    Registro RegistroSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_rendir__registro);

        db = FirebaseFirestore.getInstance();


        motivo = (EditText) findViewById(R.id.txt_Motivo);
        descripcion = (EditText) findViewById(R.id.txt_Descripcion);
        centro = (EditText) findViewById(R.id.txt_CentroCosto);
        importe = (EditText) findViewById(R.id.txt_Importe);
        txt_Nombre = (EditText) findViewById(R.id.txt_Nombre);
        txt_Empresa = (EditText)findViewById(R.id.txt_Empresa);


        fecha = (EditText) findViewById(R.id.txtfechaDesde);
        fechaHasta = (EditText) findViewById(R.id.txtfechaHasta);


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



        usuarioSelec = new usuarios();
        Bundle extras = getIntent().getExtras();
        String DNI = extras.getString("DNI");
        String Nombres = extras.getString("Nombres");
        String ApeP = extras.getString("ApeP");
        String ApeM = extras.getString("ApeM");
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

        txt_Nombre.setText(Nombres + " " + ApeP + " " + ApeM);
        usuarioSelec.setDNI(DNI);


        fecha.setOnClickListener(this);

        fechaHasta.setOnClickListener(this);

        RecibirDatos();


    }

    public void hideSoftKeyboard(View view) {

        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu02, menu);
        return true;
    }


    public void Enviar_dato_Y_salir() {
        //String x="x";

        Intent add = new Intent(xRendir_Registro.this, xRendir.class);
        //  add.putExtra("x",x);
        startActivity(add);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        String Motivo = motivo.getText().toString();
        String Descripcion = descripcion.getText().toString();
        String CentroCosto = centro.getText().toString();
        String Importe = importe.getText().toString();
        String FechaHasta = fechaHasta.getText().toString();
        String FechaDesde = fecha.getText().toString();




        switch (item.getItemId()) {
            case R.id.icon_save: {
                if (Validacion() == false) {

                    Toast.makeText(this, "completar campos obligatorios",Toast.LENGTH_SHORT).show();


                } else {



                    usuarioSelec = new usuarios();
                    Bundle extras = getIntent().getExtras();
                    String DNI = extras.getString("DNI");
                    String Ruc = extras.getString("Ruc");
                    usuarioSelec.setDNI(DNI);


                    String Uid = extras.getString("Uid");

                    String Desded = extras.getString("Desde");
                    String Hastad = extras.getString("Hasta");
                    String Motivod = extras.getString("Motivo");
                    String CentroCostod = extras.getString("CentroCosto");
                    String Descripciond = extras.getString("Descripcion");
                    String Imported = extras.getString("Importe");
                    String Estadod = extras.getString("Estado");
                    String FechaDesded = extras.getString("FechaDesde");
                    String FechaHastad = extras.getString("FechaHasta");

                    if (Estadod.equals("Registrado") || Estadod.equals("Solicitud_Rechazada")) {

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

                        r.setMotivo(motivo.getText().toString().trim());
                        r.setCentroCosto(centro.getText().toString().trim());
                        r.setDescripcion(descripcion.getText().toString().trim());
                        r.setImporte(importe.getText().toString().trim());
                        r.setFechaHasta(fechaHasta.getText().toString().trim());
                        r.setFechaDesde(fecha.getText().toString().trim());
                        r.setNombres_Apellidos(txt_Nombre.getText().toString().trim());
                        r.setFecha(Fecha);
                        r.setRuc(Ruc);
                        r.setFechaHora(fechashora);
                        r.setHora(Hora);
                        r.setDNI(DNI);

                        r.setEstadoSolicitud("Registrado");


                        limpiarCajas();
                        db.collection("xRendir").document(Uid).set(r);
                        Toast.makeText(this, "Grabado-Actualizado", Toast.LENGTH_SHORT).show();


                        finish();


                        //Enviar_dato_Y_salir();
                        //Intent add = new Intent(xRendir_Registro.this, xRendir.class);
                        //startActivity(add);
                    } else {

                        //Fecha
                        long date = System.currentTimeMillis();
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
                        String Fecha = sdf.format(date);

                        long hora = System.currentTimeMillis();
                        SimpleDateFormat sss = new SimpleDateFormat("hh-mm-ss a");
                        String Hora = sss.format(hora);

                        long fechahora = System.currentTimeMillis();
                        SimpleDateFormat fec = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
                        String fechashora = fec.format(fechahora);


                        //poblar Datos
                        Registro r = new Registro();
                        r.setHora(Hora);
                        r.setFecha(Fecha);
                        r.setUid(UUID.randomUUID().toString());
                        r.setMotivo(Motivo);
                        r.setCentroCosto(CentroCosto);
                        r.setDescripcion(Descripcion);
                        r.setNombres_Apellidos(txt_Nombre.getText().toString());
                        r.setImporte(Importe);
                        r.setRuc(Ruc);
                        r.setFechaHora(fechashora);
                        r.setFechaHasta(FechaHasta);
                        r.setFechaDesde(FechaDesde);
                        r.setDNI(DNI);
                        r.setEstadoSolicitud("Registrado");

                        limpiarCajas();


                        db.collection("xRendir").document(r.getUid()).set(r);
                        Toast.makeText(this, "Grabado", Toast.LENGTH_SHORT).show();


                        finish();
                        //Intent add = new Intent(xRendir_Registro.this, xRendir.class);
                        // startActivity(add);
                    }
                }

                break;

            }

            case R.id.icon_sent: {
                if (Validacion() == false) {

                    Toast.makeText(this, "completar campos obligatorios",Toast.LENGTH_SHORT).show();

                } else {

                    usuarioSelec = new usuarios();
                    Bundle extras = getIntent().getExtras();
                    String DNI = extras.getString("DNI");
                    String Ruc = extras.getString("Ruc");
                    usuarioSelec.setDNI(DNI);


                    String Uid = extras.getString("Uid");

                    String Desded = extras.getString("Desde");
                    String Hastad = extras.getString("Hasta");
                    String Motivod = extras.getString("Motivo");
                    String CentroCostod = extras.getString("CentroCosto");
                    String Descripciond = extras.getString("Descripcion");
                    String Imported = extras.getString("Importe");
                    String Estadod = extras.getString("Estado");
                    String FechaDesded = extras.getString("FechaDesde");
                    String FechaHastad = extras.getString("FechaHasta");

                    if (Estadod.equals("Registrado") || Estadod.equals("Solicitud_Rechazada")) {

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

                        r.setMotivo(motivo.getText().toString().trim());
                        r.setCentroCosto(centro.getText().toString().trim());
                        r.setDescripcion(descripcion.getText().toString().trim());
                        r.setImporte(importe.getText().toString().trim());
                        r.setFecha(Fecha);
                        r.setHora(Hora);
                        r.setFechaHasta(fechaHasta.getText().toString().trim());
                        r.setFechaDesde(fecha.getText().toString().trim());
                        r.setFechaHora(fechashora);
                        r.setNombres_Apellidos(txt_Nombre.getText().toString().trim());
                        r.setRuc(Ruc);
                        r.setDNI(DNI);

                        r.setEstadoSolicitud("Por_Aprobar_Solicitud");

                        limpiarCajas();
                        db.collection("xRendir").document(Uid).set(r);

                        //Intent add = new Intent(xRendir_Registro.this, xRendir.class);
                        //startActivity(add);

                        Toast.makeText(this, "Por Aprobar-Actualizado", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {

                        //Fecha
                        long date = System.currentTimeMillis();
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
                        String Fecha = sdf.format(date);

                        long hora = System.currentTimeMillis();
                        SimpleDateFormat sss = new SimpleDateFormat("hh-mm-ss a");
                        String Hora = sss.format(hora);

                        long fechahora = System.currentTimeMillis();
                        SimpleDateFormat fec = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
                        String fechashora = fec.format(fechahora);


                        //poblar Datos
                        Registro r = new Registro();
                        r.setHora(Hora);
                        r.setFecha(Fecha);
                        r.setUid(UUID.randomUUID().toString());
                        r.setMotivo(Motivo);
                        r.setCentroCosto(CentroCosto);
                        r.setDescripcion(Descripcion);

                        r.setImporte(Importe);
                        r.setRuc(Ruc);
                        r.setFechaHora(fechashora);
                        r.setFechaHasta(FechaHasta);
                        r.setFechaDesde(FechaDesde);
                        r.setNombres_Apellidos(txt_Nombre.getText().toString());
                        r.setEstadoSolicitud("Por_Aprobar_Solicitud");
                        r.setDNI(DNI);

                        limpiarCajas();
                        db.collection("xRendir").document(r.getUid()).set(r);


                        Toast.makeText(this, "Por Aprobar", Toast.LENGTH_SHORT).show();

                        // Intent add = new Intent(xRendir_Registro.this, xRendir.class);
                        //startActivity(add);

                        finish();

                    }
                }


                break;
            }
            case R.id.icon_cancel: {
                finish();

                //  Intent add = new Intent(xRendir_Registro.this, xRendir.class);
                // startActivity(add);

                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                break;
            }


        }
        return true;
    }

    private void limpiarCajas() {

        motivo.setText("");
        descripcion.setText("");
        centro.setText("");
        importe.setText("");
        fecha.setText("");
        fechaHasta.setText("");
    }

    private boolean Validacion() {
        String Motivo = motivo.getText().toString();
        String Descripcion = descripcion.getText().toString();
        String CentroCosto = centro.getText().toString();

        String FechaHasta = fechaHasta.getText().toString();
        String FechaDesde = fecha.getText().toString();

        String Importe = importe.getText().toString();

        String coma = ",";
        char[] vector = Importe.toCharArray();
        for (int a = 0; a < Importe.length(); a++) {
            String error = String.valueOf(vector[a]);
            if (coma.equalsIgnoreCase(error)) {
                importe.setError("Error: Ingrese '.'(Punto)como decimal");
                Toast.makeText(this, "Error: Ingrese '.'(Punto)como decimal ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

                if (Motivo.equals("") || Descripcion.equals("") || CentroCosto.equals("") || Importe.equals("")) {

                    if (Motivo.equals("")) {
                        motivo.setError("Campo Oblogatorio");
                        Toast.makeText(this, "Por favor ingrese un Motivo", Toast.LENGTH_SHORT).show();
                    } else if (Descripcion.equals("")) {
                        descripcion.setError(("Campo Oblogatorio"));
                        Toast.makeText(this, "Por favor ingrese una Descripcion", Toast.LENGTH_SHORT).show();
                    } else if (CentroCosto.equals("")) {
                        centro.setError(("Campo Oblogatorio"));
                        Toast.makeText(this, "Por favor ingrese un Centro Costo", Toast.LENGTH_SHORT).show();
                    } else if (Importe.equals("")) {
                        importe.setError(("Campo Oblogatorio"));
                        Toast.makeText(this, "Por favor ingrese un Importe", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                } else
                {
                    return  true;
                }
        }



    private void RecibirDatos() {
        //Recibir Datos

        Bundle extras = getIntent().getExtras();
        String Uid = extras.getString("Uid");

        String Desde = extras.getString("Desde");
        String Hasta = extras.getString("Hasta");
        String Motivo = extras.getString("Motivo");
        String CentroCosto = extras.getString("CentroCosto");
        String Descripcion = extras.getString("Descripcion");
        String Importe = extras.getString("Importe");


        //poblar Datos




        motivo.setText(Motivo);
        centro.setText(CentroCosto);
        descripcion.setText(Descripcion);
        importe.setText(Importe);
        fecha.setText(Desde);
        fechaHasta.setText(Hasta);




        /*
        Registro r = new Registro();
        r.setUid(Uid);
        r.setDesde(desde.getText().toString().trim());
        r.setHasta(hasta.getText().toString().trim());
        r.setMotivo(motivo.getText().toString().trim());
        r.setCentroCosto(centro.getText().toString().trim());
        r.setDescripcion(descripcion.getText().toString().trim());
        r.setImporte(importe.getText().toString().trim());

        */



    }

    @Override
    public void onClick(View v) {

        if(v==fecha)
        {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    fecha.setText(dayOfMonth+"/" +(month+1)+"/"+year);


                }
            }
            ,ano,mes,dia);

            datePickerDialog.show();
        }

        if(v==fechaHasta)
        {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view,  int year, int month, int dayOfMonth) {

                    //int year, int month, int dayOfMonth



                    fechaHasta.setText(dayOfMonth+"/" +(month+1)+"/"+year);



                }
            }
                     ,ano,mes,dia);

            datePickerDialog.show();
        }
    }
}
