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

class ListaArtista() {
    private val idArtista = RandomUnrepeated(0, 20)

    //Creacion artista
    @RequiresApi(Build.VERSION_CODES.O)
    fun creacionArtista(): Artista {
        println("Ingrese el Nombre del artista: ")
        val nombreArtista = readLine()!!
        val idArtista = idArtista.nextInt()
        println("ingrese la valoracion del artista")
        val valoracion = readLine()!!.toDouble()
        println("Ingrese la fecha de creacion del Artista YY-MM-DD")
        val datosCreacionArtistaLeer = readLine()!!
        val datoCreacionArtista =
            LocalDate.parse(datosCreacionArtistaLeer, DateTimeFormatter.ISO_TIME)
        val activo = true
        val nuevoArtista =
            Artista(nombreArtista, idArtista, valoracion, datoCreacionArtista, activo)
        return nuevoArtista
    }

    //Escribir artista de Artista
    fun escribirArtista(pathFile: String, artista: Artista, listaArtista: ArrayList<Artista>) {
        listaArtista.add(artista);
        var file: File? = null
        var escribir: FileWriter? = null
        var imprimir: PrintWriter? = null
        var text = ""
        try {
            file = File(pathFile)
            escribir = FileWriter(file, true)
            imprimir = PrintWriter(escribir)
            text = text + artista.nombreArtista + ","
            text = text + artista.idArtista + ","
            text = text + artista.valoracion + ","
            text = text + artista.activo + ","
            text = text + artista.datosCreacionArtista + "\n"
            escribir.write(text)
            println("El artista se ha registrado Exitosamente")
        } catch (e: Exception) {
            println("No se ha podido registar el artista")
        } finally {
            try {
                //finalizacion de tarrea
                escribir?.close()
            } catch (e: Exception) {
                println("No se ha podido registar el artista")
            }
        }
    }

    //Leer arista
    @RequiresApi(Build.VERSION_CODES.O)
    fun leerArtista(pathFile: String): ArrayList<Artista> {
        val listArtista = ArrayList<Artista>()

        try {
            var result = ""
            var line = ""
            val leer = File(pathFile).bufferedReader()
            while (leer.readLine().also { line = it } != null) {
                val tokenArtista = StringTokenizer(line, ",")
                var dato = tokenArtista.nextToken()

                val nombreArtista = dato
                dato = tokenArtista.nextToken()

                val idArtista = dato.toInt()
                dato = tokenArtista.nextToken()

                val valoracion = dato.toDouble()
                dato = tokenArtista.nextToken()

                val activo = dato.toBoolean()
                dato = tokenArtista.nextToken()

                val datoCreacionArtistas = dato

                val datoCreacionArtista =
                    LocalDate.parse(datoCreacionArtistas, DateTimeFormatter.ISO_DATE);
                val nuevoArtistaArchivo =
                    Artista(nombreArtista, idArtista, valoracion, datoCreacionArtista, activo)
                listArtista.add(nuevoArtistaArchivo)
                result += line
            }
            leer.close()
        } catch (e: Exception) {
            println("Erorr de leer archivo!! ${e}")
        }
        return listArtista
    }


