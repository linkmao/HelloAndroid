package com.maolink.helloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    private FirebaseAuth login; // declaracion de variable para la conexion a firebase
    SharedPreferences guardado; // variable del tipo SharedPreference para el guardado en local de informacion de logueo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.tUserLogin);
        pass = findViewById(R.id.tPassLogin);
        login  = FirebaseAuth.getInstance();

        // Esta funcion lo que hará es verificar si la sesion ya fue previamente iniciada, si así lo fue, esa funcion cierra esta activity
        verificarSiSesionIniciada();

        // escuchador para el boton de loguin (solo para el caso de que no haya una sesion previa ya logueada)
        Button buttonLogin = (Button) findViewById(R.id.bLoguear);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iniciarSesion(v);
            }
        });

        // escuchador para el boton cancelar (se proyecta eliminar este boton pues deberia ser mas intuitivo)
        Button buttonCancelar = (Button) findViewById(R.id.bCancelar);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelar(v);
            }
        });
    }

    public void verificarSiSesionIniciada(){
        /* Esto casi que es una plantilla para verificar si ya hay un usuario logueado, si lo está el dato email, ha quedado guardado en
         local (Ver la clase SesionIniciada alla se muestra el guaraddo de dato en local) */
        guardado= getSharedPreferences(getString(R.string.string_preference), Context.MODE_PRIVATE);
        String email = guardado.getString("email",null); // aca se trae el email, en caso de no tener guardado en local significa que no se han logueado
        if (email!=null) {
            Intent i = new Intent(this, SesionIniciada.class);
            startActivity(i);
            finish();
        }
    }

    public void iniciarSesion(View view){
         login.signInWithEmailAndPassword(user.getText().toString(),pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = login.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), SesionIniciada.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                });
      }
    public void cancelar(View  view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}