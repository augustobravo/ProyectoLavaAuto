package com.example.lavaauto.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lavaauto.MainActivity;
import com.example.lavaauto.R;

public class RegistroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
    }
    public void cancelarUsuario (View view) {
        Intent cancelarUsuario = new Intent(this, MainActivity.class);
        startActivity(cancelarUsuario);
    }
}
