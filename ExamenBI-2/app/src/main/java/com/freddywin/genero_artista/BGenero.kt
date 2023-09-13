package com.freddywin.genero_artista

class BGenero {
    var idBGenero: Int?
    var nombreGenero: String
    var calificacionGenero: Double
    var fechaGenero: Int
    var esPopular: Boolean

    constructor(
        idBGenero: Int? = null,
        nombreGenero: String,
        calificacionGenero: Double,
        fechaGenero: Int,
        esPopular: Boolean
    ) {
        this.idBGenero = idBGenero
        this.nombreGenero = nombreGenero
        this.calificacionGenero = calificacionGenero
        this.fechaGenero = fechaGenero
        this.esPopular = esPopular
    }

    override fun toString(): String {
        return "${idBGenero}: ${nombreGenero}"
    }
}
