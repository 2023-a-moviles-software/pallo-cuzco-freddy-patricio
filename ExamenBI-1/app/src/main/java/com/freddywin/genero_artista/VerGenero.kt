package com.freddywin.genero_artista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class VerGenero : AppCompatActivity() {
    var arregloViewArtista = BasesDatosMemoria.arregloBArtista
    private var idItemSeleccionado = 0
    var idGenero = -1
    lateinit var adaptador: ArrayAdapter<BArtista>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_genero)
        actualizarListViewArtista()

        val botonCrearArtista = findViewById<Button>(R.id.btnCrearArtista)
        botonCrearArtista.setOnClickListener {
            irActividad(ArtistaEditar::class.java)
        }
        idGenero = intent.getIntExtra("idGenero", -1)
        arregloViewArtista = BasesDatosMemoria.obtenerArtistasGeneroId(idGenero)
        actualizarListViewArtista()
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
        idItemSeleccionado = arregloViewArtista[id].idArtista
    }

    fun abrirDialogoEliminarArtista() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Artista")
        builder.setMessage("¿Desea eliminar el artista?")
        builder.setPositiveButton("Sí") { dialog, _ ->
            if (idItemSeleccionado.let { BasesDatosMemoria.eliminarArtista(it) }) {
                arregloViewArtista = BasesDatosMemoria.obtenerArtistasGeneroId(idGenero)///este
                Toast.makeText(this, "Artista eliminado correctamente", Toast.LENGTH_SHORT).show()
                actualizarListViewArtista()
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
            BasesDatosMemoria.arregloBArtista
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
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