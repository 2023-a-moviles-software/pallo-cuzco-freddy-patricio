package com.freddywin.sqlitecrud

import java.sql.DriverManager
import java.sql.Statement

fun main(args: Array<String>) {
    println("hola")
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
                println("---------------------------Mostar Generos Guardados----------------------------")
                println("───────────────────────────────────────────────────────────────────────────────")
                val generos = dbHelper.mostarGeneroTotal(conexionABasesDeDatos)
                for (genero in generos) {
                    val esPopularTxt = if (genero.esPopular) {
                        "Si"
                    } else {
                        "No"
                    }
                    println(
                        "Nombre del Género: ${genero.nombreGenero}, "
                                + "Puntuación: ${genero.puntuacionGenero}, "
                                + "Fecha: ${genero.fechaGenero}, "
                                + "Es Popular: $esPopularTxt"
                    )
                    println("───────────────────────────────────────────────────────────────────────────────")
                }
            }
            3 -> {
                println("Eliminaer Genero por ID")
                println("───────────────────────────────────────────────────────────────────────────────")
                val generos = dbHelper.mostarGeneroTotal(conexionABasesDeDatos)
                for (genero in generos) {
                    val esPopularTxt = if (genero.esPopular) {
                        "Si"
                    } else {
                        "No"
                    }
                    println(
                        "ID Género: ${genero.idGenero}, "
                                + "Nombre del Género: ${genero.nombreGenero}, "
                                + "Puntuación: ${genero.puntuacionGenero}, "
                                + "Fecha: ${genero.fechaGenero}, "
                                + "Es Popular: $esPopularTxt"
                    )
                    println("───────────────────────────────────────────────────────────────────────────────")
                }
                print("Ingrese el ID del Genero: ")
                val idGenero = readLine()?.toIntOrNull() ?: continue
                dbHelper.eliminarGenero(conexionABasesDeDatos, idGenero)
                println("Género Eiminado")

            }
            4 -> {

            }
            0 -> break
            else -> println("Opcion No Valida")

        }
    }
    dbHelper.conexion.close()
    statement.close()
    conexionABasesDeDatos.close()
}