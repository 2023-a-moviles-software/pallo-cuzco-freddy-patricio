package com.freddywin.youtubemusic

class ClaseCanciones(
    val titulo: String,
    val artista: String,
    val nuevoImagen: String,
    val categoria:String
) {
}
 class CategoriaArtistas(
    val categoria: String,
    val artistas: List<ClaseCanciones>
) {
}


