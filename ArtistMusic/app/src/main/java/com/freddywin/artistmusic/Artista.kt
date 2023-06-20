package com.freddywin.artistmusic

import java.time.LocalDate

class Artista (
    var nombreArtista:String,
    var idArtista:Int,
    var valoracion:Double,
    var datosCreacionArtista:LocalDate,
    var activo:Boolean
){

    override fun toString(): String {
        val imprimir = StringBuilder()
        imprimir.appendLine("-------------------------------------------------------------")
        imprimir.appendLine("Nombre del Artista: $nombreArtista")
        imprimir.appendLine("ID del Artista: $idArtista")
        imprimir.appendLine("Valoración: $valoracion")
        imprimir.appendLine("Activo: $activo")
        imprimir.appendLine("Datos de Creación del Artista: $datosCreacionArtista")
        return imprimir.toString()
    }
}