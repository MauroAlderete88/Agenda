package com.example.agenda_contactos.View

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_contactos.Model.data.contacto
import com.example.agenda_contactos.R
import com.example.agenda_contactos.databinding.ItemContactoBinding
import kotlin.concurrent.fixedRateTimer

class adapter_recycler(val contactos_lista_m: MutableList<contacto>) :RecyclerView.Adapter<adapter_recycler.contactoHolder>() {

    class contactoHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemContactoBinding.bind(view) //se crea el binding en recyclerView
        fun render(contactos_lista_m: contacto){

              val myUry: Uri = Uri.parse(contactos_lista_m.imagen)
              val context : Context
              context = binding.numeroContacto.context
              binding.profileImage.setImageURI(myUry)
              binding.NombreContacto.setText(contactos_lista_m.nombre)
              binding.numeroContacto.setText(contactos_lista_m.telefono)


              binding.editContact.setOnClickListener(){
              val  detail = Intent(context, viewContact::class.java)
                  detail.putExtra("codigo", contactos_lista_m.id)
                  detail.putExtra("nombre",contactos_lista_m.nombre)
                  detail.putExtra("telefono", contactos_lista_m.telefono)
                  detail.putExtra("imagen",contactos_lista_m.imagen)
                 context.startActivity(detail);
             }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return contactoHolder(layoutInflater.inflate(R.layout.item_contacto, parent, false))

    }

    override fun onBindViewHolder(holder: contactoHolder, position: Int) {
        holder.render(contactos_lista_m[position])
    }

    override fun getItemCount(): Int = contactos_lista_m.size

}
