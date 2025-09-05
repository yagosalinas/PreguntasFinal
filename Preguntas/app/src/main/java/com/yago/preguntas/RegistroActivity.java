package com.yago.preguntas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {
    EditText etUsuario, etContrasena;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etUsuario = findViewById(R.id.etUsuarioR);
        etContrasena = findViewById(R.id.etContrasenaR);

        btnRegistrar = findViewById(R.id.btnSignIn);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etUsuario.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Campo Nombre vacío", Toast.LENGTH_LONG).show();
                } else {
                    if (etContrasena.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Campo password vacío", Toast.LENGTH_LONG).show();
                    }
                    else{


                    }
                }

                final String username = etUsuario.getText().toString().trim();
                final String password = etContrasena.getText().toString().trim();

                if (username.length() == 0 || password.length() == 0) {

                }else{
                    final int[] a = {0};
                    Response.Listener<String> respoListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            a[0] =1;
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                int success = jsonResponse.getInt("success");

                                if (success == 1) {
                                    Toast.makeText(getApplicationContext(),
                                            "Registro completado...", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
//                                    Toast.makeText(getApplicationContext(),
//                                            "El usuario ya existe...", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                                    builder.setTitle("Error al Registrarse")
                                            .setMessage("\nEl usuario introducido ya existe, elija un nombre de usuario diferente.\n")
                                            .setNegativeButton("Reintentar", null)
                                            .create().show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    RegisterRequest registerRequest = new RegisterRequest(username, password, respoListener);
                    RequestQueue queue = Volley.newRequestQueue(RegistroActivity.this);
                    queue.add(registerRequest);

                    new Handler().postDelayed(new Runnable(){
                        public void run(){
                            comprobarconexion(a[0]);
                        };
                    }, 3000);

                }
            }
        });
    }
    private void comprobarconexion(int i){
        if(i==0){
            Toast.makeText(getApplicationContext(),
                    "Compruebe si tiene conexión a internet...", Toast.LENGTH_LONG).show();
        }
    }
}