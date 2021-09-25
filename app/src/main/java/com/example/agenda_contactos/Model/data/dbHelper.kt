package com.example.agenda_contactos.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.agenda_contactos.Model.data.contacto

 class dbHelper (val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        const val DB_NAME = "CONTACTOS.db"
        const val TABLA_NAME = "AGENDA_REGISTRO"
        const val DB_VERSION = 3
        const val CAMPO_ID = "CODIGO"
        const val CAMPO_NOMBRE = "NOMBRE"
        const val CAMPO_TELEFONO = "TELEFONO"
        const val CAMPO_IMAGEN = "IMAGEN"
        private const val QUERY_CREATOR = "CREATE TABLE $TABLA_NAME($CAMPO_ID INTEGER PRIMARY KEY,$CAMPO_NOMBRE TEXT," +
                                          "$CAMPO_TELEFONO TEXT, $CAMPO_IMAGEN TEXT)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(QUERY_CREATOR)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLA_NAME")
        onCreate(db)
    }

}

