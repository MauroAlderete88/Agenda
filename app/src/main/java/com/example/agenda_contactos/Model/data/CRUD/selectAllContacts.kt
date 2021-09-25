package com.example.agenda_contactos.Model.data.CRUD

import android.content.Context
import com.example.agenda_contactos.Model.data.contacto
import com.example.agenda_contactos.Model.dbHelper
import com.example.agenda_contactos.Model.dbHelper.Companion.TABLA_NAME

class selectAllContacts {



    fun select_allContactos(context : Context) : MutableList<contacto>{
        val dataBase = dbHelper(context)
        val db_read = dataBase.readableDatabase
        var listado : MutableList<contacto> =  mutableListOf()
        val cursor = db_read.query(TABLA_NAME, null, null, null, null,null, null)
        with(cursor) {
            while (moveToNext()) {
                var nombre = cursor.getString(1)
                var telefono = cursor.getString(2)
                var imagen = cursor.getString(3)
                var id = cursor.getInt(0)
                var contacto = contacto(id,nombre,telefono,imagen)
                listado.add(contacto)
            }
        }
        return listado
    }
}