    //Actualizar
    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarArtista(
        findArtista: String,
        listaArtista: ArrayList<Artista>,
        pathFile: String
    ): ArrayList<Artista> {
        try {
            for (encontrarArtista in listaArtista) {
                if (encontrarArtista.nombreArtista == findArtista) {
                    val indexArtista = listaArtista.indexOf(encontrarArtista)
                    println("Informacion del Artista: " + "\n")
                    println("1. Nombre del Artista: " + encontrarArtista.nombreArtista)
                    println("2. Id: " + encontrarArtista.idArtista)
                    println("3. Valoracion: " + encontrarArtista.valoracion)
                    println("4. Disponible: " + encontrarArtista.activo)
                    println("5. Fecha de creación: " + encontrarArtista.datosCreacionArtista)
                    println("Seleccione la información deseas cambiar: ")
                    when (readLine()!!.toInt()) {
                        1 -> {
                            println("Ingrese la nueva información:")
                            val nuevoArtista = readLine()
                            encontrarArtista.nombreArtista = nuevoArtista.toString()
                            listaArtista[indexArtista] = encontrarArtista
                            escribirActualizacionDato(listaArtista, pathFile)
                            println("El Artista se actualizo con exito")
                            break
                        }
                        2 -> {
                            println("Ingrese la nueva información: ")
                            val nuevoId = readLine()!!.toInt()
                            encontrarArtista.idArtista = nuevoId
                            listaArtista.set(indexArtista, encontrarArtista)
                            escribirActualizacionDato(listaArtista, pathFile)
                            println("El Artista se actualizo con exito!")
                            break
                        }
                        3 -> {
                            println("Ingrese la nueva información: ")
                            val nuevaValoracion = readLine()!!.toDouble()
                            encontrarArtista.valoracion = nuevaValoracion
                            listaArtista.set(indexArtista, encontrarArtista)
                            escribirActualizacionDato(listaArtista, pathFile)
                            println("El Artista se actualizo con exito")
                            break
                        }
                        4 -> {
                            println("Ingrese la nueva información con el formato YYYY-MM-DD:")
                            val nuevoFecha = readLine()
                            val newDateCreation =
                                LocalDate.parse(nuevoFecha, DateTimeFormatter.ISO_DATE)
                            encontrarArtista.datosCreacionArtista = newDateCreation
                            listaArtista.set(indexArtista, encontrarArtista)
                            escribirActualizacionDato(listaArtista, pathFile)
                            println("El Artista se actualizo con exito!")
                            break
                        }
                        5 -> {
                            println("Ingrese la nueva información:")
                            val nuevoActivo = readLine()!!.toBoolean()
                            encontrarArtista.activo = nuevoActivo
                            listaArtista.set(indexArtista, encontrarArtista)
                            escribirActualizacionDato(listaArtista, pathFile)
                            println("El Artista se actualizo con exito!")
                            break
                        }
                        else -> {
                            println("Error la operación ingresada no existe !!!")
                            break
                        }
                    }
                } else {
                    println("El Artista ingresado no existe, ingrese un otro valido")
                }
            }
        } catch (e: Exception) {
            println("Error de actualizar $e")
        }
        return listaArtista
    }

    //escribir actualizacion del artista
    fun escribirActualizacionDato(listaArtista: ArrayList<Artista>, pathFile: String) {
        try {
            var file: File? = null
            var escribir: FileWriter? = null
            var imprimir: PrintWriter? = null
            var text = ""
            for (artista in listaArtista) {
                try {
                    file = File(pathFile)
                    escribir = FileWriter(file)//true
                    imprimir = PrintWriter(escribir)
                    text = text + artista.nombreArtista + ",";
                    text = text + artista.idArtista + ",";
                    text = text + artista.valoracion + ",";
                    text = text + artista.datosCreacionArtista + ",";
                    text = text + artista.activo + "\n";

                    escribir.write(text);
                } catch (e: Exception) {
                    println("Error escribir la actualizacion del artista $e")
                } finally {
                    try {
                        if (escribir != null) {
                            escribir.close()
                        }
                    } catch (e: Exception) {
                        println("Error escribir la actualizacion del artista $e")
                    }
                }
            }
        } catch (e: Exception) {
            println("Error Update $e")
        }
    }

    //Eliminar artista
    fun borrarArtista(
        findArtista: String,
        listaArtista: ArrayList<Artista>,
        pathFile: String
    ): ArrayList<Artista> {
        try {
            for (encontrarArtista in listaArtista) {
                if (encontrarArtista.nombreArtista == findArtista) {
                    listaArtista.remove(encontrarArtista)
                    escribirActualizacionDato(listaArtista, pathFile)
                    println("Artista eliminado con exito!!")
                    break;
                } else {
                    println("El Artista ingresado no existe, ingrese un Artista registrado")
                }
            }
        } catch (e: Exception) {
            println("Error de eliminar Artista $e")
        }
        return listaArtista
    }
}