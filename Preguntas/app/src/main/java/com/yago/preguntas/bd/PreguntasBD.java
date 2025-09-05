package com.yago.preguntas.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pregunta.class}, version = 1)
public abstract class PreguntasBD extends RoomDatabase {
    public abstract PreguntaDao getPreguntas();
}
