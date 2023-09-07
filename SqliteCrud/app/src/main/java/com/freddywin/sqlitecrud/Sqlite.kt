package com.freddywin.sqlitecrud

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.Statement

data class Genero(
    var idGenero: Int,
    var nombreGenero: String,
    var puntuacionGenero: Double,
    var fechaGenero: String,
    var esPopular: Boolean
)

data class Artista(
    var idArtista: Int,
    var nombreArtista: String,
    var puntuacionArtista: Double,
    var fechaArtista: String,
    var esActivo: Boolean,
    var id_genero: Int
)

class Sqlite(val conexion: Connection) {

    private val statement: Statement = conexion.createStatement()

    init {
        crearTablas()
    }

    fun crearTablas() {
        val statement: Statement = conexion.createStatement()
        val scripSQLTablaGenero = """
            CREATE TABLE IF NOT EXISTS GENERO(
            idGenero INTEGER PRIMARY KEY,
            nombreGenero VARCHAR(50),
            puntuacionGenero DECIMAL(5.2),
            fechaGenero DATE,
            esPopular BOOLEAN        
            )
        """.trimIndent()
        statement.executeUpdate(scripSQLTablaGenero)

        val scripSQLTablaArtista = """
            CREATE TABLE IF NOT EXISTS Artista(
            idArtista INTEGER PRIMARY KEY,
            nombreArtista VARCHAR(50),
            puntuacionArtista DECIMAL(5.2),
            fechaArtista DATE,
            esActivo BOOLEAN,
            id_Genero integer,
            FOREIGN KEY(id_Genero) REFERENCES GENERO(idGenero)
            )
        """.trimIndent()
        statement.executeUpdate(scripSQLTablaArtista)
    }

    fun insertarGenero(connection: Connection, genero: Genero): Boolean {
        val scripSqlInsert =
            "INSERT INTO GENERO (idGenero, nombreGenero, puntuacionGenero, fechaGenero, esPopular)" +
                    "VALUES (?, ?, ?, ?, ?)"
        val valoresAGuardar: PreparedStatement =
            connection.prepareStatement(scripSqlInsert)
        valoresAGuardar.setInt(1, genero.idGenero)
        valoresAGuardar.setString(2, genero.nombreGenero)
        valoresAGuardar.setDouble(3, genero.puntuacionGenero)
        valoresAGuardar.setString(4, genero.fechaGenero)
        valoresAGuardar.setBoolean(5, genero.esPopular)

        val resulAGuadar = valoresAGuardar.executeUpdate()
        valoresAGuardar.close()
        return resulAGuadar > 0
    }

    fun insertarArtista(connection: Connection, artista: Artista): Boolean {
        val scripSqlInsert =
            "INSERT INTO ARTISTA (idArtista, nombreArtista, puntuacionArtista,fechaArtista, esActivo, id_genero)" +
                    "VALUES(?,?,?,?,?)"
        val valoresAGuardar: PreparedStatement =
            connection.prepareStatement(scripSqlInsert)
        valoresAGuardar.setInt(1, artista.idArtista)
        valoresAGuardar.setString(2, artista.nombreArtista)
        valoresAGuardar.setDouble(3, artista.puntuacionArtista)
        valoresAGuardar.setString(4, artista.fechaArtista)
        valoresAGuardar.setBoolean(5, artista.esActivo)
        valoresAGuardar.setInt(6, artista.id_genero)

        val resulAGuadar = valoresAGuardar.executeUpdate()
        valoresAGuardar.close()
        return resulAGuadar > 0
    }

    //funcion para mostrar Genero
    fun mostrarGenero(): List<Genero> {
        val sqlMostar = "SELECT * FROM GENERO"
        val resultado = statement.executeQuery(sqlMostar)
        val generoMostrar = mutableListOf<Genero>()
        while (resultado.next()) {
            val idGenero = resultado.getInt("idGenero")
            val nombreGenero = resultado.getString("nombreGenero")
            val puntuacionGenero = resultado.getDouble("puntuacionGenero")
            val fechaGenero = resultado.getString("fechaGenero")
            val esPopular = resultado.getBoolean("esPopular")
            generoMostrar.add(
                Genero(
                    idGenero,
                    nombreGenero,
                    puntuacionGenero,
                    fechaGenero,
                    esPopular
                )
            )

        }
        return generoMostrar
    }

    fun mostrarGeneroTotal() {
        val generos = mostrarGenero()
        println("---------------------------Mostar Generos Guardados----------------------------")
        println("───────────────────────────────────────────────────────────────────────────────")
        for (genero in generos) {
            val esPopularTxt = if (genero.esPopular) {
                "Si"
            } else {
                "No"
            }
            println(
                "ID Genero: ${genero.idGenero}" +
                        "Nombre del Género: ${genero.nombreGenero}, " +
                        "Puntuación: ${genero.puntuacionGenero}, " +
                        "Fecha: ${genero.fechaGenero}, " +
                        "Es Popular: $esPopularTxt"
            )
            println("───────────────────────────────────────────────────────────────────────────────")
        }
    }

    //funcion edidtar genero(Actuelizar)
    fun editarGenero(conexion: Int, genero: Genero): Boolean {
        val scripSQLiteEditar =
            "UPDATE GENERO SET nombreGenero=?, puntuacionGenero=?, fechaGenero=?, esPopular=? WHERE idGenero=?"
        val valoresAEditar: PreparedStatement = conexion.prepareStatement(scripSQLiteEditar)
        valoresAEditar.setString(1, genero.nombreGenero)
        valoresAEditar.setDouble(2, genero.puntuacionGenero)
        valoresAEditar.setString(3, genero.fechaGenero)
        valoresAEditar.setBoolean(4, genero.esPopular)
        valoresAEditar.setInt(5, genero.idGenero)

        val resultado = valoresAEditar.executeUpdate()
        valoresAEditar.close()
        return resultado > 0
    }

    //Funciones eliminar
    fun eliminarGenero(id: Int): Boolean {
        val scripSqlBorrar = "DELETE FROM GENERO WHERE idGenero = ?"
        val preparedStatement: PreparedStatement =
            conexion.prepareStatement(scripSqlBorrar)
        preparedStatement.setInt(1, id)

        val resultado = preparedStatement.executeUpdate()
        preparedStatement.close()
        return resultado > 0
    }

    //Funciones eliminar
    fun eliminarArtista(id: Int): Boolean {
        val scripSqlBorrar = "DELETE FROM GENERO WHERE idArtista = ?"
        val preparedStatement: PreparedStatement =
            conexion.prepareStatement(scripSqlBorrar)
        preparedStatement.setInt(1, id)

        val resultado = preparedStatement.executeUpdate()
        preparedStatement.close()
        return resultado > 0
    }


}