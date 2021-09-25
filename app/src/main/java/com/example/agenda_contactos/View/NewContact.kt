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
import com.example.agenda_contactos.databinding.ActivityNewContactBinding
import java.io.File
import java.io.IOException


class newContact : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
     var ruta_imagen : String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.IVcontacto?.setOnClickListener() {
            iniciarCam()
        }

        binding.btonGuardar.setOnClickListener() {
            val agendarContacto = AdminContacto(binding.ETnombre.text.toString(),
                    binding.ETtelefono.text.toString(),
                    this.ruta_imagen!!,
                    null, this).guardar()

            if (agendarContacto==false){
                Toast.makeText(this, "Guardado exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Guardado no pudo realizarse", Toast.LENGTH_SHORT).show()
            }

            this.finish()

        }

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
            binding.IVcontacto.setImageBitmap(imageBitmap)
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