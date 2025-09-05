package com.yago.preguntas.bd;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class PreguntaLab implements PreguntaDao {

    private static PreguntaLab pLab;
    private PreguntaDao pDao;

    private PreguntaLab(Context context) {
        Context appContext = context.getApplicationContext();
        PreguntasBD database = Room.databaseBuilder(appContext, PreguntasBD.class,
                "preguntas").allowMainThreadQueries().build();

        pDao = database.getPreguntas();
    }

    public static PreguntaLab get(Context context) {
        if (pLab == null) {
            pLab = new PreguntaLab((context));
        }
        return pLab;
    }

    @Override
    public List<Pregunta> getPreguntas() {
        return pDao.getPreguntas();
    }

    @Override
    public Pregunta getPregunta(int id) {
        return pDao.getPregunta(id);
    }

    @Override
    public Pregunta getPregunta(String pregunta) {
        return pDao.getPregunta(pregunta);
    }

    @Override
    public void insertarPregunta(Pregunta pregunta) {
        pDao.insertarPregunta(pregunta);
    }

    @Override
    public void actualizar(Pregunta pregunta) {
        pDao.actualizar(pregunta);
    }

    @Override
    public void borrarDatos() {
        pDao.borrarDatos();
    }

    @Override
    public LiveData<Integer> getRowCount() {
        return null;
    }

    @Override
    public void delete(Pregunta pregunta) {
        pDao.delete(pregunta);
    }



}
