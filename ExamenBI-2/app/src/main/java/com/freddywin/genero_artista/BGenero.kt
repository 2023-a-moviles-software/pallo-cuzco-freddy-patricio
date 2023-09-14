package com.freddywin.genero_artista

class BGenero {
    var key: String? = null
    var nombreGenero: String
    var calificacionGenero: Double
    var fechaGenero: Int
    var esPopular: Boolean

    constructor(
        idBGenero: String?,
        nombreGenero: String,
        calificacionGenero: Double,
        fechaGenero: Int,
        esPopular: Boolean
    ) {
        this.nombreGenero = nombreGenero
        this.calificacionGenero = calificacionGenero
        this.fechaGenero = fechaGenero
        this.esPopular = esPopular
    }

    override fun toString(): String {
        return "${key}: ${nombreGenero}"
    }
}
