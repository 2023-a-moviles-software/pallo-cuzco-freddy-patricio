package com.freddywin.movilessoftware2023a

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init {
            arregloBEntrenador.add(BEntrenador(1, "Fredy", "a@a.com"))
            arregloBEntrenador.add(BEntrenador(2, "Pallo", "b@b.com"))
            arregloBEntrenador.add(BEntrenador(3, "Taisia", "c@c.com"))
        }
    }
}

