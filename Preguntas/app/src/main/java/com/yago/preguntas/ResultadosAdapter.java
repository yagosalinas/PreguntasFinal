package com.yago.preguntas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yago.preguntas.bd.Pregunta;

import java.util.ArrayList;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ResultadosViewholder> {

    ArrayList<Pregunta> lista;

    public ResultadosAdapter(ArrayList<Pregunta> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ResultadosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ResultadosViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadosAdapter.ResultadosViewholder holder, int position) {
        holder.asignarResultado(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    ////////////////////////////////////

    public class ResultadosViewholder extends RecyclerView.ViewHolder {

        ConstraintLayout layout;
        ImageView correcta;
        ImageView incorrecta;
        TextView pregunta;
        TextView respuesta;


        public ResultadosViewholder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.item_layout);
            correcta=itemView.findViewById(R.id.tik);
            incorrecta=itemView.findViewById(R.id.cruz);
            pregunta=itemView.findViewById(R.id.preguntaTv);
            respuesta=itemView.findViewById(R.id.respuestaTv);

        }

        public void asignarResultado(Pregunta p) {
            pregunta.setText(p.getPregunta());
            respuesta.setText(p.getSeleccionada());
            if(p.getResultado()){
                correcta.setVisibility(View.VISIBLE);
                incorrecta.setVisibility(View.INVISIBLE);
                layout.setBackgroundResource(R.drawable.recyclerviewitem_correcto2);
            }else{
                incorrecta.setVisibility(View.VISIBLE);
                correcta.setVisibility(View.INVISIBLE);
                layout.setBackgroundResource(R.drawable.recyclerviewitem_incorrecto);
            }
        }
    }
}
