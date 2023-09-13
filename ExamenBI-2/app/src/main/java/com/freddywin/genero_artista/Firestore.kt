package com.freddywin.genero_artista


import com.google.firebase.database.FirebaseDatabase

class Firestore {
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    // Función para agregar un género a Firebase
    fun agregarGenero(genero: BGenero) {
        val generoRef = database. ("generos").push()
        genero.idBGenero = generoRef.key
        generoRef.setValue(genero)
    }

    // Función para agregar un artista a Firebase
    fun agregarArtista(artista: BArtista) {
        val artistaRef = database.child("artistas").push()
        artista.idArtista = artistaRef.key
        artistaRef.setValue(artista)
    }
}