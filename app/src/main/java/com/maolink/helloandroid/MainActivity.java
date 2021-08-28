package com.maolink.helloandroid;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Se definen las instancias para recibir y llevar los valores a los controles de la interfaz
    private TextView resultado;
    private EditText num1;
    private EditText num2;

    // En este metodo constructor se crea la instancia de la vista y la isntancia de los objetos que recogeran lo datos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se llevan los valores a las instancias previamente definidas, notese como aca se asocia con el nombre del id
        num1=findViewById(R.id.iNum1);
        num2=findViewById(R.id.iNum2);
        resultado=findViewById(R.id.tResultado);

        // Asignación de evento a los boton por codigo (escuchadores)
        Button buttonSuma = (Button) findViewById(R.id.bSuma);
        buttonSuma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sumar(v);
            }
        });

        Button buttonResta = (Button) findViewById(R.id.bResta);
        buttonResta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                restar(v);
            }
        });

        Button buttonMultiplica = (Button) findViewById(R.id.bMultiplica);
        buttonMultiplica.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                multiplicar(v);
            }
        });

        Button buttonDivide = (Button) findViewById(R.id.bDivision);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dividir(v);
            }
        });
    } // Fin del metodo onCreate


    // Se inicia la declaracion de metodos propios que permitan la manipulacion de los datos
    /* Nota: Los metodos que aca se declaran se defienne con parametro View, esto es necesario solo si el metodo
    se asocia al boton mediante la propiedad onClick desde el IDE, ahora, los metodos sumar, restar, multiplicar
    y dividir en este caso podrian definirse sin el parametro View, pues se está haciendo el llamado por código desde
    el onCreate por medio de escuchadores, sin embargo en este caso se dejó los parámetro View.

    Para el caso de los metodos goAbout, goSiaga, goWeb, goLogin y goRegister se asignaran al boton por medio de la propiedad onClick desde el IDE
    */


    public void sumar (View view){
        try{
            double total = Double.parseDouble(num1.getText().toString())+ Double.parseDouble(num2.getText().toString());
            resultado.setText( Double.toString(total));
        }
        catch (Exception e){
            mensaje();
        }
    }

    // Metodo que se encargará de restar los dos valores que se ingresa en los edittext
    public void restar (View view){
        try{
            double total = Double.parseDouble(num1.getText().toString())- Double.parseDouble(num2.getText().toString());
            resultado.setText( Double.toString(total));
        }
        catch (Exception e){
            mensaje();
        }
    }

    // Metodo que se encargará de multiplicar los dos valores que se ingresa en los edittext
    public void multiplicar (View view){
        try {
            double total = Double.parseDouble(num1.getText().toString()) * Double.parseDouble(num2.getText().toString());
            resultado.setText(Double.toString(total));
        }
        catch (Exception e){
            mensaje();
        }
    }

    // Metodo que se encargará de dividir los dos valores que se ingresa en los edittext
    public void dividir (View view){
        try {
            double total = Double.parseDouble(num1.getText().toString()) / Double.parseDouble(num2.getText().toString());
            resultado.setText(Double.toString(total));
        }
        catch (Exception e){
            mensaje();
        }
    }

    // Metodo que enviara un mensaje en caso de que los valores insertados no sean válidos
    private void mensaje(){
        Toast.makeText(this, "Debes introducir valores numéricos enteros en ambos campos",Toast.LENGTH_LONG).show();
    }


    // Los siguientes metodos se asignaran por medio de la propiedad onClick
    public void goAbout(View view){
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }

    public void goWeb(View view){
        Intent i = new Intent(this, Web.class);
        startActivity(i);
    }

    public void goSiaga(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://siaga.org") );
        startActivity(i);
    }

    public void goLogin(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }

    public void goRegister(View view){
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}