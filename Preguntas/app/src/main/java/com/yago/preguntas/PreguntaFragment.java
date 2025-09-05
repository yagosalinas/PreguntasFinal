package com.yago.preguntas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreguntaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreguntaFragment extends Fragment {

    Button b2, b1, b3, b4;

    gestionarPulsacionRespuesta gestionarPulsacion;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pregunta";
    private static final String ARG_PARAM2 = "r1";
    private static final String ARG_PARAM3 = "r2";
    private static final String ARG_PARAM4 = "r3";
    private static final String ARG_PARAM5 = "r4";
    private static final String ARG_PARAM6 = "correcta";


    private String pregunta;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String correcto;

    public PreguntaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pregunta Parameter 1.
     * @param r1 Parameter 2.
     * @return A new instance of fragment PreguntasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreguntaFragment newInstance(String pregunta, String r1, String r2, String r3, String r4, String correcto) {
        PreguntaFragment fragment = new PreguntaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, pregunta);
        args.putString(ARG_PARAM2, r1);
        args.putString(ARG_PARAM3, r2);
        args.putString(ARG_PARAM4, r3);
        args.putString(ARG_PARAM5, r4);
        args.putString(ARG_PARAM5, correcto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pregunta = getArguments().getString(ARG_PARAM1);
            r1 = getArguments().getString(ARG_PARAM2);
            r2 = getArguments().getString(ARG_PARAM3);
            r3 = getArguments().getString(ARG_PARAM4);
            r4 = getArguments().getString(ARG_PARAM5);
            correcto = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregunta, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.tvPregunta)).setText(pregunta);
        Drawable button_correcto=getResources().getDrawable(R.drawable.button_correcto);
        Drawable button_incorrecto=getResources().getDrawable(R.drawable.button_incorrecto);
        b1=(view.findViewById(R.id.btRespuesta1));
        b1.setText(r1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String respuesta=b1.getText().toString();
                if (respuesta.equals(correcto)){
                    gestionarPulsacion.enviarDatos(respuesta,true);
                    b1.setBackground(button_correcto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }else{
                    gestionarPulsacion.enviarDatos(respuesta,false);
                    b1.setBackground(button_incorrecto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }

            }
        });
        b2=((Button)view.findViewById(R.id.btRespuesta2));
        b2.setText(r2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String respuesta=b2.getText().toString();
                if (respuesta.equals(correcto)){
                    gestionarPulsacion.enviarDatos(respuesta,true);
                    b2.setBackground(button_correcto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }else{
                    gestionarPulsacion.enviarDatos(respuesta,false);
                    b2.setBackground(button_incorrecto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }

            }
        });
        b3=((Button)view.findViewById(R.id.btRespuesta3));
        b3.setText(r3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String respuesta=b3.getText().toString();
                if (respuesta.equals(correcto)){
                    gestionarPulsacion.enviarDatos(respuesta,true);
                    b3.setBackground(button_correcto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }else{
                    gestionarPulsacion.enviarDatos(respuesta,false);
                    b3.setBackground(button_incorrecto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }

            }
        });
        b4=((Button)view.findViewById(R.id.btRespuesta4));
        b4.setText(r4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String respuesta=b4.getText().toString();
                if (respuesta.equals(correcto)){
                    gestionarPulsacion.enviarDatos(respuesta,true);
                    b4.setBackground(button_correcto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }else{
                    gestionarPulsacion.enviarDatos(respuesta,false);
                    b4.setBackground(button_incorrecto);
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                }

            }
        });


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof gestionarPulsacionRespuesta){
            gestionarPulsacion= (gestionarPulsacionRespuesta) context;
        }else{
            throw new RuntimeException(context.toString()+"must implement OnFragmentInteractionListener");
        }
    }

    public interface gestionarPulsacionRespuesta {
        public void enviarDatos(String respuesta, boolean resultado);
    }

}