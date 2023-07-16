package com.freddywin.genero_artista

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*
import androidx.appcompat.app.AlertDialog

class ArtistaEditar : AppCompatActivity() {
    var idItemSeleccionado = 0
    lateinit var adaptador: ArrayAdapter<BArtista>


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista_editar)


        //llamar metodo llenar datos
        llenarDatosArtista()

        val botonGuardarArtista = findViewById<Button>(R.id.btn_guardar_artista)
        botonGuardarArtista.setOnClickListener {
            accionArtista()
            finish()
        }
    }

    fun llenarDatosArtista() {
        val nombreArtista = findViewById<EditText>(R.id.editar_artista_nombre)
        val valoracion = findViewById<EditText>(R.id.editar_artista_valoracion)
        val nombreAlbum = findViewById<EditText>(R.id.editor_artista_album)
        val anioArtista = findViewById<EditText>(R.id.editor_artista_anio)
        val esPopularSi = findViewById<RadioButton>(R.id.rdb_espopular_artistaSi)
        val esPopularNo = findViewById<RadioButton>(R.id.rdb_espopular_artistaNo)
        val idGenero = intent.getIntExtra("idGenero", -1)
        val idArtista = intent.getIntExtra("idArtista", -1)

        if (idArtista == -1) {
            return
        }
        val artistaEncontrado = BasesDatosMemoria.obtenerDatosArtista(idArtista)
        if (artistaEncontrado != null) {
            nombreArtista.setText(artistaEncontrado.nombreArtista)
            valoracion.setText(artistaEncontrado.valoracion.toString())
            nombreAlbum.setText(artistaEncontrado.nombreAlbum)
            anioArtista.setText(artistaEncontrado.anioArtista.toString())
            esPopularSi.isChecked = artistaEncontrado.esPopular
            esPopularNo.isChecked = artistaEncontrado.esPopular
        }
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("idGenero", intent.getIntExtra("idGenero", -1))
        startActivity(intent)
    }

    fun accionArtista() {
        val nombreArtista = findViewById<EditText>(R.id.editar_artista_nombre)
        val valoracion = findViewById<EditText>(R.id.editar_artista_valoracion)
        val nombreAlbum = findViewById<EditText>(R.id.editor_artista_album)
        val anioArtista = findViewById<EditText>(R.id.editor_artista_anio)
        val esPopularSi = findViewById<RadioButton>(R.id.rdb_espopular_artistaSi)
        val generoId = intent.getIntExtra("idGenero", -1)

        val nuevoArtista = BArtista(
            idArtista = 0,
            nombreArtista = nombreArtista.text.toString(),
            valoracion = valoracion.text.toString().toDouble(),
            nombreAlbum = nombreAlbum.text.toString(),
            anioArtista = anioArtista.text.toString().toInt(),
            esPopular = esPopularSi.isChecked,
            generoId = generoId,
        )
        val id = intent.getIntExtra("idArtista", -1)
        if (id == -1) {
            BasesDatosMemoria.agregarArtista(nuevoArtista)
            setResult(RESULT_OK)

        } else {
            nuevoArtista.idArtista = id
            BasesDatosMemoria.actualizarArtista(nuevoArtista)
            setResult(RESULT_OK)
        }
        //irActividad(VerGenero::class.java)
        finish()//este
    }


}