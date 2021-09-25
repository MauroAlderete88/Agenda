package com.example.agenda_contactos.View

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.agenda_contactos.Controller.AdminContacto
import com.example.agenda_contactos.R
import com.example.agenda_contactos.databinding.ActivityViewContactBinding
import java.io.File
import java.io.IOException

class viewContact : AppCompatActivity() {

    private lateinit var binding:ActivityViewContactBinding
     var ruta_imagen : String? = null

    companion object{
        private var id : Int? = null
        private var nombre : String?=null
        private var imagen : String?=null
        private var telefono : String?=null
        private var myUry: Uri?=null
        private var fotoNueva : Boolean =false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contact)

        binding = ActivityViewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrar()

        binding.CamaraButton.setOnClickListener(){
            iniciarCam()
        }

        binding.modificarBoton.setOnClickListener(){
            modificar()
        }

        binding.eliminarBoton.setOnClickListener(){
            eliminar()
        }

    }


    private fun cargarDatos(){
         val bundle = intent.extras
         nombre = bundle?.getString("nombre")
         telefono = bundle?.getString("telefono")
         imagen = bundle?.getString("imagen")
         myUry = Uri.parse(imagen)
         id = bundle?.getInt("codigo")
    }

    private fun limpiarDatos(){
        nombre = ""
        telefono = ""
        imagen = ""
        id = null
    }

    fun mostrar(){
        cargarDatos()
        binding.ivContacto.setImageURI(myUry)
        binding.NombreContacto.setText(nombre)
        binding.TelefonoContacto.setText(telefono)
    }

    fun eliminar(){
        var adminContacto = AdminContacto(id = id ,context = this)
        val delete = adminContacto.borrar()

        if (!delete){
            Toast.makeText(this, "Borrado no pudo realizarse", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Borrado Exitoso", Toast.LENGTH_SHORT).show()}

        this.finish()

    }

    fun modificar() {
        var adminContacto : AdminContacto

        if(ruta_imagen.isNullOrBlank()){
                     adminContacto = AdminContacto(nombre = binding.NombreContacto.text.toString(),
                             telefono = binding.TelefonoContacto.text.toString(), imagen = imagen!!, id = id!!, context = this)
        } else{
                     adminContacto = AdminContacto(nombre = binding.NombreContacto.text.toString(),
                             telefono = binding.TelefonoContacto.text.toString(), imagen = ruta_imagen!!, id = id!!, context = this) }

            val update = adminContacto.modificar()
            if (!update) {
                Toast.makeText(this, "Modificacion no pudo realizarse", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Modificado Exitoso", Toast.LENGTH_SHORT).show()}
        limpiarDatos()
        this.finish()
    }

    private fun iniciarCam() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var imagenArchivo : File? = null
        try {
            imagenArchivo = crearImagen()
        }catch (e : IOException){
            Log.e("error",e.toString())
        }
        var fotoUri : Uri = FileProvider.getUriForFile(this,"com.cdp.camara.fileprovider", imagenArchivo!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == RESULT_OK){
            val imageBitmap = BitmapFactory.decodeFile(ruta_imagen)
            binding.ivContacto.setImageBitmap(imageBitmap)
        }
    }

    private fun crearImagen () : File {
        var nombreImagen : String = "foto_" //Estructura del nombre de la imagen
        var directorio : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES) //Donde se guarda la imagen.
        var imagen : File?= File.createTempFile(nombreImagen,".jpg", directorio)
        if (imagen != null) {
            ruta_imagen = imagen.absolutePath
        }
        return imagen!!
    }


}