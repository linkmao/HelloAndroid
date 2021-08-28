package com.maolink.helloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SesionIniciada extends AppCompatActivity {
    private FirebaseFirestore db;  // Variable de instancia para la conexión a la base de datos
    SharedPreferences guardado;    // Variable de instancia para el guarado de información en local
    SharedPreferences.Editor editor;  // Variable de instancia para la edicion de la info a guardar en local
    private TextView usuario;
    private EditText nombres;
    private EditText apellidos;
    private EditText edad;
    private EditText telefono;
    private String usuarioLogueado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_iniciada);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();  // Se recupera el usuario logueado
        usuarioLogueado = user.getEmail().toString();
        usuario = findViewById(R.id.tUsuario);
        usuario.setText(usuario.getText() + usuarioLogueado); // se lleva al TextView el correo del usuario

        // Inicio de la lógica para el guaardo en local del email del usuario logueado
        guardado = getSharedPreferences(getString(R.string.string_preference), Context.MODE_PRIVATE);
        editor = guardado.edit();
        editor.putString("email", user.getEmail());
        editor.commit();

        db = FirebaseFirestore.getInstance();  // obtencion de instancia para la conexión a la base de datos
        nombres = (EditText) findViewById(R.id.tNombres);
        apellidos = (EditText) findViewById(R.id.tApellidos);
        edad = (EditText) findViewById(R.id.tEdad);
        telefono = (EditText) findViewById(R.id.tTelefono);

        // Escuchador del boton bGuardar para el llamado de la funcion guardar()
        Button buttonGuardar = (Button) findViewById(R.id.bGuardar);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardar();
            }
        });

        // Escuchador del boton bRecuperar para el llamado de la funcion recuperar()
        Button buttonRecuperar = (Button) findViewById(R.id.bRecuperar);
        buttonRecuperar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recuperar();
            }
        });

        // Escuchadr del boton bBorrar para el llamado de la funcion borrar()
        Button buttonBorrar = (Button) findViewById(R.id.bBorrar);
        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                borrar();
            }
        });
    }


    // Funcin de logout, se encarga de borrar el dato local de email e ir a la activity principal
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        guardado = getSharedPreferences(getString(R.string.string_preference), Context.MODE_PRIVATE);
        editor = guardado.edit();
        editor.clear().apply();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void guardar() {
        // Se debe crear un mapa con los campos que tendrá el docuemnto usuario
        Map<String, Object> usuario = new HashMap<>();
        usuario.put("name", nombres.getText().toString());
        usuario.put("lastname", apellidos.getText().toString());
        usuario.put("age", edad.getText().toString());
        usuario.put("phone", telefono.getText().toString());
        db.collection("users").document(usuarioLogueado).set(usuario);
    }

    public void recuperar() {
        DocumentReference docRef = db.collection("users").document(usuarioLogueado);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        nombres.setText(document.getString("name"));
                        apellidos.setText(document.getString("lastname"));
                        edad.setText(document.getString("age"));
                        telefono.setText(document.getString("phone"));
                        Log.d("Mao", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Toast.makeText(getApplicationContext(), "Datos no encontrados", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la conexión", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void borrar (){
        db.collection("users").document(usuarioLogueado).delete();
    }

}