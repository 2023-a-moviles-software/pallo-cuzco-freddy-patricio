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
        try {
            val scripSqlInsert =
                "INSERT INTO Artista (idArtista, nombreArtista, puntuacionArtista, fechaArtista, esActivo, id_Genero)" +
                        "VALUES (?, ?, ?, ?, ?, ?)"
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
        } catch (ex: Exception) {
            println("Error al insertar artista: ${ex.message}")
            return false
        }
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
                "ID Genero: ${genero.idGenero}, " +
                        "Nombre del Género: ${genero.nombreGenero}, " +
                        "Puntuación: ${genero.puntuacionGenero}, " +
                        "Fecha: ${genero.fechaGenero}, " +
                        "Es Popular: $esPopularTxt"
            )
            println("───────────────────────────────────────────────────────────────────────────────")
        }
    }
    
    //funcion para mostrar Artista
    fun mostrarArtista(): List<Artista> {
        val sqlMostar = "SELECT * FROM ARTISTA"
        val resultado = statement.executeQuery(sqlMostar)
        val artistaMostrar = mutableListOf<Artista>()
        while (resultado.next()) {
            val idArtista = resultado.getInt("idArtista")
            val nombreArtista = resultado.getString("nombreArtista")
            val puntuacionArtista = resultado.getDouble("puntuacionArtista")
            val fechaArtista = resultado.getString("fechaArtista")
            val esActivo = resultado.getBoolean("esActivo")
            val id_genero = resultado.getInt("id_Genero")
            artistaMostrar.add(
                Artista(
                    idArtista,
                    nombreArtista,
                    puntuacionArtista,
                    fechaArtista,
                    esActivo,
                    id_genero
                )
            )

        }
        return artistaMostrar
    }

    fun mostrarArtistaTotal() {
        val artistas = mostrarArtista()
        println("---------------------------Mostar Artistas Guardados----------------------------")
        println("───────────────────────────────────────────────────────────────────────────────")
        for (artista in artistas) {
            val esPopularTxt = if (artista.esActivo) {
                "Si"
            } else {
                "No"
            }
            println(
                "ID Artistas: ${artista.idArtista}, " +
                        "Nombre del Género: ${artista.nombreArtista}, " +
                        "Puntuación: ${artista.puntuacionArtista}, " +
                        "Fecha: ${artista.fechaArtista}, " +
                        "Es vivo : $esPopularTxt, "
            )
            println("───────────────────────────────────────────────────────────────────────────────")
        }
    }


    //funcion edidtar genero(Actuelizar)
    fun editarGenero(idGenero: Int, actualizarGenero: Genero): Boolean {
        val scripSQLiteEditar =
            "UPDATE GENERO SET nombreGenero=?, puntuacionGenero=?, fechaGenero=?, esPopular=? WHERE idGenero=?"
        val valoresAEditar: PreparedStatement = conexion.prepareStatement(scripSQLiteEditar)
        valoresAEditar.setString(1, actualizarGenero.nombreGenero)
        valoresAEditar.setDouble(2, actualizarGenero.puntuacionGenero)
        valoresAEditar.setString(3, actualizarGenero.fechaGenero)
        valoresAEditar.setBoolean(4, actualizarGenero.esPopular)
        valoresAEditar.setInt(5, actualizarGenero.idGenero)
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


    fun mostrarArtistasPorGenero(idGenero: Int): List<Artista> {
        val sqlMostrarArtistas = "SELECT * FROM Artista WHERE id_Genero = ?"
        val valores = conexion.prepareStatement(sqlMostrarArtistas)
        valores.setInt(1, idGenero)
        val resultado = valores.executeQuery()
        val artistasMostrar = mutableListOf<Artista>()
        while (resultado.next()) {
            // Aquí recuperas los datos del artista y los agregas a la lista artistasMostrar
        }
        return artistasMostrar
    }
}