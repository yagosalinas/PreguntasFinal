package com.yago.preguntas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yago.preguntas.bd.Pregunta;

import java.util.ArrayList;

public class ResultadosActivity extends AppCompatActivity {

    RecyclerView recycler;
    TextView tiempo,aciertos, fallos, gion, txaciertos, txfallos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            setTheme(getTheme());
        }
        setContentView(R.layout.activity_resultados);

        LinearLayout fondo=findViewById(R.id.fondo);
        String c=getIntent().getStringExtra("color");
        if(c.equals("red3")){
            fondo.setBackgroundColor(getResources().getColor(R.color.red3));
        }else if (c.equals("green2")){
            fondo.setBackgroundColor(getResources().getColor(R.color.green3));
        }

        recycler=findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Pregunta> list = (ArrayList<Pregunta>) getIntent().getSerializableExtra("list");

        ResultadosAdapter adapter=new ResultadosAdapter(list);
        recycler.setAdapter(adapter);

        String t=getIntent().getStringExtra("tiempo");
        String a=getIntent().getStringExtra("aciertos");
        String f=getIntent().getStringExtra("fallos");
        tiempo = findViewById(R.id.resulttiempo);
        aciertos = findViewById(R.id.resultaciertos);
        fallos = findViewById(R.id.resultfallos);
        tiempo.setText(t);
        aciertos.setText(a);
        fallos.setText(f);
        if(f.equals("3")){
            LinearLayout laxau=findViewById(R.id.laxau);
            gion=findViewById(R.id.guion);
            txaciertos=findViewById(R.id.txresultaciertos);
            txfallos=findViewById(R.id.txresultfallos);
            tiempo.setVisibility(View.GONE);
            txaciertos.setTextColor(getResources().getColor(R.color.red5));
            aciertos.setTextColor(getResources().getColor(R.color.red5));
            fallos.setTextColor(getResources().getColor(R.color.red5));
            gion.setTextColor(getResources().getColor(R.color.red5));
            txaciertos.setTextColor(getResources().getColor(R.color.red5));
            txfallos.setTextColor(getResources().getColor(R.color.red5));
            laxau.setBackground(getResources().getDrawable(R.drawable.fondo_fracasao));

        }
    }

    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        String c=getIntent().getStringExtra("color");
        if(c.equals("red3")){
            theme.applyStyle(R.style.ProyectoNoActionBarRojo, true);

        }else if (c.equals("green2")){
            theme.applyStyle(R.style.ProyectoNoActionBarVerde, true);
        }
        return theme;
    }
}