package com.example.ronaldozelada.xrendir;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.autofill.FillEventHistory;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.liquidacion;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Liquidacion_Registro extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db;

    private static final String CARPETA_PRINCIPAL ="misImagenesApp/";
    private static final String CARPETA_IMAGEN ="imagenes";
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    private String path;
    File fileImagen;
    Bitmap bitmapl;
    ImageView imgFoto;

    private  static final int COD_SELECCIONA = 10;
    private  static  final int COD_FOTO = 20;

    String  TipoD, TipoC,mCurrentPhotoPath;
    String [] items1, items2;
    int dia, mes, ano;


    Spinner TipoDoc, TipCat;
    private boolean listapriemra = true, listasegunda = true;

    EditText txtImp,fecha, Ruc, proveedor,  numdoc,  des;

    Button btnTomarFoto;


    private static final int REQUEST_IMAGE_CAPTURE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquidacion__registro);


       LinearLayout layout0 = (LinearLayout) findViewById(R.id.La0);

        layout0.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });



        LinearLayout layout4 = (LinearLayout) findViewById(R.id.La4);

        layout4.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        LinearLayout layout5 = (LinearLayout) findViewById(R.id.La5);

        layout5.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        LinearLayout layout6 = (LinearLayout) findViewById(R.id.La6);

        layout6.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });



        LinearLayout layout1 = (LinearLayout) findViewById(R.id.La1);

        layout1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });


        LinearLayout layout2= (LinearLayout) findViewById(R.id.La2);

        layout2.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });

        LinearLayout layout3 = (LinearLayout) findViewById(R.id.La3);

        layout3.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideSoftKeyboard(view);
                return false;
            }
        });





        db = FirebaseFirestore.getInstance();

        TipoDoc = (Spinner) findViewById(R.id.spTipoDoc);
        TipCat = (Spinner) findViewById(R.id.TipoCat);


        imgFoto = (ImageView) findViewById(R.id.imgFoto);

        Ruc = (EditText)findViewById(R.id.txtRUC);
        proveedor=(EditText)findViewById(R.id.txt_Proveedor);
        numdoc = (EditText)findViewById(R.id.txt_NumeroDoc);
        des = (EditText)findViewById(R.id.txtDescripcion);
        txtImp = (EditText)findViewById(R.id.txtImp);
        btnTomarFoto = (Button)findViewById(R.id.btnTomarFoto);
        fecha = (EditText)findViewById(R.id.txtFechaDoc);
        fecha.setOnClickListener(this);


        items1 = getResources().getStringArray(R.array.Tipo_Documento);
        items2 = getResources().getStringArray(R.array.Categoria);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Tipo_Documento, R.layout.spinner_item_formato);
        TipoDoc.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Categoria, R.layout.spinner_item_formato);
        TipCat.setAdapter(adapter2);


        TipoDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(listapriemra){
                   listapriemra = true;
               }else {
                   TipoD = items1[position];
                   listapriemra = true;
                  if(TipoD.equals("Tipo Documento"))
                  {
                      Toast.makeText(getApplicationContext(), "Por Favor Seleccione un Documento", Toast.LENGTH_SHORT).show();
                  } else
                      {

                          Toast.makeText(getApplicationContext(), TipoD , Toast.LENGTH_SHORT).show();
                      }

               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TipCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(listasegunda){
                    listasegunda = false;
                }else {
                    TipoC = items2[position];
                    listasegunda = true;
                    if(TipoC.equals("Tipo Categoria")){
                        Toast.makeText(getApplicationContext(), "Por Favor Seleccione una Categoria", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getApplicationContext(), TipoC, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     // tomarFoto(v);
                mostrarDialogoOpciones();
                      //Toast.makeText(Liquidacion_Registro.this, "Camara", Toast.LENGTH_SHORT).show();

            }
        });

    }



    public void hideSoftKeyboard(View view) {

        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }


        public void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); //aqui
            }
        }
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        String imageFileName =timeStamp;

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String path = storageDir.getPath() + "/ALUMA";

        File temp = new File(path);
        temp.mkdirs();
        File image = File.createTempFile(
           imageFileName,   //prefix
                ".png",     //    suffix
                temp      // directory
        );
         mCurrentPhotoPath = image.getPath();
        Log.e("mCurrentPhotoPath: " ,mCurrentPhotoPath);
        return image;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu07, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String ruc = Ruc.getText().toString();
        String Proveedor = proveedor.getText().toString();
        String Numdc =  numdoc.getText().toString();
        String Des = des.getText().toString();
        String FechaDoc = fecha.getText().toString();


        switch (item.getItemId()) {
            case R.id.icon_save: {
                if( Validacion() == false)
                {
                  // Toast.makeText(this, "Completar Campos obligatorios", Toast.LENGTH_SHORT).show();
                }
                else{

                    long fechahora = System.currentTimeMillis();
                    SimpleDateFormat fec = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
                    String fechashora = fec.format(fechahora);

                    Bundle extras = getIntent().getExtras();
                    String Uid = extras.getString("Uid");



                        liquidacion l = new liquidacion();


                        l.setRuc(Ruc.getText().toString());

                        l.setProveedor(proveedor.getText().toString());
                        l.setNumdoc(numdoc.getText().toString());
                        l.setDescripcion(des.getText().toString());
                        l.setFechaDoc(FechaDoc);
                        l.setCategoria(TipoC);
                        l.setTipoDoc(TipoD);
                         l.setImporte(txtImp.getText().toString());
                        l.setIdSolicitud(Uid);
                        l.setIdLiquidacion(UUID.randomUUID().toString());
                        l.setFechaCreacion(fechashora);

                        db.collection("Liquidacion").document(l.getIdLiquidacion()).set(l);
                        Toast.makeText(this, "Documento Agregado", Toast.LENGTH_SHORT).show();


                        finish();


                }

                break;
            }
            case  R.id.icon_cancel:{
                finish();
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();

                break;
            }
            case  R.id.icon_back:{
                finish();
                Toast.makeText(this, "Atras", Toast.LENGTH_SHORT).show();

                break;
            }
        }
        return true;
    }


    private boolean Validacion() {

        String ruc = Ruc.getText().toString();
        String Proveedor = proveedor.getText().toString();
        String Fecha = fecha.getText().toString();
        String Numdc =  numdoc.getText().toString();
        String Des = des.getText().toString();
        String Importe = txtImp.getText().toString();

        String coma = ",";
        char[] vector = Importe.toCharArray();
        for (int a = 0; a < Importe.length(); a++) {
            String error = String.valueOf(vector[a]);
            if (coma.equalsIgnoreCase(error)) {
                txtImp.setError("Error: Ingrese '.'(Punto)como decimal");
                Toast.makeText(this, "Error: Ingrese '.'(Punto)como decimal ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }



// listapriemra == false||




        if(Proveedor.equals("") || Fecha.equals("" )||Importe.equals("") || Des.equals("")){
               if (Proveedor.equals("")) {
                 proveedor.setError("Campo Obligatorio");
                   Toast.makeText(getApplicationContext(), "Por Favor Ingrese un proveedor", Toast.LENGTH_SHORT).show();
                } else if (Fecha.equals("")) {
                fecha.setError("Campo Obligatorio");
                   Toast.makeText(getApplicationContext(), "Por Favor Ingrese una Fecha", Toast.LENGTH_SHORT).show();

               }else if (Importe.equals("")) {
                   txtImp.setError("Campo Obligatorio");
                   Toast.makeText(getApplicationContext(), "Por Favor Ingrese un Importe", Toast.LENGTH_SHORT).show();

               }
               else if (Des.equals("")) {
                   des.setError("Campo Obligatorio");
                   Toast.makeText(getApplicationContext(), "Por Favor Ingrese una Descripcion", Toast.LENGTH_SHORT).show();

               }
              return  false;
          }
        if(  listasegunda == false|| TipoC.equals("Tipo Categoria")){

            Toast.makeText(getApplicationContext(), "Por Favor Ingrese una Categoria", Toast.LENGTH_SHORT).show();
            return false;
        } else{return true;}


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
    }


    private void mostrarDialogoOpciones()
    {
        final CharSequence[] opciones  = {"Tomar Foto", "Elegir de Galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(Liquidacion_Registro.this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(opciones[i].equals("Tomar Foto"))
                {
                    tomarFoto();
                  // AbrirCamara();
                   Toast.makeText(Liquidacion_Registro.this,"Tomar Foto", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(opciones[i].equals("Elegir de Galeria"))
                    {
                        Intent intent = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }
                    else{dialog.dismiss();}
                }
            }
        });
        builder.show();
    }

    public void AbrirCamara()
    {
        File miFile = new File (Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada = miFile.exists();
        if (isCreada == false)
        {
            isCreada = miFile.mkdirs();
        }
        if (isCreada == true)
        {
            Long consecutivo = System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString() + ".jpg";

            path = Environment.getExternalStorageDirectory()+ File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;

            fileImagen = new File(path);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));

            startActivityForResult(intent,COD_FOTO);
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case COD_SELECCIONA:
                Uri miPath = data.getData();
                imgFoto.setImageURI(miPath);
                break;


            case COD_FOTO:
                MediaScannerConnection.scanFile(this, new String[]{path},null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path: ", ""+path);
                            }
                        });
                bitmapl = BitmapFactory.decodeFile(path);
                imgFoto.setImageBitmap(bitmapl);
                break;
        }


    }

}









