package com.freddywin.genero_artista

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ArtistaEditar : AppCompatActivity() {
    var idArtista: String? = null // Variable para almacenar la clave única del artista
    var idItemSeleccionado = 0
    lateinit var adaptador: ArrayAdapter<BArtista>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artista_editar)

        // Obtener el id del artista de los extras del intent
        idArtista = intent.getStringExtra("idArtista")


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

        if (idArtista == null) {
            return
        }
        val databaseReference = FirebaseDatabase.getInstance().reference
        val artistaRef = databaseReference.child("artistas").child(idArtista!!)

        artistaRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val artista = dataSnapshot.getValue(BArtista::class.java)
                    if (artista != null) {
                        nombreArtista.setText(artista.nombreArtista)
                        valoracion.setText(artista.valoracion.toString())
                        nombreAlbum.setText(artista.nombreAlbum)
                        anioArtista.setText(artista.anioArtista.toString())
                        esPopularSi.isChecked = artista.esPopular
                        esPopularNo.isChecked = !artista.esPopular
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@ArtistaEditar,
                    "Error al cargar datos del artista",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun irActividad(clase: Class<*>) {
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
        val generoId = intent.getStringExtra("idGenero")

        val nuevoArtista = BArtista(
            nombreArtista = nombreArtista.text.toString(),
            valoracion = valoracion.text.toString().toDouble(),
            nombreAlbum = nombreAlbum.text.toString(),
            anioArtista = anioArtista.text.toString().toInt(),
            esPopular = esPopularSi.isChecked,
            generoId = generoId ?: ""
        )
        val databaseReference = FirebaseDatabase.getInstance().reference
        if (idArtista == null) {
            // Agregar un nuevo artista a Firebase
            val artistaRef = databaseReference.child("artistas").push()
            nuevoArtista.generoId = artistaRef.key ?: ""
            artistaRef.setValue(nuevoArtista)

        } else {
            // Actualizar un artista existente en Firebase
            val artistaRef = databaseReference.child("artistas").child(idArtista!!)
            artistaRef.setValue(nuevoArtista)
        }

        setResult(RESULT_OK)
        finish()//este
    }


}