package com.example.lavaauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lavaauto.ui.home.RegistroUsuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eventoBotonIngresar(View view){
        Intent menuNavegable= new Intent(this, MenuNavegable.class);
        startActivity(menuNavegable);
    }
    public void registrarUsuario (View view) {
        Intent registrarUsuario = new Intent(this, RegistroUsuario.class);
        startActivity(registrarUsuario);
    }
}
