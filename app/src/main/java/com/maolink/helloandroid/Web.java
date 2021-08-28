package com.maolink.helloandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;


// Actividad que abre un url a pantalla completa
public class Web extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView myWeb = (WebView) findViewById(R.id.webView);
        myWeb.loadUrl("https://www.google.com");
    }
}