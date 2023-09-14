package com.freddywin.genero_artista


import com.google.firebase.database.*


class Firestore {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    // Función para agregar un género a Firebase
    fun agregarGenero(genero: BGenero) {
        val generoRef = database.child("generos").push()
        generoRef.setValue(genero)
    }


    // Función para agregar un artista a Firebase
    fun agregarArtista(artista: BArtista) {
        val artistaRef = database.child("artistas").push()
        artistaRef.setValue(artista)
    }

    // Función para obtener todos los géneros de Firebase
    fun obtenerGeneros(callback: (List<BGenero>) -> Unit) {
        val generosRef = database.child("generos")
        generosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val generos = mutableListOf<BGenero>()
                for (childSnapshot in snapshot.children) {
                    val genero = childSnapshot.getValue(BGenero::class.java)
                    genero?.let { generos.add(it) }
                }
                callback(generos)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar errores aquí
            }
        })
    }

    // Función para obtener todos los artistas de Firebase por ID de género
    fun obtenerArtistasPorGeneroId(idGenero: Int, callback: (List<BArtista>) -> Unit) {
        val artistasRef =
            database.child("artistas").orderByChild("generoId").equalTo(idGenero.toDouble())
        artistasRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val artistas = mutableListOf<BArtista>()
                for (childSnapshot in snapshot.children) {
                    val artista = childSnapshot.getValue(BArtista::class.java)
                    artista?.let { artistas.add(it) }
                }
                callback(artistas)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar errores aquí
            }
        })
    }

    // Función para actualizar un género en Firebase
    fun actualizarGenero(genero: BGenero) {
        val generoRef = database.child("generos").child(genero.key ?: "")
        val generoMap = hashMapOf(
            "nombreGenero" to genero.nombreGenero,
            "calificacionGenero" to genero.calificacionGenero,
            "fechaGenero" to genero.fechaGenero,
            "esPopular" to genero.esPopular
        )

        generoRef.updateChildren(generoMap as Map<String, Any>)
    }


    // En la clase FirebaseManager.kt
    fun eliminarGeneroFS(key: String) {
        val generoRef = database.child("generos").child(key)
        generoRef.removeValue()
    }
}

