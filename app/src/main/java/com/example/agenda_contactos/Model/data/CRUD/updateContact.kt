package com.example.agenda_contactos.Model.data.CRUD

import android.content.ContentValues
import android.content.Context
import com.example.agenda_contactos.Model.dbHelper
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_ID
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_IMAGEN
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_NOMBRE
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_TELEFONO
import com.example.agenda_contactos.Model.dbHelper.Companion.TABLA_NAME

class updateContact {

    fun update_contacto(nombre : String , telefono : String , imagen : String , id : Int ,context : Context) : Boolean {
        val dataBase = dbHelper(context)
        val db = dataBase.writableDatabase
        var modificar = ContentValues()
        modificar.put(CAMPO_NOMBRE,nombre)
        modificar.put(CAMPO_TELEFONO,telefono)
        modificar.put(CAMPO_IMAGEN,imagen)
        val modificacion = db.update(TABLA_NAME, modificar, "$CAMPO_ID=$id",null)
        db.close()
        return modificacion!=-1
    }

}