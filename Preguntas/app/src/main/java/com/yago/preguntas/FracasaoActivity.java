package com.yago.preguntas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yago.preguntas.bd.Pregunta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FracasaoActivity extends AppCompatActivity {
    Button btMostrarPartida, btVolverAJugar, btMacadorPersonal, btMacadorGlobal;
    ArrayList<Marcador> listaMarcadorGlobal, listaMarcadorPersonal;
    TextView tiempo, aciertos, fallos;
    boolean mgbool, mpbool;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fracasao);
        btMostrarPartida = findViewById(R.id.botonMostrarPartida);
        btMacadorPersonal = findViewById(R.id.botonMarcadorPersonal);
        if (getIntent().getStringExtra("username") == null) {
            btMacadorPersonal.setVisibility(View.GONE);
        }
        btMacadorGlobal = findViewById(R.id.botonMarcadorGlobal);
        btVolverAJugar = findViewById(R.id.botonVolveraJugar);

        String t = getIntent().getStringExtra("tiempo");
        String a = getIntent().getStringExtra("aciertos");
        String f = getIntent().getStringExtra("fallos");
        tiempo = findViewById(R.id.fracasaotiempo);
        aciertos = findViewById(R.id.fracasaoaciertos);
        fallos = findViewById(R.id.fracasaofallos);
        tiempo.setText(t);
        tiempo.setVisibility(View.GONE);
        aciertos.setText(a);
        fallos.setText(f);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(), "s", Toast.LENGTH_LONG).show();
        mgbool = false;
        mpbool = false;
        i = 0;

        listaMarcadorGlobal = new ArrayList<Marcador>();
        listaMarcadorPersonal = new ArrayList<Marcador>();

        cargarMarcadorPersonal();
        cargarMarcadorGlobal();

        ArrayList<Pregunta> list = (ArrayList<Pregunta>) getIntent().getSerializableExtra("list");

        btMostrarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FracasaoActivity.this, ResultadosActivity.class);
                intent.putExtra("list", list);
                intent.putExtra("color", "red3");
                intent.putExtra("tiempo", tiempo.getText().toString());
                intent.putExtra("aciertos", aciertos.getText().toString());
                intent.putExtra("fallos", fallos.getText().toString());
                startActivity(intent);
            }
        });


        btMacadorPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    if (listaMarcadorPersonal.isEmpty()) {
                        cargarMarcadorPersonal();
                    }

                    if (mgbool) {
                        if (i == 0) {
                            i++;
                            Intent intent = new Intent(FracasaoActivity.this, MarcadorPersonalActivity.class);
                            intent.putExtra("listamp", listaMarcadorPersonal);
                            intent.putExtra("username", getIntent().getStringExtra("username"));
                            startActivity(intent);
                        }

                    } else {
                        onRestart();

                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                btMacadorGlobal.performClick();
                            }

                            ;
                        }, 1500);
                    }
                } else {
                    comprobarconexion();
                }
            }
        });


        btMacadorGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                //System.out.println("1");

                if (networkInfo != null && networkInfo.isConnected()) {
//                    System.out.println("2");
                    if (listaMarcadorGlobal.isEmpty()) {
                        cargarMarcadorGlobal();
//                        System.out.println("3");
                    }

                    if (mgbool) {
                        /*System.out.println("4");
                        for (Marcador mg : listaMarcadorGlobal) {
                            System.out.println(mg.toString());
                        }*/
                        if (i == 0) {
                            i++;
                            Intent intent = new Intent(FracasaoActivity.this, MarcadorGlobalActivity.class);
                            intent.putExtra("listamg", listaMarcadorGlobal);
                            startActivity(intent);
                        }

                    } else {
                        onRestart();

                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                btMacadorGlobal.performClick();
                            }

                            ;
                        }, 1500);
                    }


                } else {
                    comprobarconexion();
                }


            }
        });

        btVolverAJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FracasaoActivity.this, MainActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                intent.putExtra("iduser", getIntent().getIntExtra("iduser", 0));
                startActivity(intent);
                finish();
            }
        });
    }


    public void cargarMarcadorPersonal() {
        if (getIntent().getStringExtra("username") != null) {
            Response.Listener<String> respoListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //System.out.println(response);
                    mpbool = false;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        System.out.println(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("array");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String nombre = jsonObject1.getString("usuario");
                            String aciertos = String.valueOf(jsonObject1.getInt("aciertos"));
                            String fallos = String.valueOf(jsonObject1.getInt("fallos"));
                            String tiempo = jsonObject1.getString("tiempo");
                            Marcador mg = new Marcador(nombre, aciertos, fallos, tiempo);
                            listaMarcadorPersonal.add(mg);
                            /*System.out.println(nombre);
                            System.out.println(aciertos);
                            System.out.println(fallos);
                            System.out.println(tiempo);*/
                            mpbool = true;

                        }
                        /*for (Marcador mp : listaMarcadorPersonal) {
                        System.out.println(mp.toString());
                        }*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };

            MarcadorPersonalRequest personalrequest = new MarcadorPersonalRequest(getIntent().getIntExtra("iduser", 0), respoListener);
            RequestQueue queue = Volley.newRequestQueue(FracasaoActivity.this);
            queue.add(personalrequest);
        }
    }


    public void cargarMarcadorGlobal() {
        StringRequest marcadorGlobalRequest = new StringRequest(Request.Method.POST, "https://trivial2022.000webhostapp.com/marcadorglobal4.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mgbool = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("array");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String nombre = jsonObject1.getString("usuario");
                                String aciertos = String.valueOf(jsonObject1.getInt("aciertos"));
                                String fallos = String.valueOf(jsonObject1.getInt("fallos"));
                                String tiempo = jsonObject1.getString("tiempo");
                                Marcador mg = new Marcador(nombre, aciertos, fallos, tiempo);
                                listaMarcadorGlobal.add(mg);
                                /*System.out.println(nombre);
                                System.out.println(aciertos);
                                System.out.println(fallos);
                                System.out.println(tiempo);*/
                                mgbool = true;

                            }
                            /*for (Marcador mg : listaMarcadorGlobal) {
                                System.out.println(mg.toString());
                            }*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(FracasaoActivity.this);
        requestQueue.add(marcadorGlobalRequest);
    }

    private void comprobarconexion() {
        Toast.makeText(getApplicationContext(), "Compruebe si tiene conexión a internet...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FracasaoActivity.this, MainActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        intent.putExtra("iduser", getIntent().getIntExtra("iduser", 0));
        startActivity(intent);
        finish();
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        System.out.println("pause");
        Toast.makeText(getApplicationContext(), "pause", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("stop");
        Toast.makeText(getApplicationContext(), "stop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("resddddddddddddddddtart");
        Toast.makeText(getApplicationContext(), "restart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("resume");
        Toast.makeText(getApplicationContext(), "resume", Toast.LENGTH_LONG).show();
    }*/


}