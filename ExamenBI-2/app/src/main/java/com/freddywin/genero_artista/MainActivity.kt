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
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var idItemSeleccionado = 0
    var idBorrar = 0
    lateinit var adaptador: ArrayAdapter<BGenero>
    val genero = mutableListOf<BGenero>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        // adaptador
        val listViewGenero = findViewById<ListView>(R.id.lst_view_mostrar_genero)
        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // layout.xml que se va usar
            genero
        )
        listViewGenero.adapter = adaptador
        adaptador.notifyDataSetChanged()

        // Obtener datos de género desde Firebase Firestore
        val firestore = FirebaseFirestore.getInstance()
        val generosRef = firestore.collection("generos")

        generosRef.get().addOnSuccessListener { result ->
            genero.clear() // Limpiar la lista antes de agregar datos
            for (document in result) {
                val idBGenero = document.id
                val nombreGenero = document.getString("nombreGenero")
                val calificacionGenero = document.getDouble("calificacionGenero") ?: 0.0
                val fechaGenero = document.getLong("fechaGenero")?.toInt() ?: 0
                val esPopular = document.getBoolean("esPopular") ?: false

                val bGenero = BGenero(
                    key = idBGenero,
                    nombreGenero = nombreGenero ?: "",
                    calificacionGenero = calificacionGenero,
                    fechaGenero = fechaGenero,
                    esPopular = esPopular
                )

                genero.add(bGenero)
            }

            // Notificar al adaptador que los datos han cambiado
            adaptador.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            // Manejar errores en la obtención de datos
        }


        //boton crear genero para cambiar de actividad
        val botonCrearGenero = findViewById<Button>(R.id.btnCrearGenero)
        botonCrearGenero.setOnClickListener {
            irActividad(GeneroEditar::class.java)
        }
        registerForContextMenu(listViewGenero)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_ver_genero -> {
                //"Hacer algo con: ${idItemSeleccionado}"
                try {
                    val idGenero = idItemSeleccionado
                    irActividad(VerGenero::class.java, idGenero)
                } catch (e: Throwable) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }

                return true
            }

            R.id.menu_editar_genero -> {
                val idGenero = idItemSeleccionado
                irActividad(GeneroEditar::class.java, idGenero)
                return true
            }

            R.id.menu_eliminar_genero -> {
                val generoId =
                    idItemSeleccionado.toString() // Obtener el ID del género seleccionado
                if (generoId != null) {
                    eliminarGeneroFs(generoId) // Llamar a la función para eliminar pasando la clave (key)
                }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogoEliminarGenero() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton("Si")
        { // Callback
                dialog, _ ->
            val generoId = idItemSeleccionado.toString()
            if (generoId.isNotEmpty()) {
                eliminarGeneroFs(generoId)
            }
        }
        builder.setNegativeButton("No", null)
        val dialogo = builder.create()
        dialogo.show()
    }


    //funcion los menu Genero
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_genero, menu)
        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val generoId = genero[info.position].key

        if (generoId != null) {
            idItemSeleccionado = generoId.toIntOrNull() ?: 0
        }

        val idBorrarGenero = info.id.toInt()
        idBorrar = idBorrarGenero
    }


    //clase ir actividad
    class IrActividadGenero(
        val context: Context,
        var callback: (intent: Intent) -> Unit = { }
    ) {
        fun cambiarActividad(clase: Class<*>) {
            val intent = Intent(context, clase)
            callback(intent)
            context.startActivity(intent)
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)
        // this.startActivity()
    }

    fun irActividad(clase: Class<*>, id: Int?) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        intent.putExtra("idGenero", id)
        startActivity(intent)
        // this.startActivity()
    }


    //----------------Metodo actulizar list View
    private fun actualizarListViewGenero() {
        val listView = findViewById<ListView>(R.id.lst_view_mostrar_genero)


        //crear la instancia
        val firesotore = Firestore()

        //obtener generos
        firesotore.obtenerGeneros { generos ->
            val generoAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                generos
            )
            //establecer el adaptador
            listView.adapter = generoAdapter

            //notificar
            generoAdapter.notifyDataSetChanged()
        }
    }


    fun eliminarGeneroFs(key: String) {
        val firebaseManager = Firestore()
        firebaseManager.eliminarGeneroFS(key)
    }

    override fun onRestart() {
        super.onRestart()
        actualizarListViewGenero()
    }

    override fun onResume() {
        super.onResume()
        actualizarListViewGenero()
    }
}

