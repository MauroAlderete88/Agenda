package com.example.agenda_contactos.Model.data.CRUD

import android.content.Context
import com.example.agenda_contactos.Model.dbHelper
import com.example.agenda_contactos.Model.dbHelper.Companion.CAMPO_ID
import com.example.agenda_contactos.Model.dbHelper.Companion.TABLA_NAME

class deleteContact {
    
    
    fun delete_contacto(id : Int, context : Context) : Boolean{
        val dataBase = dbHelper(context)
         val db = dataBase.writableDatabase
        val borrar = db.delete(TABLA_NAME,"$CAMPO_ID=$id",null)
        db.close()
        return borrar != -1
    }
}