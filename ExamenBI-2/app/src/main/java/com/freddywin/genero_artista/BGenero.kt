package com.freddywin.genero_artista

class BGenero {
    var idGenero: String? = null
    var nombreGenero: String
    var calificacionGenero: Double
    var fechaGenero: Int
    var esPopular: Boolean

    constructor(
        idGenero: String?,
        nombreGenero: String,
        calificacionGenero: Double,
        fechaGenero: Int,
        esPopular: Boolean
    ) {
        this.idGenero = idGenero
        this.nombreGenero = nombreGenero
        this.calificacionGenero = calificacionGenero
        this.fechaGenero = fechaGenero
        this.esPopular = esPopular
    }

    override fun toString(): String {
        return "${idGenero}: ${nombreGenero}"
    }
}
