package com.example.agenda_contactos.View

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.agenda_contactos.Controller.AdminContacto
import com.example.agenda_contactos.R
import com.example.agenda_contactos.R.id.swipe_refresh


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main)



        val adminContacto = AdminContacto(this)

        val recyclerViewContactos = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerViewContactos.layoutManager = LinearLayoutManager(this)
        val adapter = adapter_recycler(adminContacto.mostrarTodos())
        recyclerViewContactos.adapter = adapter


        //FUNCIONA EL SWIPE PERO HAY QUE HACER MAS PROLIJO EL CODIGO

        val swipe = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
            swipe.setOnRefreshListener {
                val adapter = adapter_recycler(adminContacto.mostrarTodos())
                recyclerViewContactos.adapter = adapter
                swipe.isRefreshing = false
            }


        val boton = findViewById<Button>(R.id.agregar_nuevo)
        boton.setOnClickListener(){
       val intent = Intent(this, newContact::class.java)
        startActivity(intent)
        }


    }


}

