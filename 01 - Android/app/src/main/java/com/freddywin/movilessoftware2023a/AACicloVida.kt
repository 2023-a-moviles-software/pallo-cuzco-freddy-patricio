package com.freddywin.movilessoftware2023a

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.freddywin.movilessoftware2023a.databinding.ActivityAacicloVidaBinding

class AACicloVida : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAacicloVidaBinding

    var textoGlobal = ""

    fun mostrarSnackBar(texto: String) {
        textoGlobal += texto
        Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textoGlobal,
            Snackbar.LENGTH_LONG
        )
            .setAction("Action", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAacicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida2)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mostrarSnackBar("onCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackBar("onStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackBar("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackBar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackBar("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackBar("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            //guardar variables
            //primitivas
            putString("variableTextoGuardado", textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    //retirar lo que se gurada
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //recuperar variables
        //primitivas
        val textoRecuperado: String? = savedInstanceState.getString("variablesTextoGuardado")
        if (textoRecuperado != null) {
            mostrarSnackBar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida2)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}