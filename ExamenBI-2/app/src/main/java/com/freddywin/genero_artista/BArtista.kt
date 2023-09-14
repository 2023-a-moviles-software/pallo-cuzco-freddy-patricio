package com.freddywin.genero_artista

class BArtista {
    var idArtista: String?
    var nombreArtista: String
    var valoracion: Double
    var nombreAlbum: String
    var anioArtista: Int
    var esPopular: Boolean
    var generoId: String

    constructor(
        idArtista: String,
        nombreArtista: String,
        valoracion: Double,
        nombreAlbum: String,
        anioArtista: Int,
        esPopular: Boolean,
        generoId:String
    ) {
        this.idArtista = idArtista
        this.nombreArtista = nombreArtista
        this.valoracion = valoracion
        this.nombreAlbum = nombreAlbum
        this.anioArtista = anioArtista
        this.esPopular = esPopular
       this.generoId = generoId
    }


    override fun toString(): String {
        return "${nombreArtista}" +" - "+"${valoracion}"+" - "+"${nombreAlbum}" +" - "+"${anioArtista}"
    }
}
