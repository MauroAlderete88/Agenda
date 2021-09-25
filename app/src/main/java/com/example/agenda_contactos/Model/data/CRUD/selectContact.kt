package com.example.agenda_contactos.Model.data.CRUD

import android.content.Context
import com.example.agenda_contactos.Model.dbHelper
import com.example.agenda_contactos.Model.dbHelper.Companion.TABLA_NAME

class selectContact (val context : Context) {

     val dataBase = dbHelper(context)
     val db = dataBase.writableDatabase


    fun select_contacto(nombre : String) : Array<String>{
        var id = selectId(nombre)
        var resultado : Array<String>? =null
        val fila = db?.rawQuery("SELECT * FROM $TABLA_NAME WHERE CODIGO = $id", null)
        if (fila?.moveToFirst()!!){
            resultado  = arrayOf(fila.getString(0),fila.getString(1),
                    fila.getString(2),fila.getString(3))
        }
        db.close()
        return resultado!!
    }

    private fun selectId(nombre : String) : Int{
        var id : Int = 0
        val resultado = db?.rawQuery("SELECT CODIGO FROM $TABLA_NAME WHERE NOMBRE = $nombre",null)
        if(resultado?.moveToFirst()!!){
            id = resultado.getInt(0)
        }
        return id
    }



}