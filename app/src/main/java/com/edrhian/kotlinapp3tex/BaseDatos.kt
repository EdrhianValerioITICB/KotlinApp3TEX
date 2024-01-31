package com.edrhian.kotlinapp3tex

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
    val achievements_table = "CREATE TABLE Achievements"+
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NAME TEXT," +
            "DESCRIPTION TEXT,"+
            "LOCKED BOOLEAN)"
    override fun onCreate(database: SQLiteDatabase?){
        database?.execSQL(user_table)
        database?.execSQL(stats_table)
        database?.execSQL(achievements_table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int){

    }

}