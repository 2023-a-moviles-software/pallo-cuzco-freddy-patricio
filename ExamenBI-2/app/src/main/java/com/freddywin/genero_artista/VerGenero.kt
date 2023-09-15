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
    private var idItemSeleccionado: String? = null // Cambiada a String para evitar posibles conversiones
    var idGenero = -1
    lateinit var adaptador: ArrayAdapter<BArtista>
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_genero)

        // Inicializa el adaptador y asócialo con el ListView
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        val listView = findViewById<ListView>(R.id.lst_view_mostrar_artista)
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearArtista = findViewById<Button>(R.id.btnCrearArtista)
        botonCrearArtista.setOnClickListener {
            irActividad(ArtistaEditar::class.java)
        }
        adaptador.notifyDataSetChanged()
        idGenero = intent.getIntExtra("idGenero", -1)
        actualizarListViewArtista()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("idGenero", idGenero)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_editar_artista -> {
                val idArtista = idItemSeleccionado
                if (idArtista != null) {
                    val intent = Intent(this, ArtistaEditar::class.java)
                    intent.putExtra("idArtista", idArtista)
                    startActivity(intent)
                }
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

        // llenar las opciones del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_artista, menu)

        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        // Verificar si el adaptador y el elemento en esa posición no son nulos
        val itemSeleccionado = adaptador.getItem(id)
        if (itemSeleccionado != null) {
            idItemSeleccionado = itemSeleccionado.idArtista
        } else {
            // Manejar el caso en que el elemento sea nulo, por ejemplo, mostrar un mensaje de error
            Toast.makeText(this, "Elemento seleccionado es nulo", Toast.LENGTH_SHORT).show()
        }
    }

    fun abrirDialogoEliminarArtista() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Artista")
        builder.setMessage("¿Desea eliminar el artista?")
        builder.setPositiveButton("Sí") { dialog, _ ->
            if (idItemSeleccionado != null) {
                eliminarArtistaFirebase(idItemSeleccionado!!)
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
        adaptador.notifyDataSetChanged()




        // Obtener artistas del género desde Firebase
        val artistasRef =
            firestore.collection("generos").document(idGenero.toString()).collection("artistas")

        artistasRef.get().addOnSuccessListener { result ->
            adaptador.clear()
            for (document in result) {
                val idArtista = document.id
                val nombreArtista = document.getString("nombreArtista")
                val valoracion = document.getDouble("valoracion") ?: 0.0
                val nombreAlbum = document.getString("nombreAlbum") ?: ""
                val anioArtista = (document.getLong("anioArtista") ?: 0).toInt()
                val esPopular = document.getBoolean("esPopular") ?: false
                val generoId = document.getString("generoId") ?: ""

                adaptador.add(
                    BArtista(
                        idArtista,
                        nombreArtista ?: "",
                        valoracion,
                        nombreAlbum,
                        anioArtista,
                        esPopular,
                        generoId
                    )
                )
            }
            // Notifica al adaptador que los datos han cambiado
            adaptador.notifyDataSetChanged()

        }.addOnFailureListener { exception ->
            Toast.makeText(this, "Error al obtener los artistas", Toast.LENGTH_SHORT).show()
        }
        registerForContextMenu(listView)
        adaptador.notifyDataSetChanged()

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
