package com.freddywin.genero_artista

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
        val firestoreManager = Firestore()
        firestoreManager.obtenerGenerosRealtime { listaGeneros ->
            generos.clear()
            generos.addAll(listaGeneros)
            adaptador.notifyDataSetChanged()
        }

        // Obtener datos de género desde Firebase Firestore
        val firestore = FirebaseDatabase.getInstance()
        val generosRef = firestore.reference.child("generos")

        generosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                generos.clear()
                for (childSnapshot in snapshot.children) {
                    val nombreGenero =
                        childSnapshot.child("nombreGenero").getValue(String::class.java)
                    val calificacionGenero =
                        childSnapshot.child("calificacionGenero").getValue(Double::class.java)
                    val fechaGenero = childSnapshot.child("fechaGenero").getValue(Int::class.java)
                    val esPopular = childSnapshot.child("esPopular").getValue(Boolean::class.java)

                    val bGenero = BGenero(
                        idGenero = childSnapshot.key,
                        nombreGenero = nombreGenero ?: "",
                        calificacionGenero = calificacionGenero ?: 0.0,
                        fechaGenero = fechaGenero ?: 0,
                        esPopular = esPopular ?: false
                    )

                    generos.add(bGenero)
                }

                adaptador.notifyDataSetChanged()
                Log.d("FirebaseData", "Cantidad de géneros: ${generos.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar errores aquí
                Log.e("FirebaseError", "Error al obtener datos de Firebase: ${error.message}")
                Toast.makeText(
                    this@MainActivity,
                    "Error al obtener datos de Firebase",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


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
        val generoId = generos[info.position].idGenero

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
