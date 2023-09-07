package com.freddywin.movilessoftware2023a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.provider.ContactsContract
import android.widget.Button
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.freddywin.movilessoftware2023a.BEntrenador


class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val data = result.data
                "${data?.getStringExtra("NombreModificado")}"
            }
        }
    }

    //codigo para el callback
    val callbackIntentPickUri = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode === RESULT_OK) {
            if (result.data != null) {
                if (result.data!!.data != null) {
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(uri, null, null, null, null, null)
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )

                    val telefono = cursor?.getString(indiceTelefono!!)
                    cursor?.close()
                    "Telefono ${telefono}"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Base de datos SQLite
        //Main CTIVITY
        EBasesDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        //Se agrega un variable para el boton
        val botonCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )

        botonCicloVida.setOnClickListener {
            irActividad(AACicloVida::class.java)
        }

        botonCicloVida.setOnClickListener {
            irActividad(ACicloVida::class.java)
        }

        //Se agrega una variable para el boton listView
        val botonListView = findViewById<Button>(
            R.id.btn_ir_list_view,
        )

        botonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        //Creamos los intents de implicito
        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackIntentPickUri.launch(intentConRespuesta)
        }


        //Creamos los intents de implicito
        val botonIntentExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntentExplicito.setOnClickListener {
            abrirActividadParamentro(CIntentExplicitoParametros::class.java)
        }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite.setOnClickListener {
            irActividad(ECrudEntrenador::class.java)
        }

        val botonRView = findViewById<Button>(R.id.btn_revcycler_view)
        botonRView.setOnClickListener {
            irActividad(FRecyclerView::class.java)
        }

        //Para el boton maps
        val botonGoogleMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGoogleMaps.setOnClickListener {
            irActividad(GGoogleMaps::class.java)
        }

        //para boton ui auth
        val botonUiAuth = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonUiAuth.setOnClickListener {
            irActividad(HFirebaseUIAuth::class.java)
        }

        //para el boton firestore
        val botonFirestore = findViewById<Button>(R.id.btn_intent_firestore)
        botonFirestore.setOnClickListener {
            irActividad(IFirestore::class.java)
        }

    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
        //this.startActivity()
    }

    fun abrirActividadParamentro(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)

        //enviar paramentros
        //(aceptamos primitivas)
        intentExplicito.putExtra("Nombre", "Julian")
        intentExplicito.putExtra("Apellido", "Casablancas")
        intentExplicito.putExtra("Edad", "39")
        //enviamos el intent con respuesta

        intentExplicito.putExtra(
            "entrenador",
            BEntrenador(1, "Julian", "Entrenador")
        )
        //Recibimos la respuesta
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }
}