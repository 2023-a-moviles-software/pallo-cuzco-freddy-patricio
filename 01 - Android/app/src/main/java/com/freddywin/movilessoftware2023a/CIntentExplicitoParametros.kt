package com.freddywin.movilessoftware2023a

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        //varibales par obtener los siguientes datos
        val nombre: String? = intent.getStringExtra("Nombre")
        val apellido: String? = intent.getStringExtra("Apellido")
        val edad: Int = intent.getIntExtra("Apellido", 0)
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton.setOnClickListener { devolverRespuesta()}
    }

    fun devolverRespuesta() {
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Vicente")
        intentDevolverParametros.putExtra("edadModificado", 33)
        setResult(RESULT_OK, intentDevolverParametros)
        finish()
    }
}
