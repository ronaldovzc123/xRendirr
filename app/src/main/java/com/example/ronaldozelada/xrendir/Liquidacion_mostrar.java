package com.example.ronaldozelada.xrendir;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Liquidacion_mostrar extends AppCompatActivity {


    EditText txtRUC,txt_Proveedor,txtFechaDoc,txt_NumeroDoc,spTipoDoc,txtImp,TipoCat,txtDescripcion;
    LinearLayout layout0,layout1,layout2,layout3,layout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquidacion_mostrar);

        txtRUC = (EditText)findViewById(R.id.txtRUC);
        txt_Proveedor = (EditText)findViewById(R.id.txt_Proveedor);
        txtFechaDoc = (EditText)findViewById(R.id.txtFechaDoc);
        txt_NumeroDoc = (EditText)findViewById(R.id.txt_NumeroDoc);
        spTipoDoc = (EditText)findViewById(R.id.spTipoDoc);
        txtImp = (EditText)findViewById(R.id.txtImp);
        TipoCat = (EditText)findViewById(R.id.TipoCat);
        txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);

        layout0 = (LinearLayout) findViewById(R.id.L0);
        layout1 = (LinearLayout) findViewById(R.id.L1);
        layout2 = (LinearLayout) findViewById(R.id.L2);
        layout3 = (LinearLayout) findViewById(R.id.L3);
        layout4 = (LinearLayout) findViewById(R.id.L4);

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



        Bundle extras = getIntent().getExtras();
        String ruc = extras.getString("ruc");
        String proveedor = extras.getString("proveedor");
        String fecha = extras.getString("fecha");
        String numdoc = extras.getString("numdoc");
        String tipodoc = extras.getString("tipodoc");
        String importe = extras.getString("importe");
        String catergoria = extras.getString("catergoria");
        String descripcion = extras.getString("descripcion");

                txtRUC.setText(ruc);
                txt_Proveedor.setText(proveedor);
                txtFechaDoc.setText(fecha);
                txt_NumeroDoc.setText(numdoc);
                spTipoDoc.setText(tipodoc);
                txtImp.setText(importe);
                TipoCat.setText(catergoria);
                txtDescripcion.setText(descripcion);

    }

    public void hideSoftKeyboard(View view) {

        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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

                Toast.makeText(Liquidacion_mostrar.this, "Atras",Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
}
