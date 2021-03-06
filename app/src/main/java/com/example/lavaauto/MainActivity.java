package com.example.lavaauto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lavaauto.dao.LavaAutoDAO;
import com.example.lavaauto.ui.entidad.EUsuario;
import com.example.lavaauto.ui.home.RegistroUsuario;
import com.example.lavaauto.ui.utilitario.Constants;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private EditText txtdocumento;
    private EditText txtpassword;
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
        .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signInButton = (SignInButton) findViewById(R.id.singButton);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setColorScheme(SignInButton.COLOR_DARK);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
              startActivityForResult(intent,SIGN_IN_CODE);
            }
        });
        //  txtdocumento = (EditText)findViewById(R.id.txtdocumento);
        //  txtpassword = (EditText)findViewById(R.id.txtpassword);
        };


    public void eventoBotonIngresar(View view){
        Log.i("MainActivity", "eventoBotonIngresar()");
        LavaAutoDAO lavaAutoDAO = new LavaAutoDAO();
        EditText txtdocumento = (EditText)findViewById(R.id.txtDocumento);
        EditText txtpassword = (EditText) findViewById(R.id.txtPassword);
        //txtdocumento.setText("12345678");
        //txtpassword.setText("123456");
        EUsuario usuario = lavaAutoDAO.obtenerUsuario(txtdocumento.getText().toString(), txtpassword.getText().toString());
        String resultado = "";
        if (txtdocumento.length() == 0){
            resultado = "Falta Documento ";
        }
        if (txtpassword.length() == 0){
            resultado = resultado + "Falta Contraseña ";
        }

        if(usuario != null) {
            Constants.usuario = usuario;
            Intent menuNavegable = new Intent(this, MenuNavegable.class);
            startActivity(menuNavegable);
        }else{
            Toast.makeText(this, "Usuario Incorrecto",Toast.LENGTH_SHORT).show();
        }

    }
    public void registrarUsuario (View view) {
        Intent registrarUsuario = new Intent(this, RegistroUsuario.class);
        startActivity(registrarUsuario);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if (result.isSuccess()){
            goMainScreen();
            Toast toast3 = Toast.makeText(this, "Conectado",Toast.LENGTH_SHORT);
            toast3.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 1700);
            toast3.show();
        } else {
            Toast.makeText(this, "No se pudo iniciar",Toast.LENGTH_SHORT).show();
        }
    }
    private void goMainScreen(){
        Intent intent = new Intent(this, MenuNavegable.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
