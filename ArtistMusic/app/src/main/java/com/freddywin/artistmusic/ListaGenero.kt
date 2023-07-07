package com.freddywin.artistmusic

import android.os.Build
import android.support.annotation.RequiresApi
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ListaGenero {

    private val idGeneros = RandomUnrepeated(0, 20)


<<<<<<< HEAD
    //Creacion de genero
=======
    //Creation Restaurant
>>>>>>> main
    @RequiresApi(Build.VERSION_CODES.O)
    fun creacionGenero(): Genero {
        println("Ingrese el nombre del nuevo Genero:")
        val name = readLine()!!
        val id = idGeneros.nextInt()
<<<<<<< HEAD
        println("Ingresé la valoracion del genero , de 0.0 a 5.0:")
=======
        println("Ingresé la duracion , de 0.0 a 5.0:")
>>>>>>> main
        val puntuacion = readLine()!!.toDouble()
        println("Ingresé la fecha de lanzamiento YYYY-MM-DD:")
        val datoCreacionaux = readLine()
        val datoCreacion = LocalDate.parse(datoCreacionaux, DateTimeFormatter.ISO_DATE)
        val esPopular = true
        val nuevoGenero = Genero(name, id, puntuacion, datoCreacion, esPopular)
        return nuevoGenero
    }

    //Escribir Genero
    fun escribirGenero(pathFile: String, genero: Genero, listaGenero: ArrayList<Genero>) {
        listaGenero.add(genero);
        var file: File? = null
        var escribir: FileWriter? = null
        var imprimir: PrintWriter? = null
        var text = ""
        try {
            file = File(pathFile)
            escribir = FileWriter(file, true)
            imprimir = PrintWriter(escribir)
            text = text + genero.nombreGenero + ","
            text = text + genero.idGenero + ","
            text = text + genero.puntuacion + ","
            text = text + genero.fechaLanzamiento + ","
            text = text + genero.esPopular + "\n"
            escribir.write(text)
            println("El genero se ha registrado Exitosamente")
        } catch (e: Exception) {
            println("No se ha podido registar el genero")
        } finally {
            try {
                //finalizacion de tarrea
                escribir?.close()
            } catch (e: Exception) {
                println("No se ha podido registar el Genero $e")
            }
        }
    }

    //Read
    @RequiresApi(Build.VERSION_CODES.O)
    fun leerGenero(pathFile: String): ArrayList<Genero> {
        val listGenero = ArrayList<Genero>()
        try {
            var result = ""
            var line = ""
<<<<<<< HEAD
            val leer = File(pathFile).bufferedReader()
            while (leer.readLine().also { line = it } != null) {
=======
            val reader = File(pathFile).bufferedReader()
            while (reader.readLine().also { line = it } != null) {
>>>>>>> main
                val tokenGenero = StringTokenizer(line, ",")
                var data = tokenGenero.nextToken()
                val nombreGenero = data;
                data = tokenGenero.nextToken()
<<<<<<< HEAD
                val idGenero = data.toInt()
                data = tokenGenero.nextToken()
                val duracion = data.toDouble()
=======
                val idGenero = data.toInt();
                data = tokenGenero.nextToken()
                val duracion = data.toDouble();
>>>>>>> main
                data = tokenGenero.nextToken()
                val fechaLanzamiento = data
                data = tokenGenero.nextToken()
                val esPopular = data.toBoolean()

<<<<<<< HEAD
                val datoCreacionGenero = LocalDate.parse(fechaLanzamiento, DateTimeFormatter.ISO_DATE)
                val nuevoGeneroArchivo = Genero(nombreGenero, idGenero, duracion, datoCreacionGenero, esPopular)
                listGenero.add(nuevoGeneroArchivo)
                result+=line
            }
            leer.close()
        } catch (e: java.lang.Exception) {
=======
                val datoCreacionGenero =
                    LocalDate.parse(fechaLanzamiento, DateTimeFormatter.ISO_DATE)
                val newDishFromFile = Genero(
                    nombreGenero, idGenero, duracion, datoCreacionGenero,
                    esPopular
                )
                listGenero.add(newDishFromFile)
                result += line
            }
            reader.close()
        } catch (e: java.lang.Exception) {
            //println("Erorr!! ${e}")
>>>>>>> main
        }
        return listGenero
    }

    //Actualizar
    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarGenero(
        findGenero: String,
        listaGeneros: ArrayList<Genero>,
        pathFile: String
    ): ArrayList<Genero> {
        try {
            for (generoFind in listaGeneros) {
                if (generoFind.nombreGenero == findGenero) {
                    val indexArtista = listaGeneros.indexOf(generoFind)
                    println("Informacón del Genero" + "\n")
                    println("1-> Nombre del Genero: " + generoFind.nombreGenero)
                    println("2-> Id: " + generoFind.idGenero)
                    println("3-> Puntuacion: " + generoFind.puntuacion)
                    println("4-> Fecha de lanzamiento: " + generoFind.fechaLanzamiento)
                    println("5-> Es popular: " + generoFind.esPopular)
                    println("Seleccione la información deseas cambiar: ")
                    when (readLine()!!.toInt()) {
                        1 -> {
                            println("Ingrese la nueva información:")
                            val nuevoNombre = readLine()
                            generoFind.nombreGenero = nuevoNombre.toString()
                            listaGeneros[indexArtista] = generoFind
                            escribirActualizacionDato(listaGeneros, pathFile)
                            println("El Genero se actualizo con exito!")
                            break
                        }
                        2 -> {
                            println("Ingrese la nueva información:")
                            val nuevoId = readLine()!!.toInt()
                            generoFind.idGenero = nuevoId
                            listaGeneros.set(indexArtista, generoFind)
                            escribirActualizacionDato(listaGeneros, pathFile)
                            println("El Genero se actualizo con exito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información:")
                            val nuevaPuntuacion = readLine()!!.toDouble()
                            generoFind.puntuacion = nuevaPuntuacion
                            listaGeneros.set(indexArtista, generoFind)
                            escribirActualizacionDato(listaGeneros, pathFile)
                            println("El Genero se actualizo con exito!")
                            break
                        }
                        4 -> {
                            println("Ingrese la nueva información con el formato YYYY-MM-DD:")
                            val nuevoDato = readLine()
                            val newDateCreation =
                                LocalDate.parse(nuevoDato, DateTimeFormatter.ISO_DATE)
                            generoFind.fechaLanzamiento = newDateCreation
                            listaGeneros.set(indexArtista, generoFind)
                            escribirActualizacionDato(listaGeneros, pathFile)
                            println("El Genero se actualizo con exito!")
                            break
                        }
                        5 -> {
                            println("Ingrese la nueva información:")
                            val nuevoEspopular = readLine()!!.toBoolean()
                            generoFind.esPopular = nuevoEspopular
                            listaGeneros.set(indexArtista, generoFind)
                            escribirActualizacionDato(listaGeneros, pathFile)
                            println("El Genero se actualizo con exito!")
                            break
                        }
                        else -> {
                            println("Error la operación ingresada no existe !!!")
                            break
                        }
                    }
                } else {
                    println("El Genero ingresado no existe, ingrese un Genero valido")
                }
            }
        } catch (e: Exception) {
            println("Error Update $e")
        }
        return listaGeneros
    }

    //escribir nueva actualizacion
    fun escribirActualizacionDato(listaGenero: ArrayList<Genero>, pathFile: String) {
        try {
            var archivo: File? = null
            var escribir: FileWriter? = null
            var imprimir: PrintWriter? = null
            var text = ""
            for (genero in listaGenero) {
                try {
                    archivo = File(pathFile)
                    escribir = FileWriter(archivo)//true
                    imprimir = PrintWriter(escribir)
                    text = text + genero.nombreGenero + ",";
                    text = text + genero.idGenero + ",";
                    text = text + genero.puntuacion + ",";
                    text = text + genero.fechaLanzamiento + ",";
                    text = text + genero.esPopular + "\n";
                    escribir.write(text);
                    escribir.write("\n");
                } catch (e: Exception) {
                    println("Error de actualizar Genero $e")
                } finally {
                    try {
                        if (escribir != null) {
                            escribir.close()
                        }
                    } catch (e: Exception) {
                        println("Error de actualizar Genero $e")
                    }
                }
            }
        } catch (e: Exception) {
            println("Error de Actualizar $e")
        }
    }

    //Eliminar genero
<<<<<<< HEAD
    fun eliminarGenero(findGenero: String, listaGenero: ArrayList<Genero>, pathFile: String):
            ArrayList<Genero> {
        try {
            for (encontrarGenero in listaGenero) {
                if (encontrarGenero.nombreGenero == findGenero) {
                    listaGenero.remove(encontrarGenero)
                    escribirActualizacionDato(listaGenero, pathFile)
=======
    fun eliminarGenero(findGenero: String, listaArtista: ArrayList<Genero>, pathFile: String):
            ArrayList<Genero> {
        try {
            for (encontrarGenero in listaArtista) {
                if (encontrarGenero.nombreGenero == findGenero) {
                    listaArtista.remove(encontrarGenero)
                    escribirActualizacionDato(listaArtista, pathFile)
>>>>>>> main
                    println("Genero eliminado con exito!!")
                    break
                } else {
                    println("El genero ingresado no existe, ingrese uno valido")
                }
            }
        } catch (e: Exception) {
            println("Error de eliminar Artista $e")
        }
<<<<<<< HEAD
        return listaGenero
=======
        return listaArtista
>>>>>>> main
    }
}
