package com.freddywin.genero_artista

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    var idGeneroSeleccionado = 0
    lateinit var adaptador: ArrayAdapter<BGenero>
    val generos = mutableListOf<BGenero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        // Adaptador y ListView
        val listViewGenero = findViewById<ListView>(R.id.lst_view_mostrar_genero)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            generos
        )
        listViewGenero.adapter = adaptador

        // Obtener datos de género desde Firebase Firestore
        val firestore = FirebaseFirestore.getInstance()
        val generosRef = firestore.collection("generos")

        generosRef.get().addOnSuccessListener { result ->
            generos.clear()
            for (document in result) {
                val idGenero = document.id
                val nombreGenero = document.getString("nombreGenero")
                val calificacionGenero = document.getDouble("calificacionGenero") ?: 0.0
                val fechaGenero = document.getLong("fechaGenero")?.toInt() ?: 0
                val esPopular = document.getBoolean("esPopular") ?: false

                val bGenero = BGenero(
                    key = idGenero,
                    nombreGenero = nombreGenero ?: "",
                    calificacionGenero = calificacionGenero,
                    fechaGenero = fechaGenero,
                    esPopular = esPopular
                )

                generos.add(bGenero)
            }

            adaptador.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            // Manejar errores en la obtención de datos
        }

        // Botón crear género para cambiar de actividad
        val botonCrearGenero = findViewById<Button>(R.id.btnCrearGenero)
        botonCrearGenero.setOnClickListener {
            irActividad(GeneroEditar::class.java)
        }

        registerForContextMenu(listViewGenero)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_ver_genero -> {
                try {
                    val idGenero = idGeneroSeleccionado
                    irActividad(VerGenero::class.java, idGenero)
                } catch (e: Throwable) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
                return true
            }

            R.id.menu_editar_genero -> {
                val idGenero = idGeneroSeleccionado
                irActividad(GeneroEditar::class.java, idGenero)
                return true
            }

            R.id.menu_eliminar_genero -> {
                val generoId = idGeneroSeleccionado.toString()
                if (generoId.isNotEmpty()) {
                    eliminarGeneroFs(generoId)
                }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogoEliminarGenero() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar?")
        builder.setPositiveButton("Si") { _, _ ->
            val generoId = idGeneroSeleccionado.toString()
            if (generoId.isNotEmpty()) {
                eliminarGeneroFs(generoId)
            }
        }
        builder.setNegativeButton("No", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_genero, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val generoId = generos[info.position].key

        if (generoId != null) {
            idGeneroSeleccionado = generoId.toIntOrNull() ?: 0
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividad(clase: Class<*>, id: Int?) {
        val intent = Intent(this, clase)
        intent.putExtra("idGenero", id)
        startActivity(intent)
    }

    private fun eliminarGeneroFs(key: String) {
        val firebaseManager = Firestore()
        firebaseManager.eliminarGeneroFS(key)
    }


    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()

    }
}
