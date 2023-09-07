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
                dbHelper.mostrarGeneroTotal()
            }
            3 -> {
                println("Eliminaer Genero")
                dbHelper.mostrarGeneroTotal()
                println("───────────────────────────────────────────────────────────────────────────────")
                print("Ingrese el ID del Genero: ")
                val idGeneroEliminar = readLine()?.toIntOrNull() ?: continue
                val generoEliminado=dbHelper.editarGenero(idGeneroEliminar)
                if (generoEliminado){
                    println("Genero Eliminado Exitozamente")
                }else{
                    println("Fallo de Eliminar Genero")
                }
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