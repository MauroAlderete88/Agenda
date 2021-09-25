package com.example.agenda_contactos.Controller

import android.content.Context
import com.example.agenda_contactos.Model.data.CRUD.deleteContact
import com.example.agenda_contactos.Model.data.CRUD.nuevoContacto
import com.example.agenda_contactos.Model.data.CRUD.selectAllContacts
import com.example.agenda_contactos.Model.data.CRUD.updateContact
import com.example.agenda_contactos.Model.data.contacto

class AdminContacto(var context: Context) {

    var imagen : String? = null
    var nombre : String?= null
    var telefono : String? =null
    var id : Int? = null
     var contexto : Context? = null

    init {
        contexto = context
    }

    constructor( nombre: String,  telefono: String,  imagen: String,  id: Int?, context: Context) : this(context) {
        this.imagen = imagen
        this.nombre = nombre
        this.telefono = telefono
        this.id = id
        this.contexto = context
    }

    constructor(  id: Int?, context: Context) : this(context) {
        this.id = id
        this.contexto = context
    }





    fun guardar() : Boolean {
        val newContact = nuevoContacto()
         var guardado = newContact.new_contacto(nombre!!,telefono!!, imagen!! , this.context!!)
        return guardado
    }

    fun mostrarTodos() : MutableList<contacto>{
      val selectAllContacts = selectAllContacts()
      var lista : MutableList<contacto> = selectAllContacts.select_allContactos(this.context!!)
        return lista
    }

    fun modificar() : Boolean {
        val updateContact=updateContact()
        val update = updateContact.update_contacto(nombre!!,telefono!!,imagen!!,id!! , this.context!!)
        return update
    }

    fun borrar() : Boolean{
        val deleteContact = deleteContact()
        val delete = deleteContact.delete_contacto(id!!,this.context!!)
         return delete
    }


}