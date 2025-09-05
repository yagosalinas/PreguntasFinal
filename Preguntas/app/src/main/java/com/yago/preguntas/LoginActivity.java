package com.yago.preguntas;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText etqName, etqPass;
    Button btnLogin;
    TextView btnRegistrarse;
    int control = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Asociamos las variables al nombre que tienen en el layout
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etqName = (EditText) findViewById(R.id.etUsuario);
        etqPass = (EditText) findViewById(R.id.etContrasena);
        btnRegistrarse = (TextView) findViewById(R.id.btnRegistrarse2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etqName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Campo Nombre vacío", Toast.LENGTH_LONG).show();
                } else {
                    if (etqPass.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Campo password vacío", Toast.LENGTH_LONG).show();
                    } else {


                    }
                }
                final String username = etqName.getText().toString().trim();
                final String password = etqPass.getText().toString().trim();
                if (etqName.length() == 0 || etqPass.length() == 0) {

                } else {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                    if (networkInfo != null && networkInfo.isConnected()) {
                        Response.Listener<String> respoListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    int success = jsonResponse.getInt("success");
                                    if (success == 1) {
                                        int id = jsonResponse.getInt("iduser");
                                        System.out.println(id);
                                        if (control == 0) {
                                            control++;
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.putExtra("username", username);
                                            intent.putExtra("iduser", id);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(getApplicationContext(), "Logeo correcto...", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        if(success == 2) {
                                            Toast.makeText(getApplicationContext(), "El usuario no esta en la BD", Toast.LENGTH_LONG).show();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                            builder.setTitle("Error al Iniciar Sesión:")
                                                    .setMessage("\nEl usuario introducido no existe, escriba bien su nombre de usuario o registrese. \n")
                                                    .setNegativeButton("Reintentar", null)
                                                    .create().show();
                                        }
                                        if(success==0){
                                            Toast.makeText(getApplicationContext(), "Cuntraseña incorrecta BD", Toast.LENGTH_LONG).show();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                            builder.setTitle("Error al Iniciar Sesión:")
                                                    .setMessage("\nContraseña incorrecta, escriba bien su contraseña o registrese de nuevo.\n")
                                                    .setNegativeButton("Reintentar", null)
                                                    .create().show();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        };

                        LoginRequest loginrequest = new LoginRequest(username, password, respoListener);
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        queue.add(loginrequest);
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "Compruebe si tiene conexión a internet...", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}