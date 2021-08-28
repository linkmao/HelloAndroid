package com.maolink.helloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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


public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText usuario;
    private  EditText pass;
    private  EditText pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance(); // Se obtienen la instancia de FireBase
        usuario = findViewById(R.id.tUserRegister);
        pass = findViewById(R.id.tPassRegister);
        pass2= findViewById(R.id.tPassRegisterRepeat);

        // Escuchador para el boton bRegistrar
        Button buttonRegistrar = (Button) findViewById(R.id.bRegistrar);
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registrarUsuario(v);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    public void registrarUsuario (View view){
            if (pass.getText().toString().equals(pass2.getText().toString())) {
                mAuth.createUserWithEmailAndPassword(usuario.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Se ha logrado la autenticacion.", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent i = new Intent(getApplicationContext(), SesionIniciada.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }

    }
}