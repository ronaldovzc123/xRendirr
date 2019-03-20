package com.example.ronaldozelada.xrendir;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ronaldozelada.xrendir.modelos.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore bd;

    Button btnLogin;

    EditText txtDNI ,txtNombre;

    usuarios usuarioSelec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bd = FirebaseFirestore.getInstance();

        btnLogin = (Button)findViewById(R.id.btnlogin);
        txtDNI = (EditText)findViewById(R.id.txtDNI);
        txtNombre = (EditText)findViewById(R.id.txtNombre);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            private static final String TAG =  "HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

            @Override
            public void onClick(View v) {


                if (txtDNI.getText().length()  == 0) {
                    Toast.makeText(LoginActivity.this, "Por Favor ingrese su DNI", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "HOLA FUNCIONA");
                     //int Cantidad = txtDNI.getText().length();

                    String DNI = txtDNI.getText().toString();
                    //Log.d(TAG, String.valueOf(Cantidad));



                   // String DNI = "75228589";
                    if (DNI != "") {

                        final DocumentReference docRef = bd.collection("usuarios").document(DNI);
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            private static final String TAG = "Mensaje";

                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {

                                        //Capturar Datos

                                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                                        String gato = document.getString("nombres");
                                        String ApellidoP = document.getString("apellido_paterno");
                                        String ApellidoM = document.getString("apellido_materno");
                                        String DNI = document.getString("dni");
                                        String Correo = document.getString("correo");
                                        String Telefono = document.getString("telefono");
                                        String Usuario = document.getString("usuario");
                                        String Aprobador = document.getString("aprobador");
                                        String Caja = document.getString("caja");
                                        String Ruc = document.getString("Ruc");

                                        usuarioSelec = new usuarios();
                                        usuarioSelec.setNombres(gato);
                                        usuarioSelec.setApellidoP(ApellidoP);
                                        usuarioSelec.setApellidoM(ApellidoM);
                                        usuarioSelec.setDNI(DNI);
                                        usuarioSelec.setCorreo(Correo);
                                        usuarioSelec.setTelefono(Telefono);
                                        usuarioSelec.setUsuario(Usuario);
                                        usuarioSelec.setAprobador(Aprobador);
                                        usuarioSelec.setCaja(Caja);
                                        usuarioSelec.setRuc(Ruc);


                                        // Intent a = new Intent(LoginActivity.this, xRendir.class);
                                        //a.putExtra("Nombres",Nombres);

                                        //Intent b = new Intent(LoginActivity.this, AprobarSolicitud.class);
                                        // b.putExtra("Nombres",Nombres);


                                        Intent I = new Intent(LoginActivity.this, MenuInicio.class);

                                        //Enviar Datos

                                        I.putExtra("Nombres", usuarioSelec.getNombres());
                                        I.putExtra("ApeM", usuarioSelec.getApellidoM());
                                        I.putExtra("ApeP", usuarioSelec.getApellidoP());
                                        I.putExtra("Nombres", usuarioSelec.getNombres());
                                        I.putExtra("Usuario", usuarioSelec.getUsuario());
                                        I.putExtra("Aprobador", usuarioSelec.getAprobador());
                                        I.putExtra("Caja", usuarioSelec.getCaja());
                                        I.putExtra("DNI", usuarioSelec.getDNI());
                                        I.putExtra("Ruc", usuarioSelec.getRuc());

                                        startActivity(I);

                                        Toast.makeText(LoginActivity.this, "Bienvenido " + usuarioSelec.getNombres(), Toast.LENGTH_SHORT).show();


                                    } else {
                                        Log.d(TAG, "No such document");

                                        Toast.makeText(LoginActivity.this, "DNI No Registrado", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                    }


                }

            }




        });



    }
}
