package com.example.ronaldozelada.xrendir;

import android.app.Activity;
import android.content.ClipData;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.Adaptador;
import com.example.ronaldozelada.xrendir.modelos.Adaptador2;
import com.example.ronaldozelada.xrendir.modelos.Registro;
import com.example.ronaldozelada.xrendir.modelos.liquidacion;
import com.example.ronaldozelada.xrendir.modelos.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Liquidacion_documentos extends AppCompatActivity {


    private static final String TAG = "HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    FirebaseFirestore db;
    ListView lista;
    liquidacion liquidacionSelec;
    TextView txtNombre;
   public EditText DniAroLiqu,fecHas,fecDes,centroCost,Descrip,dni2,dnApr,DniAprodese,estadoSol,fechaaa,fechaAproa,fechaHORA,fechaSOLL,hora,impr,mot,nomb,uidd ;
    usuarios usuarioSelec;


    private List<liquidacion> listliquidacion = new ArrayList<liquidacion>();
    ArrayAdapter<liquidacion> arrayliquidacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquidacion_documentos);

        db = FirebaseFirestore.getInstance();
        lista = (ListView) findViewById(R.id.Lista);

        centroCost = (EditText)findViewById(R.id.centroCost);
        Descrip = (EditText)findViewById(R.id.Descrip);
        dni2 = (EditText)findViewById(R.id.dni2);
        dnApr = (EditText)findViewById(R.id.dnApr);
        DniAprodese = (EditText)findViewById(R.id.DniAprodese);
        estadoSol = (EditText)findViewById(R.id.estadoSol);
        fechaaa = (EditText)findViewById(R.id.fechaaa);
        fechaAproa = (EditText)findViewById(R.id.fechaAproa);
        fechaHORA = (EditText)findViewById(R.id.fechaHORA);
        fechaSOLL = (EditText)findViewById(R.id.fechaSOLL);
        hora = (EditText)findViewById(R.id.hora);
        impr = (EditText)findViewById(R.id.impr);
        mot = (EditText)findViewById(R.id.mot);
        nomb = (EditText)findViewById(R.id.nomb);
        uidd = (EditText)findViewById(R.id.uidd);
        fecHas = (EditText)findViewById(R.id.fecHas);
        fecDes = (EditText)findViewById(R.id.fecDes);
        DniAroLiqu = (EditText)findViewById(R.id.DniAroLiqu);
        txtNombre = (TextView) findViewById(R.id.txtNombre);



        LinearLayout layout0 = (LinearLayout) findViewById(R.id.L0);

        layout0.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });







        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                liquidacionSelec = (liquidacion) parent.getItemAtPosition(position);

                centroCost.setText(liquidacionSelec.getRuc());
                Descrip.setText(liquidacionSelec.getProveedor());
                dni2.setText(liquidacionSelec.getFechaDoc());
                dnApr.setText(liquidacionSelec.getNumdoc());
                DniAprodese.setText(liquidacionSelec.getTipoDoc());
                estadoSol.setText(liquidacionSelec.getImporte());
                fechaaa.setText(liquidacionSelec.getCategoria());
                fechaAproa.setText(liquidacionSelec.getDescripcion());

                String ruc = centroCost.getText().toString();
                String proveedor = Descrip.getText().toString();
                String fecha = dni2.getText().toString();
                String numdoc = dnApr.getText().toString();
                String tipodoc = DniAprodese.getText().toString();
                String importe = estadoSol.getText().toString();
                String catergoria = fechaaa.getText().toString();
                String descripcion = fechaAproa.getText().toString();

                Intent add = new Intent(Liquidacion_documentos.this, Liquidacion_mostrar.class);
               add.putExtra("ruc",ruc);
                add.putExtra("proveedor",proveedor);
                add.putExtra("fecha",fecha);
                add.putExtra("numdoc",numdoc);
                add.putExtra("tipodoc",tipodoc);
                add.putExtra("importe",importe);
                add.putExtra("catergoria",catergoria);
                add.putExtra("descripcion",descripcion);

                startActivity(add);
            }

        });










                descargar_datos();












        Bundle extras = getIntent().getExtras();
        String DNI = extras.getString("DNI");
        String Nombres = extras.getString("Nombres");
        String ApeP = extras.getString("ApeP");
       txtNombre.setText(Nombres +" "+ApeP);

        CargarDatos(this);


