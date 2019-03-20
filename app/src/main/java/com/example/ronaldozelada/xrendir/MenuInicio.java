package com.example.ronaldozelada.xrendir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.usuarios;
import com.google.firebase.firestore.FirebaseFirestore;

public class MenuInicio extends AppCompatActivity {


    FirebaseFirestore db;

    GridLayout mainGrid;

    TextView txtNombre;


   usuarios usuarioSelec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);


        db = FirebaseFirestore.getInstance();

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        txtNombre = (TextView) findViewById(R.id.txtNombre);





        // txtNombre.setText(Nombres);



        //Set Event
        setSingleEvent(mainGrid);

        usuarioSelec = new usuarios();
        Bundle extras = getIntent().getExtras();
        String Nombres = extras.getString("Nombres");
        String ApeP = extras.getString("ApeP");
        String ApeM = extras.getString("ApeM");
        String DNI = extras.getString("DNI");

        usuarioSelec.setNombres(Nombres);
        usuarioSelec.setApellidoP(ApeP);
        usuarioSelec.setApellidoM(ApeM);
        txtNombre.setText(usuarioSelec.getNombres()+" "+usuarioSelec.getApellidoP());









    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu03,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_back: {

                finish();
                break;
            }

        }
        return true;
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle extras = getIntent().getExtras();
                    usuarioSelec = new usuarios();

                      usuarioSelec.setCaja(extras.getString("Caja"));
                      usuarioSelec.setAprobador(extras.getString("Aprobador"));
                      usuarioSelec.setUsuario(extras.getString("Usuario"));


                    String Caja = usuarioSelec.getCaja();
                    String Usuario = usuarioSelec.getUsuario();
                    String Aprobador = usuarioSelec.getAprobador();


                    if( finalI == 0){

                        if(Usuario.equals("si"))
                        {

                    Intent intent = new Intent(MenuInicio.this,xRendir.class);


                    //Mandar Datos Nombre y DNI !!!!!!!!!!!!!!!!!!!!!!
                            usuarioSelec = new usuarios();
                            Bundle g = getIntent().getExtras();
                            String Nombres = g.getString("Nombres");
                            String DNI = extras.getString("DNI");
                            String ApeP = extras.getString("ApeP");
                            String ApeM = extras.getString("ApeM");
                            String Ruc = extras.getString("Ruc");
                            usuarioSelec.setDNI(DNI);
                            usuarioSelec.setApellidoP(ApeP);
                            usuarioSelec.setApellidoM(ApeM);
                            usuarioSelec.setNombres(Nombres);
                            usuarioSelec.setRuc(Ruc);


                            intent.putExtra("Nombres",usuarioSelec.getNombres());
                            intent.putExtra("ApeP",usuarioSelec.getApellidoP());
                            intent.putExtra("ApeM",usuarioSelec.getApellidoM());
                            intent.putExtra("DNI",usuarioSelec.getDNI());
                            intent.putExtra("Ruc",usuarioSelec.getRuc());






                            // intent.putExtra("Nombres",Nombres);
                   // intent.putExtra("info","This is activity from card item index  "+finalI);
                    startActivity(intent);

                        Toast.makeText(MenuInicio.this, "Solicitud", Toast.LENGTH_SHORT).show();
                        }
                        else{ Toast.makeText(MenuInicio.this, "No tiene acceso a este modulo", Toast.LENGTH_SHORT).show();}

                    } else if (finalI == 1)
                        {
                            if(Aprobador.equals("si")) {

                            Intent intent = new Intent(MenuInicio.this, AprobarSolicitud.class);

                                //Mandar Datos Nombre y DNI !!!!!!!!!!!!!!!!!!!!!!


                                usuarioSelec = new usuarios();
                                Bundle g = getIntent().getExtras();
                                String Nombres = g.getString("Nombres");
                                String ApeP = extras.getString("ApeP");
                                String ApeM = extras.getString("ApeM");
                                String DNI = extras.getString("DNI");
                                String Ruc = extras.getString("Ruc");
                                usuarioSelec.setDNI(DNI);
                                usuarioSelec.setNombres(Nombres);
                                usuarioSelec.setApellidoM(ApeM);
                                usuarioSelec.setApellidoP(ApeP);
                                usuarioSelec.setRuc(Ruc);

                                intent.putExtra("Nombres",usuarioSelec.getNombres());
                                intent.putExtra("ApeM",usuarioSelec.getApellidoM());
                                intent.putExtra("ApeP",usuarioSelec.getApellidoP());
                                intent.putExtra("DNI",usuarioSelec.getDNI());
                                intent.putExtra("Ruc",usuarioSelec.getRuc());



                                // intent.putExtra("info","This is activity from card item index  "+finalI);
                            startActivity(intent);
                            Toast.makeText(MenuInicio.this, "Aprobar Solicitud", Toast.LENGTH_SHORT).show();
                        }
                        else{Toast.makeText(MenuInicio.this, "No tiene acceso a este modulo", Toast.LENGTH_SHORT).show();}

                        }else if (finalI == 2){

                        if(Usuario.equals("si"))
                            {

                                //Mandar Datos Nombre y DNI !!!!!!!!!!!!!!!!!!!!!!

                                Intent intent = new Intent(MenuInicio.this,Liquidacion.class);


                                usuarioSelec = new usuarios();
                                Bundle g = getIntent().getExtras();
                                String Nombres = g.getString("Nombres");
                                String ApeP = extras.getString("ApeP");
                                String ApeM = extras.getString("ApeM");
                                String DNI = extras.getString("DNI");
                                String Ruc = extras.getString("Ruc");
                                usuarioSelec.setDNI(DNI);
                                usuarioSelec.setNombres(Nombres);
                                usuarioSelec.setApellidoM(ApeM);
                                usuarioSelec.setApellidoP(ApeP);
                                usuarioSelec.setRuc(Ruc);

                                intent.putExtra("Nombres",usuarioSelec.getNombres());
                                intent.putExtra("ApeM",usuarioSelec.getApellidoM());
                                intent.putExtra("ApeP",usuarioSelec.getApellidoP());
                                intent.putExtra("DNI",usuarioSelec.getDNI());
                                intent.putExtra("Ruc",usuarioSelec.getRuc());

                                startActivity(intent);

                                Toast.makeText(MenuInicio.this, "Liquidacion", Toast.LENGTH_SHORT).show();

                            }  else{Toast.makeText(MenuInicio.this, "No tiene acceso a este modulo", Toast.LENGTH_SHORT).show();}

                    }else if (finalI == 3)
                    {
                        if( Aprobador.equals("si"))
                        {

                            //Mandar Datos Nombre y DNI !!!!!!!!!!!!!!!!!!!!!!

                            Intent intent = new Intent(MenuInicio.this,AprobarLiquidacion.class);


                            usuarioSelec = new usuarios();
                            Bundle g = getIntent().getExtras();
                            String Nombres = g.getString("Nombres");
                            String ApeP = extras.getString("ApeP");
                            String ApeM = extras.getString("ApeM");
                            String DNI = extras.getString("DNI");
                            String Ruc = extras.getString("Ruc");
                            usuarioSelec.setDNI(DNI);
                            usuarioSelec.setNombres(Nombres);
                            usuarioSelec.setApellidoM(ApeM);
                            usuarioSelec.setApellidoP(ApeP);
                            usuarioSelec.setRuc(Ruc);

                            intent.putExtra("Nombres",usuarioSelec.getNombres());
                            intent.putExtra("ApeM",usuarioSelec.getApellidoM());
                            intent.putExtra("ApeP",usuarioSelec.getApellidoP());
                            intent.putExtra("DNI",usuarioSelec.getDNI());
                            intent.putExtra("Ruc",usuarioSelec.getRuc());

                            startActivity(intent);

                        Toast.makeText(MenuInicio.this, "Aprobar Liquidacion", Toast.LENGTH_SHORT).show();

                        }  else{Toast.makeText(MenuInicio.this, "No tiene acceso a este modulo", Toast.LENGTH_SHORT).show();}

                    }
                    else if (finalI == 4)
                    {

                        if(Caja.equals("si")) {
                            Toast.makeText(MenuInicio.this, "Desembolso", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(MenuInicio.this,Desembolso.class);


                            //Mandar Datos Nombre y DNI !!!!!!!!!!!!!!!!!!!!!!


                            usuarioSelec = new usuarios();
                            Bundle g = getIntent().getExtras();
                            String Nombres = g.getString("Nombres");
                            String ApeP = extras.getString("ApeP");
                            String ApeM = extras.getString("ApeM");
                            String DNI = extras.getString("DNI");
                            String Ruc = extras.getString("Ruc");

                            usuarioSelec.setDNI(DNI);
                            usuarioSelec.setNombres(Nombres);
                            usuarioSelec.setApellidoM(ApeM);
                            usuarioSelec.setApellidoP(ApeP);
                            usuarioSelec.setRuc(Ruc);

                            intent.putExtra("Nombres",usuarioSelec.getNombres());
                            intent.putExtra("ApeM",usuarioSelec.getApellidoM());
                            intent.putExtra("ApeP",usuarioSelec.getApellidoP());
                            intent.putExtra("DNI",usuarioSelec.getDNI());
                            intent.putExtra("Ruc",usuarioSelec.getRuc());

                            startActivity(intent);
                        } else{Toast.makeText(MenuInicio.this, "No tiene acceso a este modulo", Toast.LENGTH_SHORT).show();}
                    }

                }
            });
        }
    }
}
