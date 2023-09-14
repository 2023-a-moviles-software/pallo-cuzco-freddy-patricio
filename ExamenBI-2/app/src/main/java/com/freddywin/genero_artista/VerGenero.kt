package com.freddywin.genero_artista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class VerGenero : AppCompatActivity() {
    private var idItemSeleccionado = 0
    var idGenero = -1
    lateinit var adaptador: ArrayAdapter<BArtista>
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_genero)
        actualizarListViewArtista()

        val botonCrearArtista = findViewById<Button>(R.id.btnCrearArtista)
        botonCrearArtista.setOnClickListener {
            irActividad(ArtistaEditar::class.java)
        }
        idGenero = intent.getIntExtra("idGenero", -1)
        actualizarListViewArtista()


        // Escuchar cambios en la colección de artistas de Firebase Firestore para el género actual
        firestore.collection("generos").document(idGenero.toString()).collection("artistas")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Manejar errores
                } else {
                    // Limpiar la lista y agregar los artistas desde Firebase Firestore
                    artistas.clear()
                    snapshot?.documents?.forEach { document ->
                        val idArtista = document.id
                        val nombreArtista = document.getString("nombreArtista")
                        // Agregar los datos a la lista de artistas
                        artistas.add(BArtista(idArtista, nombreArtista ?: ""))
                    }
                    // Actualizar el adaptador
                    adaptador.notifyDataSetChanged()
                }
            }
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("idGenero", idGenero)
        actualizarListViewArtista()
        startActivity(intent)

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_editar_artista -> {
                val idArtista = idItemSeleccionado
                val intent = Intent(this, ArtistaEditar::class.java)
                intent.putExtra("idArtista", idArtista)
                startActivity(intent)
                return true
            }

            R.id.menu_eliminar_artista -> {
                abrirDialogoEliminarArtista()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_artista, menu)
        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = adaptador.getItem(id)?.idArtista ?: 0
    }

    fun abrirDialogoEliminarArtista() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Artista")
        builder.setMessage("¿Desea eliminar el artista?")
        builder.setPositiveButton("Sí") { dialog, _ ->
            if (idItemSeleccionado != 0) {
                eliminarArtistaFirestore(idItemSeleccionado)
            } else {
                Toast.makeText(this, "Error al eliminar el artista", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("No", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    fun actualizarListViewArtista() {
        val listView = findViewById<ListView>(R.id.lst_view_mostrar_artista)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        listView.adapter = adaptador

        // Obtener artistas del género desde Firebase
        val artistasRef =
            firestore.collection("generos").document(idGenero.toString()).collection("artistas")

        artistasRef.get().addOnSuccessListener { result ->
            adaptador.clear()
            for (document in result) {
                val idArtista = document.id
                val nombreArtista = document.getString("nombreArtista")
                adaptador.add(BArtista(idArtista, nombreArtista ?: ""))
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this, "Error al obtener los artistas", Toast.LENGTH_SHORT).show()
        }

        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }


    private fun eliminarArtistaFirebase(idArtista: String) {
        // Referencia al documento del artista
        val artistaRef = firestore
            .collection("generos")
            .document(idGenero.toString())
            .collection("artistas")
            .document(idArtista)

        artistaRef.delete()
            .addOnSuccessListener {
                // Eliminación exitosa
                Toast.makeText(this, "Artista eliminado correctamente", Toast.LENGTH_SHORT).show()
                actualizarListViewArtista()
            }
            .addOnFailureListener { exception ->
                // Manejar errores en la eliminación
                Toast.makeText(this, "Error al eliminar el artista", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onRestart() {
        super.onRestart()
        actualizarListViewArtista()
    }


    override fun onResume() {
        super.onResume()
        actualizarListViewArtista()
    }
}