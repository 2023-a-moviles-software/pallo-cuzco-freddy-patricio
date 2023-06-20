package com.freddywin.artistmusic

import java.time.LocalDate

class Genero(
    var nombreGenero: String,
    var idGenero: Int,
    var puntuacion: Double,
    var fechaLanzamiento: LocalDate,
    var esPopular: Boolean
) {
    override fun toString(): String {
        val imprimir = StringBuilder()
        imprimir.appendLine("-------------------------------------------------------------")
        imprimir.appendLine("Nombre del Artista: $nombreGenero")
        imprimir.appendLine("ID del Artista: $idGenero")
        imprimir.appendLine("Valoración: $puntuacion")
        imprimir.appendLine("Datos de Creación del Artista: $fechaLanzamiento")
        imprimir.appendLine("Activo: $esPopular")
        return imprimir.toString()
    }

}