/*
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent add = new Intent(Liquidacion_documentos.this, Liquidacion_Registro.class);
                startActivity(add);
            }
        });
*/


    }

    @Override
    protected void onRestart() {
        CargarDatos(this);
        super.onRestart();
    }

    public void hideSoftKeyboard(View view) {

        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public void CargarDatos(final Activity activity)
    {

        Bundle extras = getIntent().getExtras();

        String Uid = extras.getString("Uid");

        Log.d(TAG, Uid);


//.orderBy("fechaHora", Query.Direction.DESCENDING)

        //.whereEqualTo("idSolicitud",Uid)
        db.collection("Liquidacion").whereEqualTo("idSolicitud",Uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<liquidacion> itemList = new ArrayList<>();
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        liquidacion item = document.toObject(liquidacion.class);

                        itemList.add(item);
                    }

                    ListView itemListView = (ListView) findViewById(R.id.Lista);



                    Adaptador2 adaptador2 = new Adaptador2(activity, itemList);

                    //adaptador2.getFilter().filter("Aprobar");

                    itemListView.setAdapter(adaptador2);
                    adaptador2.notifyDataSetChanged();


                } else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu06,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_add: {
                Bundle extras = getIntent().getExtras();
                String Uid = extras.getString("Uid");

                Intent add = new Intent(Liquidacion_documentos.this, Liquidacion_Registro.class);
                add.putExtra("Uid", Uid);
                startActivity(add);

                break;
            }
            case R.id.icon_sent: {

                Bundle extras = getIntent().getExtras();
                String Uid = extras.getString("Uid");
                String Ruc = extras.getString("Ruc");

                Toast.makeText(this, "Enviar Aprobar", Toast.LENGTH_SHORT).show();
               Registro r = new Registro();



              /*  long fechahora = System.currentTimeMillis();
                SimpleDateFormat fec = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
                String fechashora = fec.format(fechahora);

                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
                String Fecha = sdf.format(date);

                long horaActual = System.currentTimeMillis();
                SimpleDateFormat sss = new SimpleDateFormat("hh-mm-ss a");
               String HoraActual = sss.format(horaActual);*/

                r.setUid(Uid);
                r.setEstadoSolicitud("Por_Aprobar_Liquidacion");
                r.setCentroCosto(centroCost.getText().toString());
                r.setDescripcion(Descrip.getText().toString());
                r.setDNI(dni2.getText().toString());
                r.setDniAproDesembol(DniAprodese.getText().toString());
                r.setFecha(fechaaa.getText().toString());
                r.setFechaAprobador(fechaAproa.getText().toString());
                r.setFechaDesembolso(fecDes.getText().toString());
                r.setFechaSolicitud(fechaSOLL.getText().toString());
                r.setHora(hora.getText().toString());
                r.setImporte(impr.getText().toString());
                r.setNombres_Apellidos(nomb.getText().toString());
                r.setMotivo(mot.getText().toString());
                r.setUid(uidd.getText().toString());
                r.setRuc(Ruc);
                r.setFechaHora(fechaHORA.getText().toString());
                r.setFechaHasta(fecHas.getText().toString());
                r.setFechaDesde(fecDes.getText().toString());
                r.setDniApro(dnApr.getText().toString());
                db.collection("xRendir").document(Uid).set(r);

                finish();

                break;
            }case R.id.icon_back:{
                finish();
                break;
            }
            }
              return true;
        }

        public void descargar_datos(){
            Bundle extras = getIntent().getExtras();
            String Uid = extras.getString("Uid");

            final DocumentReference docRef = db.collection("xRendir").document(Uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            //   String Est,CeCost,des,dni,dniA,dniAD,estaSol,fech,fechaAp,fechaDes,fechaAst,fechaHor,fechaSoli,hora,imp,mot,nom;
                            String centroCosto = document.getString("centroCosto");
                            String Descripcion = document.getString("descripcion");
                            String dnI = document.getString("dni");
                            String dniApro = document.getString("dniApro");
                            String dniAproDesembol = document.getString("dniAproDesembol");
                            String estadoSolicitud = document.getString("estadoSolicitud");
                            String fecha = document.getString("fecha");
                            String fechaAprobador = document.getString("fechaAprobador");

                            String fechaDesd = document.getString("fechaDesde");
                            String fechaHast = document.getString("fechaHasta");

                            String fechaHora = document.getString("fechaHora");
                            String fechaSolicitud = document.getString("fechaSolicitud");
                            String horaa = document.getString("hora");
                            String impor = document.getString("importe");
                            String motiv = document.getString("motivo");
                            String nombres_Apellidos= document.getString("nombres_Apellidos");
                            String uid = document.getString("uid");

                            Bundle extras = getIntent().getExtras();
                            String DNI = extras.getString("DNI");

                            fecDes.setText(fechaDesd);
                            fecHas.setText(fechaHast);
                            centroCost.setText(centroCosto);
                            Descrip.setText(Descripcion);
                            dni2.setText(dnI);
                            dnApr.setText(dniApro);
                            DniAprodese.setText(dniAproDesembol);
                            fechaaa.setText(fecha);
                            fechaAproa.setText(fechaAprobador);
                            fechaHORA.setText(fechaHora);
                            fechaSOLL.setText(fechaSolicitud);
                            hora.setText(horaa);
                            impr.setText(impor);
                            mot.setText(motiv);
                            nomb.setText(nombres_Apellidos);
                            uidd.setText(uid);
                            DniAroLiqu.setText(DNI);
                            // fechaDesde.setText(fechaDesd);
                            // fechaHasta.setText(fechaHas

                        }
                    }

                }

            }
            );
        }

    }
