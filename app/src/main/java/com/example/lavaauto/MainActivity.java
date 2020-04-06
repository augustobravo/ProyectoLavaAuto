package com.example.lavaauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}
