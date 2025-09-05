package com.yago.preguntas.bd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
interface PreguntaDao {
    @Query("SELECT * FROM preguntas")
    List<Pregunta> getPreguntas();

    @Query("SELECT * FROM preguntas WHERE id LIKE :id")
    Pregunta getPregunta(int id);

    @Query("SELECT * FROM preguntas WHERE pregunta LIKE :pregunta")
    Pregunta getPregunta(String pregunta);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarPregunta(Pregunta pregunta);

    @Update
    void actualizar(Pregunta pregunta);

    @Query("DELETE FROM preguntas")
    void borrarDatos();

    @Query("SELECT COUNT(id) FROM preguntas")
    LiveData<Integer> getRowCount();

    @Delete
    void delete(Pregunta pregunta);

//    @Query("SELECT * FROM preguntas WHERE resultado = 1")
//    ArrayList<Pregunta> getAciertos();
//
//    @Query("SELECT * FROM preguntas WHERE resultado = 0")
//    ArrayList<Pregunta> getFallos();

}
