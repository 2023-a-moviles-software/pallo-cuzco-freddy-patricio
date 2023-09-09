package com.freddywin.sqlitecrud

import java.sql.DriverManager
import java.sql.Statement

fun main(args: Array<String>) {
    val conexionDB = "jdbc:sqlite:Bases_Datos_GeneroArtista"
    val conexionABasesDeDatos = DriverManager.getConnection(conexionDB)
    val dbHelper = Sqlite(conexionABasesDeDatos)
    val statement: Statement = conexionABasesDeDatos.createStatement()


    while (true) {
        println("1. Ingresar Genero")
        println("2. Mostrar generos")
        println("3. Eliminar Genero")
        println("4. Editar Genero")
        println("5. Ingresar a un Género")
        println("0. Salir")
        print("Selecciones una opcion: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Ingresar Genero")
                // Aquí puedes solicitar al usuario que ingrese los datos del género
                print("Ingrese el ID del Genero: ")
                val idGenero = readLine()?.toIntOrNull() ?: continue
                print("Ingrese el nombre del Genero:")
                val nombreGenero = readLine() ?: continue
                print("Ingrese la puntuación del Genero: ")
                val puntuacionGenero = readLine()?.toDoubleOrNull() ?: continue
                print("Ingrese la fecha del Genero(DD-MM-AAAA): ")
                val fechaGenero = readLine() ?: continue
                print("Ingrese si el Genero es popular (true/false): ")
                val esPopular = readLine()?.toBoolean() ?: continue

                val nuevoGenero =
                    Genero(idGenero, nombreGenero, puntuacionGenero, fechaGenero, esPopular)
                val exito = dbHelper.insertarGenero(conexionABasesDeDatos, nuevoGenero)

                if (exito) {
                    println("Genero ingresado exitosamente.")
                } else {
                    println("Error al ingresar el Genero.")
                }
            }
            2 -> {
                dbHelper.mostrarGeneroTotal()
            }
            3 -> {
                println("Eliminaer Genero")
                dbHelper.mostrarGeneroTotal()
                print("Ingrese el ID del Genero: ")
                val idGeneroEliminar = readLine()?.toInt() as Int
                val generoEliminado = dbHelper.eliminarGenero(idGeneroEliminar)
                if (generoEliminado) {
                    println("Genero Eliminado Exitozamente")
                    println()
                } else {
                    println("Fallo de Eliminar Genero")
                }
            }
            4 -> {
                println("Editar Genero")
                dbHelper.mostrarGeneroTotal()

                println("Seleciones el ID para Actualizar")
                val idGeneroEditar = readLine()?.toIntOrNull() ?: continue

                // Solicitar los nuevos datos del género
                print("Ingrese el nuevo nombre del Género: ")
                val nuevoNombreGenero = readLine() ?: continue
                print("Ingrese la nueva puntuación del Género: ")
                val nuevaPuntuacionGenero = readLine()?.toDoubleOrNull() ?: continue
                print("Ingrese la nueva fecha del Género (DD-MM-AAAA): ")
                val nuevaFechaGenero = readLine() ?: continue
                print("Ingrese si el Género es popular (true/false): ")
                val nuevoEsPopular = readLine()?.toBoolean() ?: continue

                // Crear un nuevo objeto Genero con los datos actualizados
                val generoActualizado = Genero(
                    idGeneroEditar,
                    nuevoNombreGenero,
                    nuevaPuntuacionGenero,
                    nuevaFechaGenero,
                    nuevoEsPopular
                )

                val exitoEdicion = dbHelper.editarGenero(idGeneroEditar, generoActualizado)

                if (exitoEdicion) {
                    println("Género editado exitosamente.")
                } else {
                    println("Error al editar el Género. Asegúrate de que el ID sea válido.")
                }
            }
            5 -> {
                dbHelper.mostrarGeneroTotal()
                println("Mostrar Datos de Artista")
                print("Ingrese ID del genero")
                val idGenero = readLine()?.toIntOrNull() ?: continue
                val artistasDelGenero = dbHelper.mostrarArtistasPorGenero(idGenero)

                for (artista in artistasDelGenero) {
                    println("ID Artista: ${artista.idArtista}, Nombre: ${artista.nombreArtista}")
                }
                while (true) {
                    println("1. Ingresar Artista")
                    println("2. Mostrar Artista")
                    println("3. Eliminar Artista")
                    println("4. Editar Artista")
                    println("0. Salir")
                    print("Selecciones una opcion: ")

                    when (readLine()?.toIntOrNull()) {
                        1 -> {
                            println("Ingresar Artista")
                            print("Ingrese el ID del Artista: ")
                            val idArtista = readLine()?.toIntOrNull() ?: continue
                            print("Ingrese el nombre del Artista: ")
                            val nombreArtista = readLine() ?: continue
                            print("Ingrese la puntuación del Artista: ")
                            val puntuacionArtista = readLine()?.toDoubleOrNull() ?: continue
                            print("Ingrese la fecha del Artista(DD-MM-AAAA): ")
                            val fechaArtista = readLine() ?: continue
                            print("Ingrese si es popular (true/false): ")
                            val esActivo = readLine()?.toBoolean() ?: continue
                            print("Ingrese ID del genero: ")
                            val idGenero = readLine()?.toIntOrNull() ?: continue

                            val nuevoArtista =
                                Artista(
                                    idArtista,
                                    nombreArtista,
                                    puntuacionArtista,
                                    fechaArtista,
                                    esActivo,
                                    idGenero
                                )
                            val exito =
                                dbHelper.insertarArtista(conexionABasesDeDatos, nuevoArtista)

                            if (exito) {
                                println("Genero ingresado exitosamente.")
                            } else {
                                println("Error al ingresar el Genero.")
                            }
                        }
                        2 -> {
                            dbHelper.mostrarArtistaTotal()

                        }
                        3 -> {

                        }
                        4 -> {

                        }
                        0 -> break  // Salir del menú de artistas relacionados a este género
                        else -> println("Opción no válida")
                    }
                }
            }
            0 -> break
            else -> println("Opcion No Valida")

        }
    }
    dbHelper.conexion.close()
    statement.close()
    conexionABasesDeDatos.close()
}