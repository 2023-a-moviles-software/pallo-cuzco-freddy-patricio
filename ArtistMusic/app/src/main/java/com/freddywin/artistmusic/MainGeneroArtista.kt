package com.freddywin.artistmusic

import android.os.Build
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun main(args: Array<String>) {

    val lugarGeneroArchivo = "Genero.txt"
    var lugarArtistaArchivo: String

    //Llamando la clase
    val menuArtista = MenuMusicPrincipal()
    val menuGenero = MenuMusicPrincipal()
    val listaArtistaClase = ListaArtista()
    val listaGeneroClase = ListaGenero()

    //Variables a pciones a realizar
    var opcionMenuArtista = true
    var opcionMenuGenero = true

    println()
    println("_____________________________________________________________________")
    println("|                Gestiones de Generos-Artista                       |")
    println("|___________________________________________________________________|")
    try {
        while (opcionMenuGenero) {
            println()
            menuGenero.mostrarListaGenero()
            when (readLine()!!.toInt()) {
                1 -> {
                    val nuevoGenero = listaGeneroClase.creacionGenero()
                    val aux = listaGeneroClase.leerGenero(lugarGeneroArchivo)
                    listaGeneroClase.escribirGenero(lugarGeneroArchivo, nuevoGenero, aux)
                    println()
                }
                2 -> {
                    println("Los Generos de musica registrados son: ")
                    val auxLeerGenero = listaGeneroClase.leerGenero(lugarGeneroArchivo)
                    println(auxLeerGenero)

                }
                3 -> {
                    println("1->Actuaizar Genero\n2->Ir Modulo Artista")
                    println("Ingrese la Operacion: ")
                    when (readLine()!!.toInt()) {
                        1 -> {
                            println("Los Generos registrados son: ")
                            val auxLeerGenero = listaGeneroClase.leerGenero(lugarGeneroArchivo)
                            println(auxLeerGenero)
                            println()
                            println("Ingrese el nombre del Genero a Actualizar")
                            val actualizarGenero = readLine()!!
                            println()
                            println("Actualizar infomacion del Genero")
                            listaGeneroClase.actualizarGenero(
                                actualizarGenero,
                                auxLeerGenero,
                                lugarGeneroArchivo
                            )
                        }
                        2 -> {
                            try {
                                println()
                                println("Ingrese el nombre de Genero al que pertenece el Artista: ")
                                val actualizarGenero = readLine()!!
                                lugarArtistaArchivo = "$actualizarGenero.txt"
                                while (opcionMenuArtista) {
                                    println()
                                    menuArtista.mostrarListaArtista()
                                    when (readLine()!!.toInt()) {
                                        1 -> {
                                            val nuevoArtista = listaArtistaClase.creacionArtista()
                                            var auxArtista =
                                                listaArtistaClase.leerArtista(lugarArtistaArchivo)
                                            listaArtistaClase.escribirArtista(
                                                lugarGeneroArchivo,
                                                nuevoArtista,
                                                auxArtista
                                            )
                                        }
                                        2 -> {
                                            println("Los Artista registrados son: ")
                                            val auxLeerArtista =
                                                listaArtistaClase.leerArtista(lugarArtistaArchivo)
                                            println(auxLeerArtista)
                                        }
                                        3 -> {
                                            println("Los Artista registrados son: ")
                                            val auxLeerArtista =
                                                listaArtistaClase.leerArtista(lugarArtistaArchivo)
                                            println(auxLeerArtista)
                                            println()
                                            println("Ingrese el nombre del Artista que desea actualizar: ")
                                            val actualizarArtista = readLine()!!
                                            println()
                                            println("Actualizar informacion del Artista")
                                            listaArtistaClase.actualizarArtista(
                                                actualizarArtista,
                                                auxLeerArtista,
                                                lugarArtistaArchivo
                                            )
                                        }

                                        4 -> {
                                            println("Los Artistas registrados son: ")
                                            val auxLeerArtista =
                                                listaArtistaClase.leerArtista(lugarArtistaArchivo)
                                            println(auxLeerArtista)
                                            println()
                                            println("Ingrese el nombre del Artista que desea ELIMINAR: ")
                                            val eliminarArtista = readLine()!!
                                            listaArtistaClase.borrarArtista(
                                                eliminarArtista,
                                                auxLeerArtista,
                                                lugarArtistaArchivo
                                            )
                                            println()
                                            println("Los Artistas registrados son: ")
                                            val auxLeerArtistas =
                                                listaArtistaClase.leerArtista(lugarArtistaArchivo)
                                            println()
                                        }

                                        5 -> {
                                            println("Retroceder ")
                                            println()
                                            opcionMenuArtista = false
                                        }
                                        else -> {
                                            println("Error la operación ingresada no existe !!!")
                                        }
                                    }
                                }
                            } catch (e: Exception) {
                                println(" Error de modulo de Artista !! $e")
                            }
                        }
                    }
                }

                4 -> {
                    println("Los Generos Registrado son: ")
                    val auxLeerGenero = listaGeneroClase.leerGenero(lugarGeneroArchivo)
                    println(auxLeerGenero)
                    println()
<<<<<<< HEAD
                    println("Ingrese el nombre del genero que desea eliminar")
=======
                    println("Ingrese el nombre del restaurate que desea eliminar")
>>>>>>> main
                    val eliminarGeneros = readLine()!!
                    val auxEliminar = listaGeneroClase.eliminarGenero(
                        eliminarGeneros,
                        auxLeerGenero,
                        lugarGeneroArchivo
                    )
<<<<<<< HEAD
                    println("Los Generos registrados son: ")
=======
                    println("Los Restaurantes registrados son: ")
>>>>>>> main
                    println(auxEliminar)
                }

                5 -> {
                    println("Cerrando Gestion...")
                    println("Gestion cerrado  ✔")
                    opcionMenuGenero = false
                }
            }

        }
    } catch (e: Exception) {
        println("Error Menu $e")
    }
}

