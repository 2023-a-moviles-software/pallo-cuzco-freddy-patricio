package com.freddywin.genero_artista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class GeneroEditar : AppCompatActivity() {
    fun llenarDatosGenero() {
        val nombreGenero = findViewById<EditText>(R.id.editar_genero_nombre)
        val calificacion = findViewById<EditText>(R.id.editar_genero_puntuacion)
        val fechagenero = findViewById<EditText>(R.id.editor_genero_fecha)
        val esPopularSi = findViewById<RadioButton>(R.id.rdb_espopular_generoSi)
        val esPopularNo = findViewById<RadioButton>(R.id.rdb_espopular_generoNo)
        val id = intent.getIntExtra("idGenero", -1)
        if (id == -1) {
            return
        }
        val generoEncontrado = BasesDatosMemoria.obtenerDatosGenero(id)
        if (generoEncontrado != null) {
            nombreGenero.setText(generoEncontrado.nombreGenero)
            calificacion.setText(generoEncontrado.calificacionGenero.toString())
            fechagenero.setText(generoEncontrado.fechaGenero.toString())
            esPopularSi.isChecked = generoEncontrado.esPopular
            esPopularNo.isChecked = !generoEncontrado.esPopular
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genero_editar)

        //llenar arreglos
        llenarDatosGenero()
        val botonGuardarGenero = findViewById<Button>(R.id.btn_guardar_genero)
        botonGuardarGenero.setOnClickListener {

            try {
                accionGenero()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

            val nuevoIntent = Intent(this, MainActivity::class.java)
            startActivity(nuevoIntent)
        }

    }


    fun accionGenero() {
        val nombreGenero = findViewById<EditText>(R.id.editar_genero_nombre)
        val calificacion = findViewById<EditText>(R.id.editar_genero_puntuacion)
        val fechagenero = findViewById<EditText>(R.id.editor_genero_fecha)
        val esPopularSi = findViewById<RadioButton>(R.id.rdb_espopular_generoSi)

        val nuevoGenero = BGenero(
            key = intent.getStringExtra("idGenero"), // Mantenemos la clave original
            nombreGenero = nombreGenero.text.toString(),
            calificacionGenero = calificacion.text.toString().toDouble(),
            fechaGenero = fechagenero.text.toString().toInt(),
            esPopular = esPopularSi.isChecked
        )
        val firestore = Firestore()
        val id = intent.getIntExtra("idGenero", -1)

        if (id == -1) {
            firestore.agregarGenero(
                nuevoGenero
            )
        } else {
            nuevoGenero.key = id.toString()
            firestore.actualizarGenero(nuevoGenero)
        }
    }


}


