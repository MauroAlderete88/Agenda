package com.example.agenda_contactos.Model.data.CRUD

import android.content.ContentValues
import android.content.Context
import com.example.agenda_contactos.Model.dbHelper
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_IMAGEN
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_NOMBRE
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_TELEFONO
import com.example.agenda_contactos.Model.dbHelper.Companion.TABLA_NAME

/**
 * Clase para insertar en base de datos nuevo contacto.
 */
class nuevoContacto() {

    fun new_contacto (nombre : String , telefono : String , imagen : String, context : Context) : Boolean {
        val dataBase = dbHelper(context)
        val db = dataBase.writableDatabase
        val registro = ContentValues()
        registro.put(CAMPO_NOMBRE,nombre)
        registro.put(CAMPO_TELEFONO,telefono)
        registro.put(CAMPO_IMAGEN,imagen)
        val insert = db.insert(TABLA_NAME, null, registro)
        db.close()
        return insert.equals(-1)
    }


}