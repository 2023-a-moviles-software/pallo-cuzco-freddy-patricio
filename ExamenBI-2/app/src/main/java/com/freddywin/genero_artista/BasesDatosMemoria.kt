package com.freddywin.genero_artista

class BasesDatosMemoria {

    companion object {
        val arregloBGenero = arrayListOf<BGenero>()
        val arregloBArtista = arrayListOf<BArtista>()

        //-----------------Sumar el ID-----------------
        var ultimoIdGenero = 5
        fun agregarGenero(genero: BGenero) {
            genero.idBGenero = ultimoIdGenero
            arregloBGenero.add(genero)
            ultimoIdGenero++
        }

        var ultimoIdArtista = 4
        fun agregarArtista(artista: BArtista) {
            artista.idArtista = ultimoIdArtista
            arregloBArtista.add(artista)
            ultimoIdArtista++
        }

        //------------------------actualizar---------------
        fun actualizarGenero(genero: BGenero) {
            var auxIndexGenero = arregloBGenero.indexOfFirst {
                it.idBGenero == genero.idBGenero
            }
            if (auxIndexGenero != -1) {
                arregloBGenero[auxIndexGenero] = genero
            }
        }

        fun actualizarArtista(artista: BArtista) {
            var auxIndexArtista = arregloBArtista.indexOfFirst {
                it.idArtista == artista.idArtista
            }
            if (auxIndexArtista != -1) {
                arregloBArtista[auxIndexArtista] = artista
            }
        }


        //-----------------------obterner datos------------------
        fun obtenerDatosGenero(id: Int): BGenero? {
            return arregloBGenero.firstOrNull {
                it.idBGenero == id
            }
        }

        fun obtenerDatosArtista(id: Int): BArtista? {
            return arregloBArtista.firstOrNull {
                it.idArtista == id
            }
        }

        fun obtenerArtistasGeneroId(idGenero: Int): ArrayList<BArtista> {
            val auxArregloArtista = arrayListOf<BArtista>()
            for (artista in arregloBArtista) {
                if (artista.generoId == idGenero) {
                    auxArregloArtista.add(
                        artista
                    )
                }
            }
            return auxArregloArtista
        }

        //----------------------eliminar----------------------------
        fun eliminarGenero(id: Int): Boolean {
            val generoAEliminar = arregloBGenero.find { it.idBGenero == id }

            if (generoAEliminar != null) {
                arregloBGenero.remove(generoAEliminar)
                return true
            }
            return false

        }

        fun eliminarArtista(id: Int): Boolean {
            val artistaAEliminar = arregloBArtista.find { it.idArtista == id }

            if (artistaAEliminar != null) {
                return arregloBArtista.remove(artistaAEliminar)
            }
            return false

        }

        //-------------------------------------init------------------------
        init {
            arregloBGenero
                .add(
                    BGenero(
                        1, "Rock", 5.0, 1868, true
                    )
                )
            arregloBGenero
                .add(
                    BGenero(
                        2, "Metal sinfónico", 4.9, 1988, true
                    )
                )
            arregloBGenero
                .add(
                    BGenero(
                        3, "Rock Alternativo", 4.5, 1975, true
                    )
                )
            arregloBGenero
                .add(
                    BGenero(
                        4, "Indie", 4.6, 2008, false
                    )
                )

            //Datos del artista
            arregloBArtista
                .add(
                    BArtista(
                        1, "Tarja Turunen", 4.7, "Folklore", 1985, true, 2
                    )
                )
            arregloBArtista
                .add(
                    BArtista(
                        2, "Flor Jasen", 4.5, "÷ (Divide)", 1985, true, 2
                    )
                )
            arregloBArtista
                .add(
                    BArtista(
                        3, "Simone", 4.8, "Lemonade", 1996, true, 2
                    )
                )

        }
    }
}
