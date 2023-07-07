package com.freddywin.movilessoftware2023a

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {

    //variables
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        //adaptador
        val listView = findViewById<ListView>(R.id.btn_ir_list_view)
        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, //layout.xml que se va usar
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        //llamamosn la funcion
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView.setOnClickListener {
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)
    }

    //Funciones para eliminar y editar
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar -> {
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    //llamr a menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        //llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        //obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id

    }

    //llama a la clase entrenador
    fun anadirEntrenador(
        adtador: ArrayAdapter<BEntrenador>
    ) {
        arreglo.add(BEntrenador(4, "Fredy", "Descripcion"))
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which -> }
        )
        builder.setNegativeButton("Cancelar", null)

        //llamamos los selecionado de string
        val opciones = resources.getStringArray(R.array.string_array_opciones_dialog)

        //Selecionamos previamente
        val seleccionesPrevia = booleanArrayOf(true, false, false)

        builder.setMultiChoiceItems(
            opciones,
            seleccionesPrevia
        ) { dialog, which, isChecked ->
            "Dio clic en el item ${which}"
        }
        val dialogo = builder.create()
        dialogo.show()
    }
}
