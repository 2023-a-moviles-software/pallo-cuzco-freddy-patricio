package com.freddywin.genero_artista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

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
        val firestore =
            Firestore() // Reemplaza Firestore() con tu clase que interactúa con Firestore
        firestore.obtenerGenero(id.toString()) { genero ->
            if (genero != null) {
                nombreGenero.setText(genero.nombreGenero)
                calificacion.setText(genero.calificacionGenero.toString())
                fechagenero.setText(genero.fechaGenero.toString())
                esPopularSi.isChecked = genero.esPopular
                esPopularNo.isChecked = !genero.esPopular
            }
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
            idGenero = intent.getStringExtra("idGenero"), // Mantenemos la clave original
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
            nuevoGenero.idGenero = id.toString()
            firestore.actualizarGenero(nuevoGenero)
        }
    }


}


