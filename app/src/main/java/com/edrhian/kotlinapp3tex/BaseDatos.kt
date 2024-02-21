package com.edrhian.kotlinapp3tex

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.edrhian.kotlinapp3tex.data.Logro

class BaseDatos(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version){

    val user_table = "CREATE TABLE Users"+
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NAME TEXT," +
            "MAIL TEXT," +
            "PASSWORD TEXT)"

    val stats_table = "CREATE TABLE Stats"+
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "WINS INT," +
            "LOSSES INT," +
            "TIED INT," +
            "TIME_PLAYED TIME," +
            "ELO INT)"
    //TODO Una vez eliminada la tabla se puede borrar estos statements
    val delete_tabla_logros = "DROP TABLE IF EXISTS Achievements"
    val delete_tabla_logros2 = "DROP TABLE IF EXISTS Logros"

    val logros_tabla = "CREATE TABLE Logros" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NOMBRE TEXT," +
            "DESCRIPCION TEXT," +
            "IMAGEN INT," +
            "BLOQUEADO Int," +
            "USUARIO INT)"
    val insert_logros = "INSERT INTO Logros (NOMBRE, DESCRIPCION, IMAGEN, BLOQUEADO, USUARIO) VALUES " +
            "('La primera de muchas','Gana una partida',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('La primera de muchas (desgraciadamente)','Pierde una partida',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('¿Y ahora qué?','Empata una partida',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Tic Tac','Gana una partida por falta de tiempo del oponente',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Piensa rápido','Pierde una partida por falta de tiempo',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Principiante','Gana 10 partidas',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Avanzado','Gana 100 partidas',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Maestro de las fichas','Gana 1000 partidas',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Juego mental','Gana una partida por abandono de oponente',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('ALT+F4','Pierde una partida por abandono',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Pasando el tiempo','Juega un total de 1 hora',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Aflojale al 3T-EX','Juega un total de 1 dia',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Jugador inseguro','Tarda más de 5 minutos en poner una pieza',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('He perdido la cuenta','Coloca 1000 piezas en el tablero',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Dedicación','Haz login en el juego durante 7 dias seguidos',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('Cara a cara (literalmente)','Juega una partida local',"+R.drawable.baseline_lock_24+", 1, 1)," +
            "('En contra de ChatgpTTTEX','Juega una partida contra la máquina',"+R.drawable.baseline_lock_24+", 1, 1)"
    override fun onCreate(database: SQLiteDatabase?){
        database?.execSQL(user_table)
        database?.execSQL(stats_table)
        database?.execSQL(delete_tabla_logros)
        database?.execSQL(delete_tabla_logros2)
        database?.execSQL(logros_tabla)
        database?.execSQL(insert_logros)
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int){

    }

}